package com.retailers.hnc.com.service;

import com.retailers.hnc.com.base.TestBaseJunit;
import com.retailers.dht.common.service.BuyCarService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/9/27.
 */
public class BuyCarServiceTest extends TestBaseJunit {
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
    @Test
    public void updateBuyCatHadBuyTest(){
        List<Long> list = new ArrayList();
        list.add(24L);
        list.add(25L);
        list.add(26L);
        boolean goodsFreight = buyCarService.updateBuyCatHadBuy(list);
        System.out.println(goodsFreight);
    }
}
