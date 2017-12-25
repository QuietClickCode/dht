package com.retailers.dht.com.service;

import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.service.GoodsBrandService;
import com.retailers.dht.common.vo.GoodsBrandVo;
import com.retailers.mybatis.pagination.Pagination;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/9/27.
 */
public class GoodsBrandServiceTest extends TestBaseJunit {
    @Autowired
    private GoodsBrandService goodsBrandService;

    @Test
    public void queryGoodsBrandList(){
        Map params = new HashMap();
        params.put("gbId",20);
        Pagination<GoodsBrandVo> pagination = goodsBrandService.queryGoodsBrandList(params,1,100);
        System.out.println(pagination.getData().size());
    }


}
