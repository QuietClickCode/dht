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
    //是否限制使用（0 无限制，1 指定商品种类，2 指定商品）
    /**
     * 优惠卷使用范围 0 不限制
     */
    public static final  int COUPON_USED_RANGE_ALL=0;
    /**
     * 优惠卷使用范围 1 指定商品种类
     */
    public static final  long COUPON_USED_RANGE_GOODS_TYPE=1;
    /**
     * 优惠卷使用范围 2 指定商品
     */
    public static final  long COUPON_USED_RANGE_GOODS=2;

    /**
     * 优惠使用条件 数量
     */
    public static final long GOODS_UNITS_NUM=1;
    /**
     * 优惠使用条件 金额
     */
    public static final long GOODS_UNITS_PRICE=0;
}
