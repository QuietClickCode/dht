package com.retailers.hnc.com.service;

import com.alibaba.fastjson.JSON;
import com.retailers.hnc.com.base.TestBaseJunit;
import com.retailers.dht.common.service.GoodsDetailService;
import com.retailers.dht.common.vo.GoodsDetailVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by admin on 2017/9/27.
 */
public class GoodsDetailServiceTest extends TestBaseJunit {
    @Autowired
    private GoodsDetailService goodsDetailService;

    @Test
    public void queryGoodsDetailByGdIds(){
        List<GoodsDetailVo> list = goodsDetailService.queryGoodsDetailByGdIds("857,858");
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void addMyDataTest(){
        String data = "[{\"gsid\":\"11_12\",\"gsvalid\":\"28_32\",\"gdImgid\":-1,\"gdResidueinventory\":1,\"gdInventory\":1,\"gdCostprice\":100,\"gdPrice\":100},{\"gsid\":\"11_12\",\"gsvalid\":\"28_34\",\"gdImgid\":-1,\"gdResidueinventory\":1,\"gdInventory\":1,\"gdCostprice\":100,\"gdPrice\":100},{\"gsid\":\"11_12\",\"gsvalid\":\"28_33\",\"gdImgid\":-1,\"gdResidueinventory\":1,\"gdInventory\":1,\"gdCostprice\":100,\"gdPrice\":100}]";
        goodsDetailService.addMyData(data,51L,-1L);

    }
}
