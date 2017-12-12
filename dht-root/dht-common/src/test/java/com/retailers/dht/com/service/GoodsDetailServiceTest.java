package com.retailers.dht.com.service;

import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.entity.GoodsDetail;
import com.retailers.dht.common.service.GoodsClassificationService;
import com.retailers.dht.common.service.GoodsDetailService;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.mybatis.pagination.Pagination;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/9/27.
 */
public class GoodsDetailServiceTest extends TestBaseJunit{
    @Autowired
    private GoodsDetailService goodsDetailService;

    @Test
    public void queryGoodsDetailByGdIds(){
        List<GoodsDetail> list = goodsDetailService.queryGoodsDetailByGdIds("806,809");
        System.out.println(list.size());
        System.out.println(list.get(0).getGdPrice());
    }


}
