package com.retailers.dht.common.constant;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/14
 */
public class OrderProcessingQueueConstant {
    /**
     * 订单处理类型（0 修改订单状态，1 支付回调，2 退款状态）
     */
    public static final long ORDER_QUEUE_TYPE_UPDATE=0;
    /**
     * 订单处理类型（0 修改订单状态，1 支付回调，2 退款状态）
     */
    public static final long ORDER_QUEUE_TYPE_PAY_CALLBACK=1;
    /**
     * 订单处理类型（0 修改订单状态，1 支付回调，2 退款状态）
     */
    public static final long ORDER_QUEUE_TYPE_REFUND=2;
    /**
     * 执行状态（0 未执行，1 执行失败，2执行成功）
     */
    public static final long ORDER_EXECUTE_STATUS_UN=0;
    /**
     * 执行状态（0 未执行，1 执行失败，2执行成功）
     */
    public static final long ORDER_EXECUTE_STATUS_FAILE=1;
    /**
     * 执行状态（0 未执行，1 执行失败，2执行成功）
     */
    public static final long ORDER_EXECUTE_STATUS_SUCCESS=2;


}
