package com.retailers.dht.web.controller;

import com.retailers.dht.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户个人中心
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/15
 */
@Controller
@RequestMapping("user")
public class UserCenterController extends BaseController{
    /**
     * 打开用户中心
     * @param request
     * @return
     */
    @RequestMapping("userCenter")
    public String openUserCenter(HttpServletRequest request){
        return redirectUrl(request,"usercenter/user-center");
    }

    /**
     * 用户余额
     * @param request
     * @return
     */
    @RequestMapping("userWallet")
    public String openUserWallet(HttpServletRequest request){
        return redirectUrl(request,"usercenter/user-wallet");
    }

    /**
     * 打开用户优惠卷页面
     * @param request
     * @return
     */
    @RequestMapping("userCoupon")
    public String openUserCoupon(HttpServletRequest request){
        return redirectUrl(request,"usercenter/user-coupon");
    }
}
