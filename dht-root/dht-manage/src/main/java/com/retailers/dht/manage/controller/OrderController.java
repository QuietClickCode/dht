package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.service.OrderService;
import com.retailers.dht.common.vo.OrderDetailVo;
import com.retailers.dht.common.vo.OrderVo;
import com.retailers.dht.common.vo.UserVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.common.constant.SysParameterConfigConstant;
import com.retailers.mybatis.common.entity.SysParameterConfig;
import com.retailers.mybatis.common.service.SysParameterConfigService;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    /**
     * 商品发货
     * @param request
     * @param orderId 订单id
     * @param orderLogisticsCode 快递单号
     * @return
     */
    @RequestMapping("sendGoods")
    @Function(label = "商品发货",description = "商品发货",resourse = "order.sendGoods",parentRes = "order.openOrderPage",sort =2)
    @ResponseBody
    public BaseResp sendGoods(HttpServletRequest request,Long orderId,String logisticsCompany,String orderLogisticsCode,String sendRemark){
        long sysUid=getCurLoginUserId(request);
        if(ObjectUtils.isEmpty(logisticsCompany)){
            return errorForParam("请选择物流公司");
        }
        if(ObjectUtils.isEmpty(orderLogisticsCode)){
            return errorForParam("请输入快递单号");
        }
        try{
            orderService.sendGoods(sysUid,orderId,logisticsCompany,orderLogisticsCode,sendRemark);
        }catch (AppException e){
            e.printStackTrace();
            return errorForSystem(e.getMessage());
        }catch (Exception e){
            return errorForSystem(e.getMessage());
        }
        return success(true);
    }

    /**
     * 打开订单设置
     * @return
     */
    @RequestMapping("openOrderSettingPage")
    @Menu(label = "订单基础设置",description = "订单基础设置",resourse = "order.openOrderSettingPage",parentRes = "sys.manager.orderManage",sort = 2)
    public ModelAndView openOrderSettingPage(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("params",SysParameterConfigConstant.params);
        modelAndView.setViewName("order/order_setting");
        return modelAndView;
    }

    /**
     * 订单设置
     * @param request
     * @param orderExpireDate
     * @param orderConfirmDate
     * @param orderCompleteDate
     * @param defaultLogisPrice
     * @return
     */
    @RequestMapping("orderSetting")
    @ResponseBody
    public BaseResp orderSetting(HttpServletRequest request,String orderExpireDate,String orderConfirmDate,String orderCompleteDate,String defaultLogisPrice){
        if(ObjectUtils.isEmpty(orderExpireDate)){
            return errorForParam("订单失效时间不能为空");
        }
        if(ObjectUtils.isEmpty(orderConfirmDate)){
            return errorForParam("自动确认收货时间不能为空");
        }
        if(ObjectUtils.isEmpty(orderCompleteDate)){
            return errorForParam("订单完成时间不能为空");
        }
        if(ObjectUtils.isEmpty(defaultLogisPrice)){
            return errorForParam("平台默认快递费不能为空");
        }
        orderService.orderSetting(orderExpireDate,orderConfirmDate,orderCompleteDate,orderCompleteDate);

        return success(true);
    }

}
