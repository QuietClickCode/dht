package com.retailers.dht.com.service;

import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.entity.GoodsCoupon;
import com.retailers.dht.common.service.CouponService;
import com.retailers.dht.common.service.GoodsCouponService;
import com.retailers.dht.common.vo.CouponShowVo;
import com.retailers.mybatis.pagination.Pagination;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/14
 */
public class CouponServiceTest extends TestBaseJunit {

    @Autowired
    private GoodsCouponService goodsCouponService;

    @Test
    public void test()throws Exception{
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("isDelete",0);
        params.put("nowDate",new Date());
        Pagination<GoodsCoupon> pages= goodsCouponService.queryGoodsCouponList(params,1,100);
        System.out.println(pages.getData().size());
    }
}
