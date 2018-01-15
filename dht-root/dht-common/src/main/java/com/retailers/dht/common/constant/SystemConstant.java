package com.retailers.dht.common.constant;

import com.retailers.dht.common.entity.OrderProcessingQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 大汇堂系统 常量配置
 */
public class SystemConstant {
//    /**
//     * 平台支付方式 0 微信
//     */
//    public static final  int PLATFORM_PAY_WAY_WX=0;
//    /**
//     * 平台支付方式 1 支付宝
//     */
//    public static final  int PLATFORM_PAY_WAY_ALIPY=1;
//    /**
//     * 平台支付方式 2 钱包支付
//     */
//    public static final int PALTFORM_PAY_WAY_WALLET=2;

    /**
     * 微信支付方式 1 公众号
     */
    public static final int WX_PAY_WAY_GZH=1;
    /**
     * 微信支付方式 2 扫码
     */
    public static final int WX_PAY_WAY_SM=2;
    /**
     * 微信支付方式 3 h5
     */
    public static final int WX_PAY_WAY_H5=3;
    /**
     * 成功
     */
    public static final int EXECUTE_SUCCESS=0;
    /**
     * 失败
     */
    public static final int EXECUTE_FAIL=1;
    /**
     * 商品售卖方（平台自己出售)
     */
    public static final long GOODS_SHOP_USER=1;
    /**
     * 系统默认值
     */
    public static final long SYSTEM_DEFAULT_LONG_VAL=0;

    /**
     * 默认字符编码集
     */
    public static final String DEFAUT_CHARSET="UTF-8";

    /**
     * 微信访问页面授权原始请求地址
     */
    public static final String WX_ACCESS_ADDRESS_AUTH_URL="wxAccessAddressAuthUrl";

    /**
     * 验证码
     */
    public static final String LOGIN_VALIDATE_CODE="VALIDATE_CODE";
    /**
     * 默认金额
     */
    public static final long DEFAULT_PRICE =0;

    /**
     * 压缩类型（ small 小图，middle 中图 ，originalfile 原图)
     */
    public static List<String> compressTypes=new ArrayList<String>();
    /**
     * 用户是旧平台用户（未变更密码）
     */
    public static final int USER_IS_OLD_YES=1;
    /**
     * 用户是新平台用户（新的加密方式）
     */
    public static final int USER_IS_OLD_NO=0;
    /**
     * 平台返现队例状态 0 排队中
     */
    public static final long PLAT_CASH_BACK_MENOY_STATUS_LINE_UP=0;
    /**
     * 平台返现队例状态 1 筹款中(排到自己返现）
     */
    public static final long PLAT_CASH_BACK_MENOY_STATUS_LINE_FUNDRAISING=1;
    /**
     * 平台返现队例状态 2 己返现
     */
    public static final long PLAT_CASH_BACK_MENOY_STATUS_END=2;


    /**
     * 当前 平台消费统计类型 0 直接返现
     */
    public static final long CURRENT_PLATFORM_SALES_TYPE_CASH=0;
    /**
     * 当前 平台消费统计类型 1 累计消费统计 按类分
     */
    public static final long CURRENT_PLATFORM_SALES_TYPE_GT_SALES_TOTAL=1;
    /**
     * 当前 平台消费统计类型 2 累计消费统计 总金额
     */
    public static final long CURRENT_PLATFORM_SALES_TYPE_SALES_TOTAL=2;
    /**
     * java 队列
     */
    private static Queue<OrderProcessingQueue> queue = new LinkedList<OrderProcessingQueue>();

    public static void addOrderQueue(OrderProcessingQueue opq){
        queue.add(opq);
    }

    public static OrderProcessingQueue executeQueue(){
        OrderProcessingQueue opq=queue.poll();
        return opq;
    }

    static {
        compressTypes.add("small");
        compressTypes.add("middle");
        compressTypes.add("originalfile");
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            System.out.println(executeQueue());
        }
    }
}
