package com.retailers.dht.common.service;

import com.retailers.dht.common.entity.OrderProcessingQueue;
import com.retailers.dht.common.entity.OrderSuccessQueue;

/**
 * 系统异常执行方法
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/19
 */
public interface AsyncService {
    /**
     * 订单回调异步处理
     * @param orderProcessingQueue
     */
    public void orderCallback(OrderProcessingQueue orderProcessingQueue);

    /**
     * 编辑执行队列
     * @param osq
     * @param isSuccess
     */
    public void editorOrderSuccessQueue(OrderSuccessQueue osq,boolean isSuccess);

}
