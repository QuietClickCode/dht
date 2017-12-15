package com.retailers.dht.web.controller;

import com.retailers.dht.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("order")
public class OrderController extends BaseController {

    @RequestMapping("checkOrder")
    public String checkOrder(HttpServletRequest request){
        return redirectUrl(request,"order/create-order");
    }

    @RequestMapping("getCheckOrderData")
    public String getCheckOrderData(HttpServletRequest request,String data){
        request.getSession().setAttribute("checkOrderData",data);
        return "redirect://order/checkOrder";
    }
}
