package com.retailers.hnc.com.service;

import com.retailers.hnc.com.base.TestBaseJunit;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.service.GoodsClassificationService;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.mybatis.pagination.Pagination;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/9/27.
 */
public class GoodsServiceTest extends TestBaseJunit {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsClassificationService goodsClassificationService;

    @Test
    public void updateGoods(){
        Goods goods = goodsService.queryGoodsByGid(34L);
        goods.setGcopyid(16L);
        goodsService.updateGoods(goods,-1L);
    }

    @Test
    public void queryGoodsClassificationList(){
        Map map1 = new HashMap();
        map1.put("parentId",24);
        map1.put("isDelete",0L);
        Pagination<GoodsClassification> pagination1 = goodsClassificationService.queryGoodsClassificationList(map1,1,1000);
        System.out.println(pagination1.getData().get(0).getGgId());
    }


}
