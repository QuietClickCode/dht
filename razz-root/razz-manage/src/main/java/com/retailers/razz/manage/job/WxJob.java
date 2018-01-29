package com.retailers.razz.manage.job;

import com.retailers.wx.common.entity.WxAccessToken;
import com.retailers.wx.common.service.WxAccessTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zaqhr on 2018/1/11.
 */
@Service("wxJob")
public class WxJob {
    @Autowired
    private WxAccessTokenService wxAccessTokenService;

    //获取发送消息的微信公众号的AccessToken
    public void getGZHAccessToken(){
        Logger logger = LoggerFactory.getLogger(WxJob.class);

        logger.info("开始获取公众号Access_Token");
//        String appid = HNCGZHConstant.APP_ID;//"wx53881ce6778c5e1f";
//        String appsecret = HNCGZHConstant.APP_SECRET;//"356ea90409ec9e34064e1c860f2bf667";
//        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appsecret;
//        String str = HttpClientUtil.doGet(url);
//        System.out.println(str);
//        JSONObject jsonObject = JSON.parseObject(str);
//        Map params = new HashMap();
//        List<WxAccessToken> wxAccessTokens = wxAccessTokenService.queryWxAccessTokenList(params,1,1).getData();
//        String accessToken = wxAccessTokens.get(0).getWatToken();
//        logger.info("Access_Token:"+accessToken);
//        HNCGZHConstant.ACCESS_TOKEN = accessToken;
        logger.info("获取公众号Access_Token完毕");
    }
}
