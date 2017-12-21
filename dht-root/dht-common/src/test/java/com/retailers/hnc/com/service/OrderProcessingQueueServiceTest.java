package com.retailers.hnc.com.service;

import com.retailers.hnc.com.base.TestBaseJunit;
import com.retailers.dht.common.entity.OrderProcessingQueue;
import com.retailers.dht.common.service.OrderProcessingQueueService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/15
 */
public class OrderProcessingQueueServiceTest extends TestBaseJunit {
    @Autowired
    OrderProcessingQueueService orderProcessingQueueService;

    @Test
    public void test(){
        OrderProcessingQueue opq=orderProcessingQueueService.queryOrderProcessingQueueById(1l);
        try{
            orderProcessingQueueService.test(opq);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
