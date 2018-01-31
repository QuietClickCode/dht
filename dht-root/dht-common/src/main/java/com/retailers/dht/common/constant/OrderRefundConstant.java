package com.retailers.dht.common.constant;

/**
 * 退款订单常量配置
 * @author zhongp
 * @version 1.0.1
 * @data 2018/1/31
 */
public class OrderRefundConstant {
    /**
     * 退款申请审核状态 0 待审核
     */
    public static  int REFUND_AUDITING_STATUS_CREATE=0;

    /**
     * 退款申请审核状态 1 审核失败
     */
    public static  int REFUND_AUDITING_STATUS_FAILE=1;
    /**
     * 退款申请审核状态 2  审核成功
     */
    public static  int REFUND_AUDITING_STATUS_SUCCESS=2;
    /**
     * 退款申请审核状态 3 发起退款
     */
    public static  int REFUND_AUDITING_STATUS_REFUND=3;
    /**
     * 退款申请审核状态 4 退款成功
     */
    public static  int REFUND_AUDITING_STATUS_REFUND_SUCCESS=4;
    /**
     * 退款申请审核状态 5 退款失败
     */
    public static  int REFUND_AUDITING_STATUS_REFUND_FAILE=5;
}
