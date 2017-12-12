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
}
