package com.retailers.dht.com.service;

import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.service.PayService;
import com.retailers.tools.exception.AppException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2018/2/2
 */
public class PayServiceTest extends TestBaseJunit {
    @Autowired
    PayService payService;


    @Test
    public void refundOrder()throws Exception{
        try{
            payService.refundOrder("zpaman12322","CZ171231211502015","4200000047201712315442968727",1l,1l);
        }catch(Exception e){
            e.printStackTrace();
        }
        Thread.sleep(10000);

    }


}
