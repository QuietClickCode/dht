package com.retailers.dht.com.service;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.dao.CashOrderMapper;
import com.retailers.dht.common.entity.CashOrder;
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
    @Autowired
    private CashOrderMapper cashOrderMapper;

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

    @Test
    public void queryCashOrderByCoId(){
        long ocId=1;
        CashOrder cashOrder=cashOrderMapper.queryCashOrderByCoId(ocId);
        System.out.println(JSON.toJSON(cashOrder));
    }

}
