
package com.retailers.dht.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.retailers.dht.common.dao.WxAuthUserMapper;
import com.retailers.dht.common.entity.WxAuthUser;
import com.retailers.dht.common.service.WxAuthUserService;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.HttpClientUtil;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import com.retailers.wx.common.config.WxConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：用户卡包操作日志（钱包，积分）Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-22 23:23:16
 */
@Service("wxauthuserService")
public class WxAuthUserServiceImpl implements WxAuthUserService {
	Logger logger = LoggerFactory.getLogger(WxAuthUserServiceImpl.class);
	@Autowired
	private WxAuthUserMapper wxAuthUserMapper;
	public boolean saveWxAuthUser(WxAuthUser wxAuthUser) {
		int status = wxAuthUserMapper.saveWxAuthUser(wxAuthUser);
		return status == 1 ? true : false;
	}
	public boolean updateWxAuthUser(WxAuthUser wxAuthUser) {
		int status = wxAuthUserMapper.updateWxAuthUser(wxAuthUser);
		return status == 1 ? true : false;
	}
	public WxAuthUser queryWxAuthUserByWauId(Long wauId) {
		return wxAuthUserMapper.queryWxAuthUserByWauId(wauId);
	}

	public Pagination<WxAuthUser> queryWxAuthUserList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<WxAuthUser> page = new Pagination<WxAuthUser>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<WxAuthUser> list = wxAuthUserMapper.queryWxAuthUserList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteWxAuthUserByWauId(Long wauId) {
		int status = wxAuthUserMapper.deleteWxAuthUserByWauId(wauId);
		return status == 1 ? true : false;
	}

	/**
	 *
	 * @param code
	 * @return
	 * @throws AppException
	 */
	public WxAuthUser queryWxAuthUser(String code,Long recommendId){
		//跟据code取得用户信息
		Map<String, String> result = getUserInfoAccessToken(code);//通过这个code获取access_token
		//判断是否取得用户信息成功
		if(result.containsKey("openid")){
			String openId = result.get("openid");
			if (ObjectUtils.isNotEmpty(openId)) {
				logger.info("try getting user info. [openid={}]", openId);
				WxAuthUser authUser= getUserInfo(result.get("access_token"), openId,recommendId);
				logger.info("received user info. [result={}]", authUser);
				return authUser;
			}
		}
		return null;
	}



	/**
	 * 获取请求用户信息的access_token
	 *
	 * @param code
	 * @return
	 */
	private Map<String, String> getUserInfoAccessToken(String code) {
		JSONObject object = null;
		Map<String, String> data = new HashMap();
		try {
			String url = String.format(WxConfig.AUTH2_GET_TOKEN_URL,WxConfig.APP_ID,WxConfig.APP_SECRET, code);
			logger.info("请求用户token的URL地址: {}", url);
			String rtn = HttpClientUtil.doGet(url);
			object = JSONObject.parseObject(rtn);
			if(object.containsKey("openid")){
				logger.info("request accessToken success. [result={}]", object);
				data.put("openid", object.get("openid").toString().replaceAll("\"", ""));
				data.put("access_token", object.get("access_token").toString().replaceAll("\"", ""));
			}
		} catch (Exception ex) {
			logger.error("fail to request wechat access token. [error={}]", ex);
		}
		return data;
	}

	/**
	 * 获取用户信息
	 *
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	private WxAuthUser getUserInfo(String accessToken, String openId,Long recommendId) {
		String url = String.format(WxConfig.OAUTH2_GET_USER_URL,accessToken,openId);
		logger.info("request user info from url: {}", url);
		logger.info("推荐人用户id--------------------->:[{}]",recommendId);
		try {
			String info = HttpClientUtil.doGet(url);
			//将取得的数据信息进行utf转码
			info = new String(info.getBytes("ISO-8859-1"), "UTF-8");
			logger.info("取得微信用户基本信息:{}",info);
			JSONObject userInfo=JSONObject.parseObject(info);
			boolean isAdd=false;
			//根据微信用户openid 判断该用户是否是己注册用户
			WxAuthUser wxAuthUser=wxAuthUserMapper.queryWxAuthUserByOpenId(1l,userInfo.get("openid").toString().replaceAll("\"", ""));
			if(ObjectUtils.isEmpty(wxAuthUser)){
				wxAuthUser=new WxAuthUser();
				isAdd=true;
				wxAuthUser.setWauCreateDate(new Date());
				wxAuthUser.setWauWxId(1l);
				//推荐人ID
				wxAuthUser.setWauRefereeId(recommendId);
			}
			wxAuthUser.setWauOpenid(userInfo.get("openid").toString().replaceAll("\"", ""));
			wxAuthUser.setWauNickname(userInfo.get("nickname").toString().replaceAll("\"", ""));
			wxAuthUser.setWauSex(userInfo.getInteger("sex"));
			wxAuthUser.setWauLanguage(userInfo.get("language").toString().replaceAll("\"", ""));
			wxAuthUser.setWauCity(userInfo.get("city").toString().replaceAll("\"", ""));
			wxAuthUser.setWauProvince(userInfo.get("province").toString().replaceAll("\"", ""));
			wxAuthUser.setWauCountry(userInfo.get("country").toString().replaceAll("\"", ""));
			wxAuthUser.setWauHeadimgurl(userInfo.get("headimgurl").toString().replaceAll("\"", ""));
			if(userInfo.containsKey("unionid")&&ObjectUtils.isNotEmpty(userInfo.get("unionid"))){
				wxAuthUser.setWauUnionid(userInfo.get("unionid").toString().replaceAll("\"", ""));
			}
			//判断是否关联用户
			if(ObjectUtils.isEmpty(wxAuthUser.getWauUid())){
				//根据uuid 取得相关用户
				Long uid=wxAuthUserMapper.queryRelationUserByUnionid(wxAuthUser.getWauUnionid(),wxAuthUser.getWauOpenid());
				if(ObjectUtils.isNotEmpty(uid)){
					wxAuthUser.setWauUid(uid);
				}
			}
			//判断是新增还是修改
			if(isAdd){
				wxAuthUserMapper.saveWxAuthUser(wxAuthUser);
			}else{
				wxAuthUserMapper.updateWxAuthUser(wxAuthUser);
			}
			return wxAuthUser;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.info(StringUtils.getErrorInfoFromException(ex));
		}
		return null;
	}
}

