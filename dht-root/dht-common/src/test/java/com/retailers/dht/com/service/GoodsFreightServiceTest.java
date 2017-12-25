package com.retailers.dht.com.service;

import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.entity.GoodsFreight;
import com.retailers.dht.common.service.GoodsFreightService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by admin on 2017/9/27.
 */
public class GoodsFreightServiceTest extends TestBaseJunit {
    @Autowired
    private GoodsFreightService freightService;

    @Test
    public void queryFreightByAddressTest(){
        String address = "重庆市重庆市市辖区石柱县";
        GoodsFreight goodsFreight = freightService.queryFreightByAddress(address);
        System.out.println(goodsFreight.getGfPrice());
    }
}
