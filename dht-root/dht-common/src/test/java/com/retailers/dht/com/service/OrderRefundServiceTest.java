package com.retailers.dht.com.service;

import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.entity.OrderRefund;
import com.retailers.dht.common.service.OrderRefundService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2018/1/30
 */
public class OrderRefundServiceTest extends TestBaseJunit {

    @Autowired
    private OrderRefundService orderRefundService;


    @Test
    public void createRefund()throws Exception{
        long uid=1559994;
        long orderId=319;
        String remark="退款测试";
        orderRefundService.createRefund(uid,orderId,remark);
        Thread.sleep(10000);
    }
}
