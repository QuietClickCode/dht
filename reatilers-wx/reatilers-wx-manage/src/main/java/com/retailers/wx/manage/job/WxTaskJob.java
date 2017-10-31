package com.retailers.wx.manage.job;

import com.retailers.wx.common.service.WxAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信定时任务
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/31
 */
@Service("wxTaskJob")
public class WxTaskJob {

    @Autowired
    private  WxAccessTokenService accessTokenService;
    /**
     * 取得微信token
     */
    public void queryWxToken(){
        accessTokenService.initWxToken();
    }
}
