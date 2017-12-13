package com.retailers.dht.com.service;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.service.GoodsCouponService;
import com.retailers.dht.common.vo.GoodsCouponShowVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/9
 */
public class GoodsCouponServiceTest extends TestBaseJunit {

    @Autowired
    private GoodsCouponService goodsCouponService;
    /**
     * 取得商品关联的优惠
     */
    @Test
    public void  queryGoodsCouponByGid()throws Exception{
        long goodsIds=40;
        List<GoodsCouponShowVo> lists = goodsCouponService.queryGoodsCouponByGid(goodsIds);
        System.out.println(JSON.toJSON(lists));
    }
    /**
     * 取得商品关联的优惠
     */
    @Test
    public void  checkGoodsCoupon()throws Exception{
        Map<Long,List<Long>> data=new HashMap<Long, List<Long>>();
        data.put(57l,null);
        data.put(1l,null);
        data.put(56l,null);
        data.put(4l,null);
        data.put(51l,null);
        data.put(52l,null);
        data.put(53l,null);
        data.put(54l,null);
        data.put(55l,null);
        data.put(27l,null);
        data.put(40l,null);
        goodsCouponService.checkGoodsCoupon(data,null);
    }


    /**
     * 取得所有子集
     */
    @Test
    public void  queryUnBindGoodsCouponByGid()throws Exception{
        long goodsIds=51;
        String couponNm=null;
        List<GoodsCouponShowVo> lists = goodsCouponService.queryUnBindGoodsCouponByGid(couponNm,goodsIds);
        System.out.println(JSON.toJSON(lists));
    }
    /**
     * 取得所有子集
     */
    @Test
    public void  goodsBindCoupon()throws Exception{
        long goodsId=51;
        String gcpIds="9,10,11";
        boolean flag = goodsCouponService.goodsBindCoupon(goodsId,gcpIds);
        System.out.println(flag);
    }
    /**
     * 取得所有子集
     */
    @Test
    public void  goodsUnBindCoupon()throws Exception{
        long goodsId=51;
        String gcpIds="9,10";
        boolean flag = goodsCouponService.goodsUnBindCoupon(goodsId,gcpIds);
        System.out.println(flag);
    }

}
