
package com.retailers.wx.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.DateUtil;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.wx.common.config.WxConfig;
import com.retailers.wx.common.dao.WxAccessTokenMapper;
import com.retailers.wx.common.dao.WxManagerMapper;
import com.retailers.wx.common.entity.WxAccessToken;
import com.retailers.wx.common.entity.WxManager;
import com.retailers.wx.common.service.WxAccessTokenService;
import com.retailers.wx.common.utils.WxReqUtils;
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
		WxManager wxManager= wxManagerMapper.queryCurUsedWx();
		if(ObjectUtils.isNotEmpty(wxManager)){
			//根据当前使用微信，取得token
			WxAccessToken token=wxAccessTokenMapper.queryCurWxAccessTokenByWxId(wxManager.getWxId(),curDate);
			//判断当前token是否为空 如果当前token 还处于有效期则不进行处理。
			if(ObjectUtils.isNotEmpty(token)){
				WxConfig.ACCESS_TOKEN=token.getWatToken();
			}else{
				//取得最新token
				String rtn = WxReqUtils.getToken(wxManager.getAppId(),wxManager.getAppSecret());
				if(ObjectUtils.isNotEmpty(rtn)){
					token = new WxAccessToken();
					JSONObject obj = JSONObject.parseObject(rtn);
					if(obj.containsKey("errcode")){

					}else{
						int expires=obj.getIntValue("expires_in");
						Date expiresTime= DateUtil.addSecond(curDate,(expires-60*10));
						token.setWatToken(obj.getString("access_token"));
						token.setWatTokenCreateTime(curDate);
						token.setWatTokenExpiresTime(expiresTime);
						token.setWatTokenExpires(expires);
						token.setWatWxId(wxManager.getWxId());
						WxConfig.ACCESS_TOKEN=token.getWatToken();
						wxAccessTokenMapper.saveWxAccessToken(token);
					}
				}
			}
		}else{
		}
	}
}

