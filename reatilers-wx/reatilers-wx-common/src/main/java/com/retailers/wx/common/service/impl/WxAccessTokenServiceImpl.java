
package com.retailers.wx.common.service.impl;

import com.alibaba.druid.sql.dialect.postgresql.ast.stmt.PGSelectQueryBlock;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retailers.mybatis.common.constant.SingleThreadLockConstant;
import com.retailers.mybatis.common.constant.SysParameterConfigConstant;
import com.retailers.mybatis.common.service.ProcedureToolsService;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.DateUtil;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import com.retailers.wx.common.config.WxConfig;
import com.retailers.wx.common.dao.WxAccessTokenMapper;
import com.retailers.wx.common.dao.WxManagerMapper;
import com.retailers.wx.common.dao.WxPayMapper;
import com.retailers.wx.common.enm.WXAccountEnum;
import com.retailers.wx.common.entity.WxAccessToken;
import com.retailers.wx.common.entity.WxManager;
import com.retailers.wx.common.service.WxAccessTokenService;
import com.retailers.wx.common.utils.WxHttpClientUtils;
import com.retailers.wx.common.utils.WxReqUtils;
import com.retailers.wx.common.vo.WxPayVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 描述：微信认证tokenService
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-31 22:05:24
 */
@Service("wxaccesstokenService")
public class WxAccessTokenServiceImpl implements WxAccessTokenService {
	Logger logger = LoggerFactory.getLogger(WxAccessTokenServiceImpl.class);
	@Autowired
	private WxAccessTokenMapper wxAccessTokenMapper;
	@Autowired
	private WxManagerMapper wxManagerMapper;
	@Autowired
	private ProcedureToolsService procedureToolsService;
	@Autowired
	private WxPayMapper wxPayMapper;

	public boolean saveWxAccessToken(WxAccessToken wxAccessToken) {
		int status = wxAccessTokenMapper.saveWxAccessToken(wxAccessToken);
		return status == 1 ? true : false;
	}
	public boolean updateWxAccessToken(WxAccessToken wxAccessToken) {
		int status = wxAccessTokenMapper.updateWxAccessToken(wxAccessToken);
		return status == 1 ? true : false;
	}
	public WxAccessToken queryWxAccessTokenByWatId(Long watId) {
		return wxAccessTokenMapper.queryWxAccessTokenByWatId(watId);
	}

	public Pagination<WxAccessToken> queryWxAccessTokenList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<WxAccessToken> page = new Pagination<WxAccessToken>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<WxAccessToken> list = wxAccessTokenMapper.queryWxAccessTokenList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteWxAccessTokenByWatId(Long watId) {
		int status = wxAccessTokenMapper.deleteWxAccessTokenByWatId(watId);
		return status == 1 ? true : false;
	}

	public void initWxToken() {
		logger.info("开始取得微信当前token");
		Date curDate= new Date();
		//取得当前使用微信
		if(ObjectUtils.isNotEmpty(WxConfig.APP_ID)){
			//根据当前使用微信，取得token
			WxAccessToken token=wxAccessTokenMapper.queryCurWxAccessTokenByWxId(WxConfig.APP_ID,curDate);
			//判断当前token是否为空 如果当前token 还处于有效期则不进行处理。
			if(ObjectUtils.isNotEmpty(token)){
				WxConfig.ACCESS_TOKEN=token.getWatToken();
				WxConfig.ACCESS_TICKET=token.getWatTicket();
			}else{
				pullWxToken(WxConfig.APP_ID,WxConfig.APP_SECRET);
			}
		}else{
			logger.info("未配置公众号信息");
		}
	}

	/**
	 * 取得微信token 和jsapi token
	 * @param appId
	 * @param appSecret
	 */
	private void pullWxToken(String appId,String appSecret){
		logger.info("重新取得微信token");
		Date curDate=new Date();
		String key= SingleThreadLockConstant.PULL_WX_TOKEN;
		try{
			procedureToolsService.singleLockManager(key);
			//取得最新token
			String rtn = WxReqUtils.getToken(appId,appSecret);
			if(ObjectUtils.isNotEmpty(rtn)){
				WxAccessToken token = new WxAccessToken();
				JSONObject obj = JSONObject.parseObject(rtn);
				System.out.println(obj);
				if(obj.containsKey("errcode")){
				}else{
					int expires=obj.getIntValue("expires_in");
					String jsapiTicket=WxReqUtils.getTicket(obj.getString("access_token"));
					JSONObject ticketObj=JSONObject.parseObject(jsapiTicket);
					if(ticketObj.containsKey("errcode")&&ticketObj.getIntValue("errcode")==0){
						String ticket=ticketObj.getString("ticket");
						//取得ticket
						Date expiresTime= DateUtil.addSecond(curDate,(expires-60*10));
						token.setWatToken(obj.getString("access_token"));
						token.setWatTokenCreateTime(curDate);
						token.setWatTokenExpiresTime(expiresTime);
						token.setWatTokenExpires(expires);
						token.setWatWxAppId(appId);
						token.setWatTicket(ticket);
						WxConfig.ACCESS_TOKEN=token.getWatToken();
						WxConfig.ACCESS_TICKET=ticket;
						//取得js-sdk 的token
						wxAccessTokenMapper.saveWxAccessToken(token);
					}
				}
			}
		}catch(AppException e){
			logger.error(StringUtils.getErrorInfoFromException(e));
		}finally {
			procedureToolsService.singleUnLockManager(key);
		}
		logger.info("重新取得微信结束,执行时间:[{}]",(System.currentTimeMillis()-curDate.getTime()));
	}

	/**
	 *初始化公众号配置参数
	 */
	public void initWxConfig() {
		logger.info("初始化微信配置");
		//取得当前使用微信
		WxManager wxManager= wxManagerMapper.queryCurUsedWx(WXAccountEnum.WX_GZH.getType());
		if(ObjectUtils.isNotEmpty(wxManager)){
			WxConfig.APP_ID=wxManager.getAppId();
			WxConfig.APP_SECRET=wxManager.getAppSecret();
			WxConfig.WX_CHECK_REQ_TOKEN=wxManager.getWxToken();
			//取得支付信息
			WxPayVo wxPayVo= wxPayMapper.queryCurUsedPay();
			if(ObjectUtils.isNotEmpty(wxPayVo)){
				WxConfig.WX_MCH_ID=wxPayVo.getWxMchId();
				WxConfig.WX_API_KEY=wxPayVo.getWxApiKey();
				if(ObjectUtils.isNotEmpty(wxPayVo.getWxCertificateCode())){
					WxConfig.WX_CRET_FILE=StringUtils.formate(wxPayVo.getWxCertificateCode()+"",wxPayVo.getWxLocalCertificateCodeAddr());
					WxConfig.WX_REMOTE_FILE_URL=wxPayVo.getRomoteFile();
					WxConfig.WX_REMOTE_FILE_ID=wxPayVo.getWxCertificateCode()+"";
					WxHttpClientUtils.downWxCretFile(SysParameterConfigConstant.getValue(SysParameterConfigConstant.WX_PAY_CERT_LOCAL_ADDRESS),WxConfig.WX_CRET_FILE,wxPayVo.getRomoteFile());

				}
			}
		}
		logger.info("初始化微信配置结束");
	}
}

