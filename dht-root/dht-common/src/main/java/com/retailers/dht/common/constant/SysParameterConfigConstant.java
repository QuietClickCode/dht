package com.retailers.dht.common.constant;

import com.alibaba.fastjson.util.TypeUtils;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统参数配置表 key
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/8
 */
public class SysParameterConfigConstant {
    //附件服务器地址
    public static final String ATTACHMENT_SERVER_ADDRESS="ATTACHMENT_SERVER_ADDRESS";
    //系统 参数对应的key值
    public static Map<String,Object> params=new HashMap<String, Object>();

    public static String getValue(String key){
        return params.get(key).toString();
    }

    public static <T> T getValue(String key,Class<T> clazz){
        Object obj = params.get(key);
        return TypeUtils.castToJavaBean(obj, clazz);
    }

    public static void main(String[] args) {
        params.put("zpaman",true);
        params.put("zpaman1","1");
        params.put("zpaman2",1.2);
        params.put("zpaman3",2.3);
        params.put("zpaman4","true");
        boolean flag = getValue("zpaman",Boolean.class);
        long num = getValue("zpaman1",Long.class);
        double d = getValue("zpaman2",Double.class);
        boolean q = getValue("zpaman4",Boolean.class);
        System.out.println(flag);
        System.out.println(num);
        System.out.println(d);
        System.out.println(q);
    }
}
