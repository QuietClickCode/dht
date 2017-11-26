package com.retailers.wx.common.enm;

/**
 * 微信应用授权作用域枚举
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/26
 */
public enum ScopeEnum {
    /** 不弹出授权页面，直接跳转，只能获取用户openid*/
    snsapi_base,
    /** 弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，
     * 即使在未关注的情况下，只要用户授权，也能获取其信息*/
    snsapi_userinfo
}
