package com.retailers.dht.common.service.impl;

import com.retailers.dht.common.entity.OrderProcessingQueue;
import com.retailers.dht.common.service.AsyncService;
import com.retailers.dht.common.service.OrderProcessingQueueService;
import com.retailers.tools.utils.SpringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/19
 */
@Service("asyncServiceImpl")
public class AsyncServiceImpl implements AsyncService {
    /**
     * 异步执行
     * @param orderProcessingQueue
     */
    @Async
    @Transactional
    public void orderCallback(OrderProcessingQueue orderProcessingQueue) {
        OrderProcessingQueueService orderProcessingQueueService= SpringUtils.getBean("orderProcessingQueueService");
        orderProcessingQueueService.addProcessingQueueToHistory(orderProcessingQueue);
    }
}
