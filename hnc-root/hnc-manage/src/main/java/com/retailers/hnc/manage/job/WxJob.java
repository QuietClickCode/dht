package com.retailers.hnc.manage.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retailers.hnc.manage.controller.HNCGZHConstant;
import com.retailers.tools.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by zaqhr on 2018/1/11.
 */
@Service("wxJob")
public class WxJob {
    //获取发送消息的微信公众号的AccessToken
    public void getGZHAccessToken(){
        Logger logger = LoggerFactory.getLogger(WxJob.class);

        logger.info("开始获取公众号Access_Token");
        String appid = HNCGZHConstant.APP_ID;//"wx53881ce6778c5e1f";
        String appsecret = HNCGZHConstant.APP_SECRET;//"356ea90409ec9e34064e1c860f2bf667";
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appsecret;
        String str = HttpClientUtil.doGet(url);
        System.out.println(str);
        JSONObject jsonObject = JSON.parseObject(str);
        String accessToken = jsonObject.getString("access_token");
        HNCGZHConstant.ACCESS_TOKEN = accessToken;
        logger.info("获取公众号Access_Token完毕");
    }
}
