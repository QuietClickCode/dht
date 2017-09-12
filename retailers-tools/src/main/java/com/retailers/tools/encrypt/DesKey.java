package com.retailers.tools.encrypt;

import java.util.Properties;

/**
 * 接口key
 */
public class DesKey {
    private static Properties properties;
    //参数字段加密Key
    public static String WEB_KEY = "";
    //数据字段加密key
    public static String WEB_FIELD_KEY = "";

    static {
        properties = new Properties();
        try {
            properties.load(DesKey.class.getResourceAsStream("/desKey.properties"));
            WEB_KEY = properties.getProperty("web_key");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
