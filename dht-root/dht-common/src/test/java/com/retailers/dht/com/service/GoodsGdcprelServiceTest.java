package com.retailers.dht.com.service;

import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.service.GoodsGdcprelService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/9/27.
 */
public class GoodsGdcprelServiceTest extends TestBaseJunit{

    @Autowired
    private GoodsGdcprelService goodsGdcprelService;

    @Test
    public void queryGoodsDetailByGdIds(){
        Map map = new HashMap();
        map.put(231L,1L);
        map.put(234L,1L);
        boolean falg = goodsGdcprelService.editGoodsInventorys(map);
        System.out.println(falg);
    }
}
