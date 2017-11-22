package com.retailers.dht.web.controller;

import com.retailers.dht.common.service.RechargeService;
import com.retailers.dht.common.view.RechargeView;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
}
