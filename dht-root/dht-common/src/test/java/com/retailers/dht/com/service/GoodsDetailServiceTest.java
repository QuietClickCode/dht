package com.retailers.dht.com.service;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.service.GoodsDetailService;
import com.retailers.dht.common.vo.GoodsDetailVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        System.out.println(JSON.parseArray(data).size());
        goodsDetailService.addMyData(data,51L,-1L);
    }

    @Test
    public void queryGoodsDetailVoList(){
        List<Map<String,Long>> list = new ArrayList<Map<String,Long>>();
        Map map1 = new HashMap();
        map1.put("id",858L);
        map1.put("type",0L);
        Map map2 = new HashMap();
        map2.put("id",855L);
        map2.put("type",0L);
        Map map3 = new HashMap();
        map3.put("id",380L);
        map3.put("type",2L);
        Map map4 = new HashMap();
        map4.put("id",232L);
        map4.put("type",1L);

        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);

        Map map = goodsDetailService.queryGoodsDetailVoList(list);
        System.out.println(JSON.toJSONString(map));
    }
}
