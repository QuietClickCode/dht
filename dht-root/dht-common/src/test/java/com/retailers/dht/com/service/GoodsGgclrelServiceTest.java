package com.retailers.dht.com.service;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.service.GoodsGgclrelService;
import com.retailers.dht.common.service.GoodsGgcrelService;
import com.retailers.dht.common.vo.GoodsGgclrelVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by admin on 2017/9/27.
 */
public class GoodsGgclrelServiceTest extends TestBaseJunit {
    @Autowired
    private GoodsGgclrelService goodsGgclrelService;



    @Test
    public void queryGclassGoodsGgcrelLists(){
        Object a =  goodsGgclrelService.queryGclassGoodsGgclrelLists(66L);
        System.out.println(JSON.toJSONString(a));

    }


}
