package com.retailers.dht.com.service;

import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.service.CashOrderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2018/2/28
 */
public class CashOrderServiceTest extends TestBaseJunit {
    @Autowired
    private CashOrderService cashOrderService;

    @Test
    public void userCashMoney()throws Exception{
        long uid=1623487;
        long money=1000;
        String remark="提现测试";
        try{
            cashOrderService.userCashMoney(uid,money,remark);
        }catch(Exception e){
            e.printStackTrace();
        }
        Thread.sleep(2000);
    }

}
