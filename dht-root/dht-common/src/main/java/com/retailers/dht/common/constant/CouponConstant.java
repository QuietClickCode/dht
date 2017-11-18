package com.retailers.dht.common.constant;

/**
 * 商品优惠常量
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/9
 */
public class CouponConstant {
    /**
     * 优惠卷金额类型 0 固定金额
     */
    public static final int CP_COIN_TYPE_FIXED =0;
    /**
     * 优惠卷金额类型 1 随机金额
     */
    public static final int CP_COIN_TYPE_RAND=1;

    /**
     * 商品优惠活动类型 代金卷
     */
    public static final int GCP_TYPE_MONEY=0;
    /**
     * 商品优惠活动类型 折扣卷
     */
    public static final int GCP_TYPE_DISCOUNT=1;
    /**
     * 商品优惠活动类型 包邮
     */
    public static final int GCP_TYPE_FREE_SHIPPING=2;
}
