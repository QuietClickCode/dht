package com.retailers.hnc.web.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retailers.hnc.web.constant.WebSystemConstant;
import com.retailers.tools.utils.HttpClientUtil;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.wx.common.config.WxConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 后台管理定时任务
 * @author zhongp
 * @version 1.0.1
 * @data 2017-10-07
 */
@Service("mTaskJob")
public class ManageTaskJob {
    Logger logger = LoggerFactory.getLogger(ManageTaskJob.class);

    /**
     * 清除退出用户
     */
    public void clearLoginOutUser(){
        logger.info("开始清除退出用户");
        Map<String,Object> map = WebSystemConstant.globelOpenId;
        List<String> strArr = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Date date = (Date) entry.getValue();
            if(date.getTime()<System.currentTimeMillis()){
                strArr.add(key);
            }
        }
        if(ObjectUtils.isNotEmpty(strArr)){
            for(String key:strArr){
                map.remove(key);
            }
        }
        logger.info("清除退出用户完毕");
    }

    public void getAccessToken(){
        logger.info("开始获取Access_Token");
        String appid = WxConfig.APP_ID;//"wx53881ce6778c5e1f";
        String appsecret = WxConfig.APP_SECRET;//"356ea90409ec9e34064e1c860f2bf667";
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appsecret;
        String str = HttpClientUtil.doGet(url);
        System.out.println(str);
        JSONObject jsonObject = JSON.parseObject(str);
        String accessToken = jsonObject.getString("access_token");
        WxConfig.ACCESS_TOKEN = accessToken;
        logger.info("获取Access_Token完毕");
    }


}
