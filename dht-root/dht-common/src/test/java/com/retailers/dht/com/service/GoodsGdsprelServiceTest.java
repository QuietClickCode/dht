package com.retailers.dht.com.service;

import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.entity.GoodsDetail;
import com.retailers.dht.common.entity.GoodsGdsprel;
import com.retailers.dht.common.service.GoodsDetailService;
import com.retailers.dht.common.service.GoodsGdsprelService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/9/27.
 */
public class GoodsGdsprelServiceTest extends TestBaseJunit{
    @Autowired
    private GoodsGdsprelService goodsGdsprelService;

    @Test
    public void queryGoodsDetailByGdIds(){
        Map map = new HashMap();
        map.put(326L,-1000L);
        boolean falg = goodsGdsprelService.editGoodsInventorys(map);
        System.out.println(falg);
    }


}
