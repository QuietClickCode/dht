package com.retailers.dht.com.service;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.service.GoodsClassificationService;
import com.retailers.dht.common.service.GoodsLabelService;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.mybatis.pagination.Pagination;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/9/27.
 */
public class GoodsLabelServiceTest extends TestBaseJunit {
    @Autowired
    private GoodsLabelService goodsLabelService;

    @Test
    public void queryGoodsListsByGoodsLabel(){
        List<GoodsVo> goods = goodsLabelService.queryGoodsListsByGoodsLabel(19L,1,10);
        System.out.println(JSON.toJSONString(goods));
    }


}
