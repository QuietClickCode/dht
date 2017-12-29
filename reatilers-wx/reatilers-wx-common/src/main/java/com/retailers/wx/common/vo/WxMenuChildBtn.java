package com.retailers.wx.common.vo;

/**
 * 微信菜单  二级菜单 配置信息
 * @author zhongp
 * @version  1.0.1
 */
public class WxMenuChildBtn {
    //菜单类型
    private String type;
    //菜单名称
    private String name;
    /**
     * 连接跳转地址  view、miniprogram类型必须
     * 网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url。
     */
    private String url;
    /**
     * click等点击类型必须  菜单KEY值，用于消息接口推送，不超过128字节
     */
    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
