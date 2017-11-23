package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.UserCardPackage;
import com.retailers.dht.common.service.UserCardPackageService;
import com.retailers.dht.common.service.UserService;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @Autowired
    private UserCardPackageService userCardPackageService;
    @Autowired
    private UserService userService;
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
    @RequestMapping("userMember")
    public String openUserMember(HttpServletRequest request){
        return redirectUrl(request,"usercenter/user-member");
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
     * 用户详情
     * @param request
     * @return
     */
    @RequestMapping("userDetailInfo")
    public String openUserDetailInfo(HttpServletRequest request){
        return redirectUrl(request,"usercenter/user-info");
    }

    /**
     * 用户详情
     * @param request
     * @return
     */
    @RequestMapping("userRecharge")
    public String userRecharge(HttpServletRequest request){
        return redirectUrl(request,"usercenter/user-recharge");
    }


    /**
     * 用户收货地址
     * @param request
     * @return
     */
    @RequestMapping("userAddress")
    public String openUserAddress(HttpServletRequest request){
        return redirectUrl(request,"usercenter/user-address");
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

    /**
     * 取得用户卡包信息
     * @param request
     * @return
     */
    @RequestMapping("queryUserCardPackage")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp queryUserCardPackage(HttpServletRequest request){
        long uid=getCurLoginUserId(request);
        UserCardPackage user=userCardPackageService.queryUserCardPackage(uid);
        return success(user);
    }


    /**
     * 设置支付密码
     * @param request
     * @param payPwd 支付密码
     * @return
     */
    @RequestMapping("addPayPwd")
    @CheckSession(key=SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp addPayPwd(HttpServletRequest request,String payPwd){
        long uid=getCurLoginUserId(request);
        if(ObjectUtils.isEmpty(payPwd)){
            return errorForSystem("支付密码不能为空");
        }
        try{
            boolean flag = userService.addPwd(uid,payPwd,1);
            return success(flag);
        }catch (AppException e){
            return errorForSystem(e.getMessage());
        }

    }

    /**
     * 修改用户昵称
     * @param request
     * @param name
     * @return
     */
    @RequestMapping("updateUserName")
    @CheckSession(key=SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp updateUserName(HttpServletRequest request,String name){
        long uid=getCurLoginUserId(request);
        if(ObjectUtils.isEmpty(name)){
            return errorForSystem("昵称不能为空");
        }

        try{
            boolean flag = userService.updateUserName(uid,name);
            return success(flag);
        }catch (AppException e){
            return errorForSystem(e.getMessage());
        }
    }
    /**
     * 修改用户性别
     * @param request
     * @param sex
     * @return
     */
    @RequestMapping("updateUserSex")
    @CheckSession(key=SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp updateUserSex(HttpServletRequest request,Integer sex){
        long uid=getCurLoginUserId(request);
        if(ObjectUtils.isEmpty(sex)){
            return errorForSystem("性别不能为空");
        }
        try{
            boolean flag = userService.updateUserSex(uid,sex);
            return success(flag);
        }catch (AppException e){
            return errorForSystem(e.getMessage());
        }
    }

    /**
     * 修改用户头像
     * @param request
     * @param attachmentId
     * @return
     */
    @RequestMapping("updateUserHead")
    @CheckSession(key=SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp updateUserHead(HttpServletRequest request,Long attachmentId){
        long uid=getCurLoginUserId(request);
        if(ObjectUtils.isEmpty(attachmentId)){
            return errorForSystem("头像不能为空");
        }
        try{
            boolean flag = userService.updateUserHead(uid,attachmentId);
            return success(flag);
        }catch (AppException e){
            return errorForSystem(e.getMessage());
        }
    }

    /**
     * 用户绑定手机
     * @param request
     * @param phone 绑定手机
     * @param code 验证码
     * @return
     */
    @RequestMapping("bindPhone")
    @CheckSession(key=SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp bindPhone(HttpServletRequest request,String phone,String code){
        long uid=getCurLoginUserId(request);
        if(ObjectUtils.isEmpty(phone)){
            return errorForSystem("手机号不能为空");
        }
        if(ObjectUtils.isEmpty(code)){
            return errorForSystem("验证码不能为空");
        }
        try{
            boolean flag = userService.bindPhone(uid,phone,code);
            return success(flag);
        }catch (AppException e){
            return errorForSystem(e.getMessage());
        }
    }

    /**
     * 用户绑定手机
     * @param request
     * @param phone 手机校验
     * @return
     */
    @RequestMapping("checkPhone")
    @CheckSession(key=SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp checkPhone(HttpServletRequest request,String phone){
        long uid=getCurLoginUserId(request);
        if(ObjectUtils.isEmpty(phone)){
            return errorForSystem("手机号不能为空");
        }
        try{
            boolean flag = userService.checkPhone(uid,phone);
            return success(flag);
        }catch (AppException e){
            return errorForSystem(e.getMessage());
        }
    }

    /**
     * 设置支付密码
     * @param request
     * @param payPwd 支付密码
     * @return
     */
    @RequestMapping("changePayPwd")
    @CheckSession(key=SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp changePayPwd(HttpServletRequest request,String oldPayPwd,String payPwd){
        long uid=getCurLoginUserId(request);
        if(ObjectUtils.isEmpty(oldPayPwd)){
            return errorForSystem("原始支付密码不能为空");
        }
        if(ObjectUtils.isEmpty(payPwd)){
            return errorForSystem("支付密码不能为空");
        }
        try{
            boolean flag = userService.changePwd(uid,oldPayPwd,payPwd,1);
            return success(flag);
        }catch (AppException e){
            return errorForSystem(e.getMessage());
        }
    }
}
