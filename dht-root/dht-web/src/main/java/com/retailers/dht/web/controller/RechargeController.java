package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.annotation.SystemControllerLog;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.service.OrderService;
import com.retailers.dht.common.service.RechargeService;
import com.retailers.dht.common.view.RechargeView;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.encrypt.DESUtils;
import com.retailers.tools.encrypt.DesKey;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 系统充值中心
 * @author zhongp
 * @version 1.0.1
 */
@RestController
@RequestMapping("recharge")
public class RechargeController extends BaseController {

    @Autowired
    private RechargeService rechargeService;
    @Autowired
    private OrderService orderService;


    /**
     * 取得充值例表
     * @param request
     * @return
     */
    @RequestMapping("queryRechargeLists")
    public BaseResp queryRecharges(HttpServletRequest request){
        List<RechargeView> list = rechargeService.queryRechargeLists();
        return success(list);
    }

    /**
     * 用户开始充值（创建充值单）
     * @param request
     * @param rid 充值id
     * @return
     */
    @RequestMapping("userRecharges")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY)
    public BaseResp userRecharges(HttpServletRequest request,String rid){
        long curUid=getCurLoginUserId(request);
        if(ObjectUtils.isEmpty(rid)){
            return errorForSystem("请选择充值金额");
        }
        try{
            Long rid_= Long.parseLong(DESUtils.decryptDES(rid, DesKey.WEB_KEY));
            Map<String,Object> rtn =orderService.userRecharge(curUid,rid_);
            return  success(rtn);
        }catch(Exception e){
            return errorForSystem("请你选择充值金额");
        }
    }
}
