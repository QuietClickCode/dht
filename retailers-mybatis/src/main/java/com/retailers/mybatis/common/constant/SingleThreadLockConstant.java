package com.retailers.mybatis.common.constant;

/**
 * 单线程锁 常量
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/23
 */
public class SingleThreadLockConstant {
    /**
     * 短信发送单线程锁
     */
    public static  final String SEND_MSG_CODE="sendMsgCode";
    /**
     * 支付锁
     */
    public static  final String PAY_LOCK="payLock";
    /**
     * 取得微信token
     */
    public static final String PULL_WX_TOKEN="pullWxToken";
    /**
     * 用户购买商品
     */
    public static final String USER_BUY_GOODS="buyGoods";
    /**
     * 钱包支付 订单
     */
    public static final String WALLET_ORDER_PAY="walletPay";
    /**
     * 确认交易订单
     */
    public static final String CONFIRM_TRADE_ORDER="confirmOrder";
    /**
     * 清除超时订单
     */
    public static final String CLEAR_EXPIRE_ORDER="clearExpireOrder";
    /**
     * 审核退款订单
     */
    public static final String AUDITING_REFUND_ORDER="auditingRefund";
    /**
     * 退款
     */
    public static final String REFUND="refund";
    /**
     * 用户提现锁
     */
    public static final String USER_CASH_MONEY="cashMoney";
    /**
     * 用户提现审核
     */
    public static final String AUDITING_CASH_MONEY="auditingCashMoney";
    /**
     * 用户提现 打款
     */
    public static final String CASH_PLAY_MONEY="playMoney";



}
