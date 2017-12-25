package com.retailers.dht.com.service;

import com.retailers.dht.common.dao.OrderMapper;
import com.retailers.dht.common.dao.UserCardPackageMapper;
import com.retailers.dht.com.base.TestBaseJunit;
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
    @Autowired
    private UserCardPackageMapper userCardPackageMapper;
    @Autowired
    private OrderMapper orderMapper;

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
    @Test
    public void userCardPackageMapper(){
        long uid=17;
        long type=0;
        long tradePrice=600;
        long cumulationCashPrice=100;
        int version =3;
        boolean isCashBack=true;
        userCardPackageMapper.statisticsUserSalseConsume(uid,type,tradePrice,cumulationCashPrice,isCashBack,version);
    }
    @Test
    public void findUserFirstBuy(){
        long uid=12;
        Long id=orderMapper.findUserFirstBuy(uid);
        System.out.println(id);
    }
}
