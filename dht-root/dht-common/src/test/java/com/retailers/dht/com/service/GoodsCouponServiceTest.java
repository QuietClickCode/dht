package com.retailers.dht.com.service;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.dao.GoodsCouponMapper;
import com.retailers.dht.common.service.GoodsCouponService;
import com.retailers.dht.common.view.GoodsCouponView;
import com.retailers.dht.common.vo.GoodsCouponShowVo;
import com.retailers.dht.common.vo.GoodsTypePriceVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/9
 */
public class GoodsCouponServiceTest extends TestBaseJunit {

    @Autowired
    private GoodsCouponService goodsCouponService;
    @Autowired
    private GoodsCouponMapper goodsCouponMapper;
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

    @Test
    public void queryUnRestrictedGoodsCoupon(){
        Date curDate=new Date();
        List<GoodsCouponView> gcvs=goodsCouponMapper.queryUnRestrictedGoodsCoupon(curDate);
        System.out.println(JSON.toJSON(gcvs));
    }
    @Test
    public void queryGoodsCouponByGids(){
        Date curDate=new Date();
        Set<Long> gids= new HashSet<Long>();
        gids.add(1l);
        gids.add(4l);
        gids.add(51l);
        gids.add(52l);
        gids.add(53l);
        gids.add(54l);
        gids.add(57l);
        List<GoodsCouponView> gcvs=goodsCouponMapper.queryGoodsCouponByGids(gids,curDate);
        System.out.println(JSON.toJSON(gcvs));
    }


    @Test
    public void queryGoodsCouponBuyGid(){
        List<GoodsTypePriceVo> list = new ArrayList<GoodsTypePriceVo>();
        GoodsTypePriceVo vo=new GoodsTypePriceVo(1l,771l,null,1000l,2l);
        GoodsTypePriceVo vo1=new GoodsTypePriceVo(4l,805l,null,10000l,2l);
        GoodsTypePriceVo vo2=new GoodsTypePriceVo(51l,853l,null,10000l,2l);
        GoodsTypePriceVo vo3=new GoodsTypePriceVo(52l,99l,null,10000l,2l);
        GoodsTypePriceVo vo4=new GoodsTypePriceVo(53l,2l,null,10000l,2l);
        GoodsTypePriceVo vo5=new GoodsTypePriceVo(54l,9l,null,10000l,2l);
        GoodsTypePriceVo vo6=new GoodsTypePriceVo(57l,11l,null,10000l,2l);
        list.add(vo);
        list.add(vo1);
        list.add(vo2);
        list.add(vo3);
        list.add(vo4);
        list.add(vo5);
        list.add(vo6);
        Map<String,List<GoodsCouponView>> rtn = goodsCouponService.queryGoodsCouponBuyGid(list);
        System.out.println(JSON.toJSON(rtn));
    }
}
