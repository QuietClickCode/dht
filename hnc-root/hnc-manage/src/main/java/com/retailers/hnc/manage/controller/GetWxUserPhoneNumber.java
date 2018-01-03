package com.retailers.hnc.manage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retailers.hnc.manage.base.BaseController;
import com.retailers.hnc.common.util.MyHttpUrlConnection;
import com.retailers.tools.utils.Base64;
import com.retailers.wx.common.config.WxConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by niconiconi on 2018/1/3.
 */
@Controller
@RequestMapping("test")
public class GetWxUserPhoneNumber extends BaseController {
    @RequestMapping("/getWxUserPhoneNumber")
    @ResponseBody
    public HashMap<String,Object> getWxUserPhoneNumber(String code,String encData,String iv) {
        MyHttpUrlConnection connection = new MyHttpUrlConnection();
        HashMap<String,Object> map = new HashMap<String,Object>();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx53881ce6778c5e1f&secret="+ WxConfig.APP_SECRET+"&js_code="
                + code + "&grant_type=authorization_code";
        String res[] = connection.requestJson(url);
        JSONObject object = JSON.parseObject(res[0]);
        String openId = object.getString("openid");
        String session_key = object.getString("session_key");
        byte[] encrypData = Base64.decode(encData);
        byte[] ivDta = Base64.decode(iv);
        byte[] sessionKey = Base64.decode(session_key);
        byte[] userInfo = null;
        try {
            userInfo = MyHttpUrlConnection.decrypt(sessionKey,ivDta,encrypData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("userInfo",new String(userInfo).toString());
        return map;
    }
}
