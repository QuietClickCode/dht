package com.retailers.wx.common.utils;

import com.retailers.wx.common.service.WxAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 初始化微信token
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/31
 */
public class WxSystemInit {

    @Autowired
    private WxAccessTokenService accessTokenService;

    /**
     * 加载微信token
     */
    public void initWxToken(){
        accessTokenService.initWxToken();
    }
}
