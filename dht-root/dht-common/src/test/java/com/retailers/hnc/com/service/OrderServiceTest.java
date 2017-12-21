package com.retailers.hnc.com.service;

import com.retailers.hnc.com.base.TestBaseJunit;
import com.retailers.dht.common.service.OrderService;
import com.retailers.dht.common.vo.BuyGoodsDetailVo;
import com.retailers.dht.common.vo.BuyInfoVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/20
 */
public class OrderServiceTest extends TestBaseJunit {

    @Autowired
    private OrderService orderService;

    @Test
    public void shoppingOrder(){
        long uid=11;
        BuyInfoVo biv=new BuyInfoVo();
        biv.setAddress(65l);
        biv.setCpIds("");
        List<BuyGoodsDetailVo> bgds=new ArrayList<BuyGoodsDetailVo>();
        BuyGoodsDetailVo bgd=new BuyGoodsDetailVo();
        bgd.setRemark("数据测试");
        bgd.setBuyCarId(28l);
        bgd.setGdId(857l);
        bgd.setGoodsId(57l);
        bgd.setNum(7);
        bgds.add(bgd);
        biv.setBuyGoods(bgds);
        try{
            orderService.shoppingOrder(uid,biv,null,null);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
