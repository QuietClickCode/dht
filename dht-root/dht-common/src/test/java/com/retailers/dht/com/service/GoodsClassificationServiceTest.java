package com.retailers.dht.com.service;

import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.service.GoodsClassificationService;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.dht.common.vo.GoodsClassificationVo;
import com.retailers.mybatis.pagination.Pagination;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/9/27.
 */
public class GoodsClassificationServiceTest extends TestBaseJunit{

    @Autowired
    private GoodsClassificationService goodsClassificationService;

    @Test
    public void queryGoodsClassificationList(){
        List<GoodsClassificationVo> pagination1 = goodsClassificationService.queryGoodsClassificationListByParentId(1L);
        System.out.println(pagination1.get(0).getGgName());
        System.out.println(pagination1.get(1).getGgName());
        System.out.println(pagination1.size());
    }


}
