package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.service.OrderService;
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
 * 钱包支付管理
 * @author zhongp
 * @version 1.0.1
 */
@Controller
@RequestMapping("wallet")
public class WalletPayController extends BaseController {
    @Autowired
    private OrderService orderService;

    /**
     * 钱包支付
     * @param request
     * @param orderNo 订单号
     * @return
     */
    @RequestMapping("walletPay")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp walletPay(HttpServletRequest request,String orderNo,String payPwd){
        long uid=getCurLoginUserId(request);
        try{
            orderService.walletPay(uid,orderNo,payPwd);
        }catch(AppException e){
            e.printStackTrace();
            return errorForSystem(e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
        return success(true);
    }
}
