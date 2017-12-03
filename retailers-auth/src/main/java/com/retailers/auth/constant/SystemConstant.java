package com.retailers.auth.constant;

/**
 * 系统常量
 */
public class SystemConstant {
    /**
     * 是否有效 0 有效
     */
    public static int SYS_IS_VALID_YES=0;
    /**
     * 是否有效 1 无效
     */
    public static int SYS_IS_VALID_NO=1;
    /**
     * 是否删除 否
     */
    public static int SYS_IS_DELETE_NO=0;
    /**
     * 是否删除 是
     */
    public static int SYS_IS_DELETE_YES=1;
    /**
     * 是否是默认 是
     */
    public static int SYS_IS_DEFAULT_YES=0;
    /**
     * 是否是默认 否
     */
    public static int SYS_IS_DEFAULT_NO=1;

    /**
     * 登录用户session 存入key
     */
    public static final String LOG_USER_SESSION_KEY="user";
    /**
     * 分享用户session 存入key
     */
    public static final String Share_USER_SESSION_KEY="shareUser";
    /**
     * 是否拉取得微信用户信息
     */
    public static final String IS_PULL_WX_USER_INFO="wxUser";
    /**
     * 后台职工默认密码
     */
    public static final String SYS_USER_DEFAULT_PASSWORD="123456";
    /**
     * 成功
     */
    public static final int SUCCESS=0;
    /**
     * 失败
     */
    public static final int FAIL=1;

}
