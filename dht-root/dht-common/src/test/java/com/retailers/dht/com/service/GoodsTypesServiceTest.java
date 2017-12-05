package com.retailers.dht.com.service;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.vo.ZTreeVo;
import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.entity.GoodsType;
import com.retailers.dht.common.service.GoodsTypeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/5
 */
public class GoodsTypesServiceTest extends TestBaseJunit {
    @Autowired
    private GoodsTypeService goodsTypeService;

    @Test
    public void queryGoodsTypeTree(){
        long couponId=1l;
        List<ZTreeVo> lists = goodsTypeService.queryGoodsTypeTree(couponId);
        System.out.println(JSON.toJSON(lists));
    }

}
