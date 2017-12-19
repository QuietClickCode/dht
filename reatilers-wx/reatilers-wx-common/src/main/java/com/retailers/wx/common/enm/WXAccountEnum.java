package com.retailers.wx.common.enm;

import com.retailers.mybatis.common.enm.OrderEnum;

/**
 * 微信公众号类型
 * Created by admin on 2017/12/19.
 */
public enum  WXAccountEnum {
    //用于微信取得用户信息
    WX_GZH("WXGZH","微信公众号登陆"),
    WX_PC("WXPC","微信网页登陆");
    private String type;
    private String illustrate;

    WXAccountEnum(String type,String illustrate){
        this.type=type;
        this.illustrate=illustrate;
    }

    /**
     * 取得登陆类型
     * @param type 类型
     * @return
     */
    public static WXAccountEnum getLoginType(String type) {
        WXAccountEnum[] typeEnumArray = WXAccountEnum.values();
        for (WXAccountEnum typeEnum : typeEnumArray) {
            if (type.equals(typeEnum.getType())) {
                return typeEnum;
            }
        }
        return null;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIllustrate() {
        return illustrate;
    }

    public void setIllustrate(String illustrate) {
        this.illustrate = illustrate;
    }
}
