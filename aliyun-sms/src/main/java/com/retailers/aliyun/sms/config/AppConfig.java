package com.retailers.aliyun.sms.config;

import java.io.IOException;
import java.util.Properties;

/**
 * 系统常量配置
 */
public class AppConfig {
    public static Properties properties;
    public static boolean IS_DEBUG;

    static {
        properties = new Properties();
        try {
            properties.load(AppConfig.class.getResourceAsStream("/appconfig.properties"));
            IS_DEBUG = Boolean.valueOf(properties.getProperty("system.is.debug"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
