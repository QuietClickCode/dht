package com.retailers.wx.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import com.retailers.wx.common.config.WxConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信授权接口
 * @author zhongp
 * @version 1.0.1
 */
@RestController
@RequestMapping("wxAuth")
public class WxAuthController {
    Logger logger = LoggerFactory.getLogger(WxAuthController.class);

    /**
     * 路由控制
     *
     * @param request
     * @param code
     * @return
     */
    public void auth(HttpServletRequest request,String code) {
        Map<String, String> data = new HashMap();
        Map<String, String> result = getUserInfoAccessToken(code);//通过这个code获取access_token
        String openId = result.get("openid");
        if (ObjectUtils.isNotEmpty(openId)) {
            logger.info("try getting user info. [openid={}]", openId);
            Map<String, String> userInfo = getUserInfo(result.get("access_token"), openId);//使用access_token获取用户信息
            logger.info("received user info. [result={}]", userInfo);
//            return forward("auth", userInfo);
        }
//        return Response.ok("openid为空").build();
    }


    /**
     * 获取请求用户信息的access_token
     *
     * @param code
     * @return
     */
    public Map<String, String> getUserInfoAccessToken(String code) {
        JSONObject object = null;
        Map<String, String> data = new HashMap();
//        try {
//            String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
//                    WxConfig.APP_ID,WxConfig.APP_SECRET, code);
//            logger.info("request accessToken from url: {}", url);
//            DefaultHttpClient httpClient = new DefaultHttpClient();
//            HttpGet httpGet = new HttpGet(url);
//            HttpResponse httpResponse = httpClient.execute(httpGet);
//            HttpEntity httpEntity = httpResponse.getEntity();
//            String tokens = EntityUtils.toString(httpEntity, "utf-8");
//            object = JSONObject.parseObject(tokens);
//            logger.info("request accessToken success. [result={}]", object);
//            data.put("openid", object.get("openid").toString().replaceAll("\"", ""));
//            data.put("access_token", object.get("access_token").toString().replaceAll("\"", ""));
//        } catch (Exception ex) {
//            logger.error("fail to request wechat access token. [error={}]", ex);
//        }
        return data;
    }


    /**
     * 获取用户信息
     *
     * @param accessToken
     * @param openId
     * @return
     */
    public Map<String, String> getUserInfo(String accessToken, String openId) {
        Map<String, String> data = new HashMap();
//        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
//        logger.info("request user info from url: {}", url);
//        JsonObject userInfo = null;
//        try {
//            DefaultHttpClient httpClient = new DefaultHttpClient();
//            HttpGet httpGet = new HttpGet(url);
//            HttpResponse httpResponse = httpClient.execute(httpGet);
//            HttpEntity httpEntity = httpResponse.getEntity();
//            String response = EntityUtils.toString(httpEntity, "utf-8");
//            Gson token_gson = new Gson();
//            userInfo = token_gson.fromJson(response, JsonObject.class);
//            logger.info("get userinfo success. [result={}]", userInfo);
//            data.put("openid", userInfo.get("openid").toString().replaceAll("\"", ""));
//            data.put("nickname", userInfo.get("nickname").toString().replaceAll("\"", ""));
//            data.put("city", userInfo.get("city").toString().replaceAll("\"", ""));
//            data.put("province", userInfo.get("province").toString().replaceAll("\"", ""));
//            data.put("country", userInfo.get("country").toString().replaceAll("\"", ""));
//            data.put("headimgurl", userInfo.get("headimgurl").toString().replaceAll("\"", ""));
//        } catch (Exception ex) {
//            logger.error("fail to request wechat user info. [error={}]", ex);
//        }
        return data;
    }
}
