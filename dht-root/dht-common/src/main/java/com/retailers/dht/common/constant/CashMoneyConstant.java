package com.retailers.dht.common.constant;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2018/2/28
 */
public class CashMoneyConstant {
    /**
     * 提现状态（0 待审核，1 拒绝，2 待下划，3 提现成功，4 提现失败）
     */
    public static long CASH_STATUS_CREATE=0;

    /**
     * 退款申请审核状态 1 拒绝
     */
    public static  long CASH_AUDITING_STATUS_FAILE=1;
    /**
     * 退款申请审核状态 2  待下划
     */
    public static  long CASH_AUDITING_STATUS_SUCCESS=2;
    /**
     * 退款申请审核状态 3 提现成功
     */
    public static  long CASH_STATUS_SUCCESS=3;
    /**
     * 退款申请审核状态 4 提现失败
     */
    public static  long CASH_STATUS_FAILE=4;

}
