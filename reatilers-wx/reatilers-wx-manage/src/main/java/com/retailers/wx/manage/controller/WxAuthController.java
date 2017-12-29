package com.retailers.wx.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.retailers.tools.utils.HttpClientUtil;
import com.retailers.tools.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
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
     * 获得用户授权确认
     */
    @RequestMapping("queryUserAuth")
    public void queryUserAuth(HttpServletResponse response,HttpServletRequest request) throws IOException {
        String userAgent = request.getHeader("user-agent");
        String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa3da99ccbc1cafd0&redirect_uri="+
                URLEncoder.encode("http://www.kuaiyis.com/wxAuth/userLogin","utf-8")+"&response_type=code&scope=snsapi_userinfo&state=aabc";
        response.sendRedirect(url);
    }


    /**
     * 路由控制
     *
     * @param request
     * @return
     */
    @RequestMapping("userLogin")
    public void auth(HttpServletRequest request,String code) {
        System.out.println("------------------------------------------------->:"+code);
        Map<String,Object> parms = WebUtils.getParametersStartingWith(request,"");
        System.out.println(parms);
//        String code =WxConfig.ACCESS_TOKEN;
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
        try {
            String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
                    "wxa3da99ccbc1cafd0","e42aae6dd96dc71a3625cc8de331a003", code);
            logger.info("request accessToken from url: {}", url);
            String rtn = HttpClientUtil.doGet(url);
            object = JSONObject.parseObject(rtn);
            logger.info("request accessToken success. [result={}]", object);
            data.put("openid", object.get("openid").toString().replaceAll("\"", ""));
            data.put("access_token", object.get("access_token").toString().replaceAll("\"", ""));
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
    public Map<String, String> getUserInfo(String accessToken, String openId) {
        Map<String, String> data = new HashMap();
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
        logger.info("request user info from url: {}", url);
        try {
            String info = HttpClientUtil.doGet(url);;
            info = new String(info.getBytes("ISO-8859-1"), "UTF-8");
            System.out.println(info);
            JSONObject userInfo=JSONObject.parseObject(info);
            logger.info("get userinfo success. [result={}]", info);
            System.out.println(info);
            data.put("openid", userInfo.get("openid").toString().replaceAll("\"", ""));
            data.put("nickname", userInfo.get("nickname").toString().replaceAll("\"", ""));
            data.put("city", userInfo.get("city").toString().replaceAll("\"", ""));
            data.put("province", userInfo.get("province").toString().replaceAll("\"", ""));
            data.put("country", userInfo.get("country").toString().replaceAll("\"", ""));
            data.put("headimgurl", userInfo.get("headimgurl").toString().replaceAll("\"", ""));
        } catch (Exception ex) {
            logger.error("fail to request wechat user info. [error={}]", ex);
        }
        return data;
    }
}
