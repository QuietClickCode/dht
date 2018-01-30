package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.service.OrderRefundService;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品退款处理
 * @author zhongp
 * @version 1.0.1
 * @data 2018/1/30
 */
@Controller
@RequestMapping("refund")
public class OrderRefundController extends BaseController{
    @Autowired
    private OrderRefundService orderRefundService;


    /**
     * 用户申请退款
     * @param request
     * @param orderId 订单id
     * @param remark 退款备注
     * @return
     */
    @RequestMapping("createRefund")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp createRefundOrder(HttpServletRequest request,Long orderId,String remark){
        try{
            long uid=getCurLoginUserId(request);
            String orderNo=orderRefundService.createRefund(uid,orderId,remark);
            return success(orderNo);
        }catch(AppException e){
            return errorForSystem(e.getMessage());
        }
    }
    @RequestMapping("queryUserRefundOrder")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp queryUserRefundOrder(HttpServletRequest request){
        return null;
    }
}
