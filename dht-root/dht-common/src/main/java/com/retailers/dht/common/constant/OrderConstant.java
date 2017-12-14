package com.retailers.dht.common.constant;

/**
 * Created by admin on 2017/11/21.
 */
public class OrderConstant {
    /**
     * 订单状态 0 创建（未支付）
     */
    public static final int ORDER_STATUS_CREATE=0;
    /**
     * 订单状态(0 未支付，1 支付中，2 支付失败，3 支付成功/待发货，4 己发货，5 确认收货，6 起发退款 ，9 交易完成（收货后15天关闭交易，不能进行退款申请））
     */
    public static final int ORDER_STATUS_PAY_FAILE=2;
    /**
     * 订单状态(0 未支付，1 支付中，2 支付失败，3 支付成功/待发货，4 己发货，5 确认收货，6 起发退款 ，9 交易完成（收货后15天关闭交易，不能进行退款申请））
     */
    public static final int ORDER_STATUS_PAY_SUCCESS=3;

    /**
     * 是否是真实单子 0 真实
     */
    public static final int ORDER_IS_REAL_YES=0;
    /**
     * 是否是真实单子 1 虚拟
     */
    public static final int ORDER_IS_REAL_NO=1;

    /**
     * 订单退款状态 0 未申请
     */
    public static final int ORDER_REFUND_STATUS_UN=0;
    /**
     *订单退款状态 1 申请中
     */
    public static final int ORDER_REFUND_STATUS_APPLY=1;
    /**
     * 订单退款状态 2 驳回
     */
    public static final int ORDER_REFUND_STATUS_REJECT=2;
    /**
     * 订单退款状态 3 成功
     */
    public static final int ORDER_REFUND_STATUS_ADOPT=3;

    /**
     * 购物订单返还方式 积分（0 消费积分，1 消费返现）
     */
    public static final int ORDER_RETURN_TYPE_INTEGRAL=0;
    /**
     * 购物订单返还方式 返现（0 消费积分，1 消费返现）
     */
    public static final int ORDER_RETURN_TYPE_CASH=1;

}
