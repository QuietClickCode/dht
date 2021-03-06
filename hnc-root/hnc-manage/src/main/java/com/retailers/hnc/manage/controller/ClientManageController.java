package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.service.ClientManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by niconiconi on 2017/12/27.
 */
@Controller
@RequestMapping("clientManage")
public class ClientManageController {
    @Autowired
    ClientManageService clientManageService;

    @RequestMapping("/clientManageMapping")
    @Menu(parentRes = "sys.manager.client",resourse = "clientManage.clientManageMapping",description = "客户管理",label = "客户管理")
    public String clientManageMapping(){
        return "clientManage/clientManage";
    }

    @RequestMapping("/QuotaAllocationMapping")
    @Menu(parentRes = "sys.manager.client",resourse = "clientManage.QuotaAllocationMapping",description = "分配客户名额",label = "分配客户名额")
    public String QuotaAllocationMapping(){
        return "quota-allocation";
    }

    @RequestMapping("/checkDistribution")
    @Menu(parentRes = "sys.manager.client",resourse = "clientManage.checkDistribution",description = "审核顾客分配",label = "审核顾客分配")
    public String checkDistribution(){
        return "check-distribution";
    }

}
