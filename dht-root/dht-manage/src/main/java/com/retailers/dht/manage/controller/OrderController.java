package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.service.OrderService;
import com.retailers.dht.common.vo.OrderDetailVo;
import com.retailers.dht.common.vo.OrderVo;
import com.retailers.dht.common.vo.UserVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单管理
 * @author zhongp
 * @version 1.0.1
 */
@Controller
@RequestMapping("order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    /**
     * 打开订单列表
     * @return
     */
    @RequestMapping("openOrderPage")
    @Menu(label = "订单列表",description = "订单列表",resourse = "order.openOrderPage",parentRes = "sys.manager.orderManage",sort = 1)
    public String openOrderPage(){
        return "order/order";
    }

    /**
     * 取得订单例表
     * @param request
     * @param orderNo 订单号
     * @param orderType 订单类型
     * @param orderStatus 订单状态
     * @param orderPayWay 支付方式
     * @param orderBuyNm 购买人
     * @param orderBuyUid 购买人id
     * @param orderLogisticsCode 快递单号
     * @param orderUaName 收货人姓名
     * @param orderUaPhone 收货人电话
     * @param pageForm 分页信息
     * @return
     */
    @RequestMapping("queryOrderLists")
    @Function(label = "查询订单列表",description = "查询订单列表",resourse = "order.queryOrderLists",parentRes = "order.openOrderPage",sort = 1)
    @ResponseBody
    public Map<String,Object> queryOrderLists(HttpServletRequest request,String orderNo,String orderType,String orderStatus,Long orderPayWay,String orderBuyNm,
                                    String orderBuyUid,String orderLogisticsCode,String orderUaName,String orderUaPhone,PageUtils pageForm){
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("orderNo",orderNo);
        if(ObjectUtils.isNotEmpty(orderType)){
            List<String> ots=new ArrayList<String>();
            for(String ot:orderType.split(",")){
                ots.add(ot);
            }
            params.put("orderType",ots);
        }
        if(ObjectUtils.isNotEmpty(orderStatus)){
            List<Long> oss=new ArrayList<Long>();
            for(String os:orderStatus.split(",")){
                if(ObjectUtils.isNotEmpty(os)){
                    oss.add(Long.parseLong(os));
                }
            }
            params.put("orderStatus",oss);
        }
        params.put("orderNo",orderNo);
        params.put("orderPayWay",orderPayWay);
        params.put("orderBuyNm",orderBuyNm);
        params.put("orderLogisticsCode",orderLogisticsCode);
        params.put("orderUaName",orderUaName);
        params.put("orderUaPhone",orderUaPhone);


        Pagination<OrderVo> pages = orderService.queryOrderLists(params,pageForm.getPageNo(),pageForm.getPageSize());
        return queryPages(pages);
    }

}
