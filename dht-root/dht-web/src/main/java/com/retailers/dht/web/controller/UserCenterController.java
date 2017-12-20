package com.retailers.dht.web.controller;

import com.alibaba.fastjson.JSON;
import com.retailers.aliyun.sms.constant.SmsSendRecordConstant;
import com.retailers.aliyun.sms.entity.SmsSendRecord;
import com.retailers.aliyun.sms.service.SmsSendRecordService;
import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.entity.Attachment;
import com.retailers.dht.common.entity.User;
import com.retailers.dht.common.entity.UserCardPackage;
import com.retailers.dht.common.entity.WxAuthUser;
import com.retailers.dht.common.service.UserAddressService;
import com.retailers.dht.common.service.UserCardPackageService;
import com.retailers.dht.common.service.UserService;
import com.retailers.dht.common.view.UserInfoVIew;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.NumberUtils;
import com.retailers.tools.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户个人中心
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/15
 */
@Controller
@RequestMapping("user")
public class UserCenterController extends BaseController{
    Logger logger= LoggerFactory.getLogger(UserCenterController.class);
    @Autowired
    private UserCardPackageService userCardPackageService;
    @Autowired
    private UserService userService;
    @Autowired
    private SmsSendRecordService smsSendRecordService;

    @Autowired
    private UserAddressService addressService;

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
     * 我的会员卡
     * @param request
     * @return
     */
    @RequestMapping("userMember")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,redirectUrl = "/loginPage")
    public String openUserMember(HttpServletRequest request){
        return redirectUrl(request,"usercenter/user-member");
    }
    /**
     * 用户余额
     * @param request
     * @return
     */
    @RequestMapping("userWallet")
    @CheckSession(key=SystemConstant.LOG_USER_SESSION_KEY,redirectUrl = "/loginPage",isOpenPage = true)
    public ModelAndView openUserWallet(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        Long uId=getCurLoginUserId(request);
        UserInfoVIew user = userService.queryUserInfoByUid(uId);
        modelAndView.addObject("wallet", NumberUtils.formaterNumberPower(user.getUcurWallet()));
        modelAndView.setViewName(redirectUrl(request,"usercenter/user-wallet"));
        return modelAndView;
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
     *  绑定手机页面
     */
    @RequestMapping("UserPhone")
    public String openUserPhone(HttpServletRequest request){
        return redirectUrl(request,"usercenter/user-phone");
    }

    /**
     *  安全管理页面
     */
    @RequestMapping("UserSafety")
    public String openUserSafety(HttpServletRequest request){
        return redirectUrl(request,"usercenter/user-safety");
    }

    /**
     *  修改昵称页面
     */
    @RequestMapping("UserNickName")
    public String openUserNickName(HttpServletRequest request){
        return redirectUrl(request,"usercenter/user-nickname");
    }

    /**
     *  更改手机号页面
     */
    @RequestMapping("updateUserPhone")
    public String openUpdateUserPhone(HttpServletRequest request){
        return redirectUrl(request,"usercenter/update_user_phone");
    }

    /**
     *  修改支付密码页面
     */
    @RequestMapping("updatePayPwd")
    public String openUpdatePayPwd(HttpServletRequest request){
        return redirectUrl(request,"usercenter/update_pay_password");
    }

    /**
     *  设置支付密码页面
     */
    @RequestMapping("setPayPwd")
    public String openSetPayPwd(HttpServletRequest request){
        return redirectUrl(request,"usercenter/user_set_pay_password");
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
     * 发送验证码
     * @param request
     * @param phone 收短信手机号码
     * @return
     */
    @RequestMapping("sendSmsValidCode")
    @ResponseBody
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY)
    public BaseResp sendSmsValidCode(HttpServletRequest request,String phone){
        long uid=getCurLoginUserId(request);
        if(ObjectUtils.isEmpty(phone)){
            return errorForParam("手机号不能为空");
        }
        try{
            SmsSendRecord sendRecord = smsSendRecordService.sendSmsCode(uid,phone, SmsSendRecordConstant.SMS_SEND_TYPE_BIND_PHONE);
            return success(sendRecord.getId());
        }catch (AppException e){
            return errorForSystem(e.getMessage());
        }
    }

    /**
     * 根据手机号，类型取得发送消息内容
     * @param request
     * @return
     */
    @RequestMapping("verifyValidCode")
    @ResponseBody
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY)
    public BaseResp verifyValidCode(HttpServletRequest request,String phone,int type,String code,Date curDate){
        boolean flag = smsSendRecordService.queryCurSmsSendRecordByPhone(phone,type,code,curDate);
        return success(flag);
    }

    /**
     *  校验短信是否可以发送（该时间周期内未发送过短信)
     */

    @RequestMapping("checkSendSms")
    @ResponseBody
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY)
    public BaseResp verifyValidCode(HttpServletRequest request,String phone,long type,Date curDate){
        long uid=getCurLoginUserId(request);
        int time = smsSendRecordService.checkSendSms(uid,phone,type,curDate);
        System.out.println(time);
        return success(time);
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
     * 校验手机是否可用
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
     * 修改支付密码
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
     * 获取用户信息
     * @param request
     * @return
     */
    @RequestMapping("getUserInfo")
    @CheckSession(key=SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public Map<String,Object> getUserInfo(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<String,Object>();
        long uid=getCurLoginUserId(request);
        User user = userService.queryUserByUid(uid);
        String userUphone = user.getUphone();
        userUphone = userUphone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
        map.put("userPhone",userUphone);
        map.put("sex",user.getUsex());
        map.put("nickName",user.getUname());
        Attachment attachment = userService.queryUserHeader(user.getUimgid());
        map.put("UserHeaderSrc",AttachmentConstant.IMAGE_SHOW_URL+attachment.getShowUrl());
        String userAddress = addressService.queryDefaultUserAddress(uid);
        map.put("userAddress",userAddress);
        return map;
    }


    /**
     *  获取当前用户ID
     */
    @RequestMapping("queryLoginUserId")
    @CheckSession(key=SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public Map<String,Object> queryLoginUserId(HttpServletRequest request){
        Long uId=getCurLoginUserId(request);
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("uId",uId);
        return map;
    }

    /**
     *  获取当前用户昵称
     */
    @RequestMapping("queryLoginUserName")
    @CheckSession(key=SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public Map<String,Object> queryLoginUserName(HttpServletRequest request){
        Long uId=getCurLoginUserId(request);
        HashMap<String,Object> map = new HashMap<String,Object>();
        User user = userService.queryUserByUid(uId);
        map.put("uname",user.getUname());
        return map;
    }

    /**
     *  当前用户是否登陆
     */
    @RequestMapping("queryLoginUser")
    @CheckSession(key=SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public Map<String,Object> queryLoginUser(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<String,Object>();
        UserInfoVIew userInfoVIew = (UserInfoVIew)request.getSession().getAttribute(SystemConstant.LOG_USER_SESSION_KEY);
        System.out.println(userInfoVIew);
        if (userInfoVIew == null) {
            map.put("flag",false);
        }else {
            map.put("flag",true);
        }
        return map;
    }


    /**
     *  获取当前用户支付密码是否为空
     */
    @RequestMapping("queryLoginUserPayPwd")
    @CheckSession(key=SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public Map<String,Object> queryLoginUserPayPwd(HttpServletRequest request){
        Long uId=getCurLoginUserId(request);
        User user = userService.queryUserByUid(uId);
        HashMap<String,Object> map = new HashMap<String,Object>();
        System.out.println(user.getUpayPwd());
        boolean flag = ObjectUtils.isEmpty(user.getUpayPwd());
        map.put("flag",flag);
        map.put("userPhone",user.getUphone());
        return map;
    }

    /**
     * 用户登录
     * @param request
     * @param account 帐号
     * @param pwd 密码
     * @param isBindWx 成功后跳转跳径
     * @param validateCode 验证码
     * @return
     */
    @RequestMapping("userLogin")
    @ResponseBody
    public BaseResp userLogin(HttpServletRequest request,String account,String pwd,String validateCode,Boolean isBindWx){
        if(ObjectUtils.isEmpty(account)){
            return errorForParam("请输入登录帐号");
        }
        if(ObjectUtils.isEmpty(pwd)){
            return errorForParam("请输入密码");
        }
        if(ObjectUtils.isEmpty(validateCode)){
            return errorForParam("请输入验证码");
        }

        String serverCode="";
        if(ObjectUtils.isNotEmpty(request.getSession().getAttribute(com.retailers.dht.common.constant.SystemConstant.LOGIN_VALIDATE_CODE))){
            serverCode = request.getSession().getAttribute(com.retailers.dht.common.constant.SystemConstant.LOGIN_VALIDATE_CODE).toString();
        }
        if(!validateCode.equalsIgnoreCase(serverCode)){
            return errorForValidateCode("验证码错误");
        }
        try{
            Long wxId=null;
            if(isBindWx){
                WxAuthUser wxAuthUser=(WxAuthUser)request.getSession().getAttribute(SystemConstant.CUR_LOGIN_WXUSER_INFO);
                wxId=wxAuthUser.getWauId();
            }
            UserInfoVIew userInfoVIew= userService.userLogin(account,pwd,isBindWx,wxId);
            logger.info("取得用户登陆信息:{}", JSON.toJSON(userInfoVIew));
            setCurLoginUser(request,userInfoVIew);
        }catch(AppException e){
            e.printStackTrace();
            return errorForSystem(e.getMessage());
        }
        return success(null);
    }

    /**
     *微信登陆未关联用户
     * @return
     */
    @RequestMapping("wxLoginNoUser")
    @ResponseBody
    public BaseResp wxLoginNoUser(HttpServletRequest request){
        Object obj=request.getSession().getAttribute(com.retailers.auth.constant.SystemConstant.CUR_LOGIN_WXUSER_INFO);
        if(ObjectUtils.isNotEmpty(obj)){
            WxAuthUser wxAuthUser= (WxAuthUser)obj;
            UserInfoVIew userInfoVIew=userService.wxLoginNoUser(wxAuthUser);
            setCurLoginUser(request,userInfoVIew);
            return success("新建用户成功");
        }else{
            return errorForSystem("关联微信登陆帐号异常");
        }
    }
}
