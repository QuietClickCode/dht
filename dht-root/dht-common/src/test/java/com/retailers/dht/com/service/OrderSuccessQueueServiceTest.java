package com.retailers.dht.com.service;

import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.service.OrderSuccessQueueService;
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
