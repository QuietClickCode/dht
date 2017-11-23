package com.retailers.dht.common.constant;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/23
 */
public class SmsSendRecordConstant {
    /**
     * 发送验证码类型 0 绑定手机号
     */
    public static final int SMS_SEND_TYPE_BIND_PHONE=0;
    /**
     * 发送验证码类型 1 密码找回
     */
    public static final int SMS_SEND_TYPE_FIND_PWD=1;
    /**
     * 发送验证码类型 2 登录
     */
    public static final int SMS_SEND_TYPE_LOGIN=2;
    /**
     * 当前 验证码所处状态 0 未使用
     */
    public static final int SMS_SEND_STAUTS_UN_USE=0;
    /**
     * 当前 验证码所处状态 1 己使用
     */
    public static final int SMS_SEND_STAUTS_USE=1;
    /**
     * 当前 验证码所处状态 2 己失效
     */
    public static final int SMS_SEND_STAUTS_VALID=2;
    /**
     * 当前 验证码所处状态 3 再次发送
     */
    public static final int SMS_SEND_STAUTS_ONCE_SEND=3;
}
