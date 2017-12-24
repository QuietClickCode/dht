package com.retailers.hnc.com.service;

import com.retailers.hnc.com.base.TestBaseJunit;
import com.retailers.dht.common.service.GoodsImageService;
import com.retailers.dht.common.vo.GoodsImageVo;
import com.retailers.mybatis.pagination.Pagination;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/9/27.
 */
public class GoodsImageServiceTest extends TestBaseJunit {
    @Autowired
    private GoodsImageService goodsImageService;

    @Test
    public void queryGoodsImagesList(){
        Map params = new HashMap();
        params.put("gid",1);
        params.put("isDelete",0);
        Pagination<GoodsImageVo> pagination = goodsImageService.queryGoodsImageList(params,1,100);
        System.out.println(pagination.getData().size());
    }


}
