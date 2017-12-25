package com.retailers.hnc.web.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 移动端 常量
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/9
 */
public class WebSystemConstant {
    //web端请求类型 p pc电脑端，m 移动端
    public static final String WEB_REQ_TYPE="WEB_REQ_TYPE";
    //全局变量用来存储OPENID key为openid val为过期时间
    public static Map<String,Object> globelOpenId = new HashMap<String,Object>();
}
