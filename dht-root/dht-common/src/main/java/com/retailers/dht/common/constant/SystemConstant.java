package com.retailers.dht.common.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 大汇堂系统 常量配置
 */
public class SystemConstant {
    /**
     * 平台支付方式 0 微信
     */
    public static final  int PLATFORM_PAY_WAY_WX=0;
    /**
     * 平台支付方式 1 支付宝
     */
    public static final  int PLATFORM_PAY_WAY_ALIPY=1;
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
     * 压缩类型（ small 小图，middle 中图 ，originalfile 原图)
     */
    public static List<String> compressTypes=new ArrayList<String>();

    static {
        compressTypes.add("small");
        compressTypes.add("middle");
        compressTypes.add("originalfile");
    }
}
