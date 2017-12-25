package com.retailers.hnc.com.service;

import com.retailers.dht.common.service.OrderSuccessQueueService;
import com.retailers.hnc.com.base.TestBaseJunit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/25
 */
public class OrderSuccessQueueServiceTest extends TestBaseJunit {
    @Autowired
    private OrderSuccessQueueService orderSuccessQueueService;
    @Test
    public void executeOrderQueue(){
        orderSuccessQueueService.executeOrderQueue();
    }
}
