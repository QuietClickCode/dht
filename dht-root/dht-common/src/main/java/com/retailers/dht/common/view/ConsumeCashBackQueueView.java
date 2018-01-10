package com.retailers.dht.common.view;

import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.Date;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2018/1/9
 */
public class ConsumeCashBackQueueView {
    /**icbqId*/
    private Long icbqId;
    /**返现用户*/
    private Long icbqUid;
    /**返现金额*/
    private Long icbqMoney;
    /**消耗积分*/
    private Long icbqUseIntegral;
    /**创建时间*/
    private Date icbqCreateTime;
    /**返现时间*/
    private Date icbqCashBackTime;
    /**等待时间*/
    private Long icbqWaitTime;


}
