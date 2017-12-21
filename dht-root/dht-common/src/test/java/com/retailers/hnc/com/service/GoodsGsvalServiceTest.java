package com.retailers.hnc.com.service;

import com.retailers.hnc.com.base.TestBaseJunit;
import com.retailers.dht.common.entity.GoodsGsval;
import com.retailers.dht.common.service.GoodsGsvalService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by admin on 2017/9/27.
 */
public class GoodsGsvalServiceTest extends TestBaseJunit {
    @Autowired
    private GoodsGsvalService goodsGsvalService;


    @Test
    public void updategoodsGsval(){
        GoodsGsval goodsGsval = new GoodsGsval();
        goodsGsval.setIsDelete(0L);
        goodsGsval.setGsId(1L);
        goodsGsval.setGsvId(19L);
        goodsGsval.setGsvVal("啊啊");
        goodsGsval.setVersion(goodsGsvalService.queryGoodsGsvalByGsvId(19L).getVersion());

        boolean flag = goodsGsvalService.updateGoodsGsval(goodsGsval);
        System.out.println(flag);
    }


}
