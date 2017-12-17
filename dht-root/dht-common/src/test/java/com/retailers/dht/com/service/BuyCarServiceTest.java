package com.retailers.dht.com.service;

import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.entity.BuyCar;
import com.retailers.dht.common.entity.GoodsFreight;
import com.retailers.dht.common.service.BuyCarService;
import com.retailers.dht.common.service.GoodsFreightService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/9/27.
 */
public class BuyCarServiceTest extends TestBaseJunit{
    @Autowired
    private BuyCarService buyCarService;

    @Test
    public void queryFreightByAddressTest(){
        List list = new ArrayList();
        list.add(16);
        list.add(18);
        list.add(28);
        Map goodsFreight = buyCarService.queryInviterIdByBcIds(list);
        System.out.println(goodsFreight.size());
    }
}
