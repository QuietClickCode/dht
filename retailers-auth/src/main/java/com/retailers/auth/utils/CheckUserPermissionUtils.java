package com.retailers.auth.utils;

import com.retailers.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 校验用户权限
 */
public class CheckUserPermissionUtils {
    static Logger logger = LoggerFactory.getLogger(CheckUserPermissionUtils.class);
    //用户权限列表 (登录后缓存内存）
    public static Map<Long,Map<String,String>> permUrl=new HashMap<Long, Map<String, String>>();
    //耍要校验的url
    private static Map<String,String> checkUrl=new HashMap<String, String>();
    //url 开始字符串
    public static final String URL_START_CHART="/";
    //替换字符串
    public static final String URL_START_REPLACE="";

    /**
     * 校验当前用户是否有访问当前路径权限
     * @param userId 用户id
     * @param url 访问路径
     * @return
     */
    public static boolean checkPermission(Long userId,String url){
        logger.info("开始进入访问路径权限验证，用户id：{},校验url:{}",userId,url);
        if(permUrl.containsKey(userId)){
            url = StringUtils.replaceFirstChart(url,URL_START_CHART,URL_START_REPLACE);
            if(permUrl.get(userId).isEmpty()){
                return false;
            }
            if(permUrl.get(userId).containsKey(url)){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断当前请求的url 量澡校验权限
     * @param url
     * @return
     */
    public static boolean checkUrl(String url){
        if(url.equals(URL_START_CHART)){
            return false;
        }
        if(url.startsWith(URL_START_CHART)){
            url=url.substring(1);
        }
        url = StringUtils.replaceFirstChart(url,URL_START_CHART,URL_START_REPLACE);
        if(checkUrl.containsKey(url)){
            return true;
        }
        return false;
    }

    /**
     * 初始化权限校验的url
     * @param urls
     */
    public static void initCheckUrl(List<String> urls){
        checkUrl.clear();
        for(String url:urls){
            checkUrl.put(StringUtils.replaceFirstChart(url,URL_START_CHART,URL_START_REPLACE),url);
        }
    }
}
