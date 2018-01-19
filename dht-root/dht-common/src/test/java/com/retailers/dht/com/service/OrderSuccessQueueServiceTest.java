package com.retailers.dht.com.service;

import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.service.OrderSuccessQueueService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/25
 */
public class OrderSuccessQueueServiceTest extends TestBaseJunit {
    @Autowired
    private OrderSuccessQueueService orderSuccessQueueService;
    @Test
    public void executeOrderQueue()throws Exception{
        orderSuccessQueueService.executeOrderQueue();
        Thread.sleep(10000);
    }

    @Test
    public void test(){
        Map<Long,Long> maps=new HashMap<Long, Long>();
        maps.put(4l,6076501l);
        maps.put(5l,9673181l);
        long orderId=990012;
        orderSuccessQueueService.test(maps,orderId);
    }
}
