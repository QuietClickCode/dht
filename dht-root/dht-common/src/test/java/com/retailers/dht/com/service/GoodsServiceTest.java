package com.retailers.dht.com.service;

import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.dao.GoodsMapper;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.service.GoodsBrandService;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.dht.common.vo.GoodsBrandVo;
import com.retailers.mybatis.pagination.Pagination;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/9/27.
 */
public class GoodsServiceTest extends TestBaseJunit{
    @Autowired
    private GoodsService goodsService;


    @Test
    public void updateGoods(){
        Goods goods = goodsService.queryGoodsByGid(34L);
        goods.setGcopyid(16L);
        goodsService.updateGoods(goods,-1L);
    }


}
