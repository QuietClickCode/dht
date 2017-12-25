package com.retailers.dht.com.service;

import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.service.GoodsGgsvalDetailService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/9/27.
 */
public class GoodsGgsvalDetailServiceTest extends TestBaseJunit {
    @Autowired
    private GoodsGgsvalDetailService goodsGgsvalDetailService;



    @Test
    public void editGoodsInventorysTest(){

        Map map = new HashMap();
        map.put(858L,-10000L);
        map.put(859L,-1L);
        try {
            boolean falg = goodsGgsvalDetailService.editGoodsInventorys(map);
            System.out.println(falg);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void addGoodsInventorysTest(){
//        GoodsDetail goodsDetail = new GoodsDetail();
//        goodsDetail.setGdId(858L);
//        goodsDetail.setGdResidueinventory(1L);
//        goodsDetail.setGdInventory(1L);
//        List<GoodsDetail> list = new ArrayList<GoodsDetail>();
//        list.add(goodsDetail);
//        boolean falg = goodsGgsvalDetailService.addGoodsInventorys(list);
//        System.out.println(falg);
    }
}
