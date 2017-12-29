package com.retailers.mybatis.common.constant;

/**
 * 数据库常配置
 */
public class DBSystemConstant {

    /**
     * 服务器ip 在启动的时候取得服务器的ip
     */
    public static String SERVER_IP="";
    /**
     * 服务器端口
     */
    public static String SERVER_PORT="";

    /**
     *  重新加载系统参数至内存
     */
    public static String RELOAD_PARAMS_TO_MEMORY="/reaload/reloadParamsToMemory";
    /**
     *  重新加载微信配置至内存
     */
    public static String RELOAD_WX_CONFIG_TO_MEMORY="/reaload/reloadWxConfigToMemory";


    /**
     * 取得当前服务器信息（用于服务器重启之后进行队列的重新执行）
     * @return
     */
    public static String getCurServletInfo(){
        return SERVER_IP+":"+SERVER_PORT;
    }
    public static  String getUrl(String serverInfo,String url){
        return "http://"+serverInfo+url;
    }
}
