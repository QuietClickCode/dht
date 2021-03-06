package com.retailers.dht.com.service;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.common.dao.CurrentPlatformSalesMapper;
import com.retailers.dht.common.dao.OrderMapper;
import com.retailers.dht.common.dao.UserCardPackageMapper;
import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.entity.CurrentPlatformSales;
import com.retailers.dht.common.service.OrderService;
import com.retailers.dht.common.vo.BuyGoodsDetailVo;
import com.retailers.dht.common.vo.BuyInfoVo;
import com.retailers.dht.common.vo.OrderVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.exception.AppException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

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
    @Autowired
    private CurrentPlatformSalesMapper currentPlatformSalesMapper;
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
        int type=0;
        long tradePrice=600;
        long cumulationCashPrice=100;
        int version =3;
        boolean isCashBack=true;
        userCardPackageMapper.statisticsUserSalseConsume(uid,type,tradePrice,cumulationCashPrice,version);
    }
    @Test
    public void findUserFirstBuy(){
        long uid=12;
        Long id=orderMapper.findUserFirstBuy(uid);
        System.out.println(id);
    }

    /**
     * 查询订单详情
     */
    @Test
    public void queryOrderInfoLists(){
        Map<String, Object> params=new HashMap<String,Object>();
        List<String> orderTypes= Arrays.asList("SHOPPING");
        params.put("orderType",orderTypes);
        List<Long> orderStatus= Arrays.asList(3l,5l);
        params.put("orderStatus",orderStatus);
        //取得分页订单
        Pagination<OrderVo> page = new Pagination<OrderVo>();
        page.setPageNo(1);
        page.setPageSize(20);
        page.setParams(params);
        List<OrderVo> list = orderMapper.queryOrderInfoLists(page);
        System.out.println(JSON.toJSON(list));
    }

    @Test
    public void queryOrderLists(){
        Map<String, Object> params=new HashMap<String,Object>();
        List<String> orderTypes= Arrays.asList("SHOPPING");
        params.put("orderType",orderTypes);
        List<Long> orderStatus= Arrays.asList(3l,5l);
        params.put("orderStatus",orderStatus);
        Pagination<OrderVo> pages = orderService.queryOrderLists(params,1,10);
        System.out.println(JSON.toJSON(pages));
    }

    @Test
    public void walletPay(){
        long uid=1683058;
        String orderNo="GM180119115349116";
        try{
            orderService.walletPay(uid,orderNo,"");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 取得订单支付金额
     */
    @Test
    public void queryMenberPrice()throws Exception{
        long uid=1545352;
        String orderNo="CZ180303145455024";
        orderService.queryMenberPrice(uid,orderNo);
    }

    @Test
    public void clearExpireOrder()throws Exception{
        orderService.clearExpireOrder();
    }

    @Test
    public void queryOrderInfos()throws Exception{
        long uid=15599231;
        long orderId=272;
        OrderVo ov= orderService.queryOrderInfos(uid,orderId);
        System.out.println(JSON.toJSON(ov));
    }
    @Test
    public void queryCurrentPlatformSalesByGtype(){
        long type=0;
        List<Long> types=Arrays.asList(4l,5l,7l,8l);
        Set<Long> ts=new HashSet<Long>();
        for(Long t:types){
            ts.add(t);
        }
        List<CurrentPlatformSales> list=currentPlatformSalesMapper.queryCurrentPlatformSalesByGtype(type,ts);
        System.out.println(JSON.toJSON(list));
    }
}
