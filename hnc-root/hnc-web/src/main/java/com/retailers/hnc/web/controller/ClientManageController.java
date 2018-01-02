package com.retailers.hnc.web.controller;

import com.retailers.hnc.common.entity.ClientManage;
import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.entity.UserLikeHourse;
import com.retailers.hnc.common.service.ClientManageService;
import com.retailers.hnc.common.service.EmployeeManageService;
import com.retailers.hnc.common.service.UserLikeHourseService;
import com.retailers.hnc.web.annotation.CheckOpenId;
import com.retailers.hnc.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("clientManage")
public class ClientManageController extends BaseController {

    @Autowired
    ClientManageService clientManageService;
    @Autowired
    EmployeeManageService employeeManageService;

    @RequestMapping("queryEmpByClientManageId")
    @CheckOpenId
    @ResponseBody
    public Map<String,Object> queryEmpByClientManageId(String randStr){
        Long cid = getClientIdByOpenId(randStr);
        ClientManage clientManage = clientManageService.queryClientManageByTmId(cid);
        Long empId = clientManage.getTmEmployee();
        Map map = new HashMap();
        if(ObjectUtils.isNotEmpty(empId)){
            EmployeeManage employeeManage = employeeManageService.queryEmployeeManageByEmId(empId);
            map.put("emp",employeeManage);
        }
        return map;
    }

}
