package com.retailers.dht.com.service;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.entity.Coupon;
import com.retailers.dht.common.entity.GoodsCoupon;
import com.retailers.dht.common.service.CouponService;
import com.retailers.dht.common.service.GoodsCouponService;
import com.retailers.dht.common.vo.CouponShowVo;
import com.retailers.dht.common.vo.CouponWebVo;
import com.retailers.dht.common.vo.GoodsCouponShowVo;
import com.retailers.dht.common.vo.GoodsCouponVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.exception.AppException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/14
 */
public class CouponServiceTest extends TestBaseJunit {

    @Autowired
    private GoodsCouponService goodsCouponService;
    @Autowired
    private CouponService couponService;

    @Test
    public void test()throws Exception{
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("isDelete",0);
        params.put("nowDate",new Date());
        params.put("type",0);
        Pagination<GoodsCouponShowVo> pages= goodsCouponService.queryGoodsCouponList(params,1,100);
        System.out.println(pages.getData().size());
    }

    @Test
    public void queryCurCoupon()throws Exception{
        Long uid=-1l;
        int pageNo=1;
        int pageSize=10;
        List<CouponWebVo> lists=couponService.queryCouponList(uid,pageNo,pageSize);
        System.out.println(JSON.toJSON(lists));
    }

    @Test
    public void userGrabCoupon()throws Exception{
        for(int i=0;i<2;i++){
            new Thread(new Runnable() {
                public void run() {
                    long cpid=6;
                    long uid=1;
                    try{
                        couponService.userGrabCoupon(uid,cpid);
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }
            }).start();
        }
        Thread.sleep(1000*30);
    }

    /**
     * 根据状态取得用户优惠卷列表
     * @param uid
     * @param type
     * @param pageNo
     * @param pageSize
     * @return
     * @throws AppException
     */
    @Test
    public void queryUserCoupon()throws Exception{
        Long uid=-1l;
        int type=2;
        int pageNo=1;
        int pageSize=10;
        Pagination<CouponWebVo> lists=couponService.queryUserCoupon(uid,type,pageNo,pageSize);
        System.out.println(JSON.toJSON(lists));
    }

}
