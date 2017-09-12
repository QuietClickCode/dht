package com.retailers.tools.encrypt;

import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2015/5/25.
 */
public class ParamKey {
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(DesKey.class.getResourceAsStream("/paramKey.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        return value;
    }
    public static String getPropertyParam(String pf,String uri) {
        uri = uri.replace("/","_");
        String key = pf + uri;
        String value = properties.getProperty(key);
        return value;
    }

    /**
     * des解密参数
     * @param param 参数
     * @return
     */
    public static Map<String, Object> decryptParams(Map<String, Object> param,String uri) throws Exception{
        for(String k: param.keySet()){
            //去掉空字符串
            if("".equals(param.get(k))){
                param.put(k, null);
            }
        }
        String field = ParamKey.getPropertyParam(PlatformContant.PF_WEB,uri);
        String value = null;
        if (field != null) {
            String[] f = field.split(",");
            for (int i = 0; i < f.length; i++) {
                value = (String) param.get(f[i]);
                param.put(f[i], DESUtils.decryptDES(value, DesKey.WEB_KEY));
            }
        }
        return param;
    }

}
