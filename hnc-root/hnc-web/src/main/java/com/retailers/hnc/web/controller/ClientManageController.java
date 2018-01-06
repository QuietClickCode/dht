package com.retailers.hnc.web.controller;

import com.retailers.hnc.common.entity.ClientManage;
import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.entity.Opening;
import com.retailers.hnc.common.service.ClientManageService;
import com.retailers.hnc.common.service.EmployeeManageService;
import com.retailers.hnc.common.vo.OpeningVo;
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
    @ResponseBody
    public Map<String,Object> queryEmpByClientManageId(String randStr){
        Long cid = getClientIdByOpenId(randStr);
        System.out.println(cid);
        ClientManage clientManage = clientManageService.queryClientManageByTmId(cid);
        Long empId = clientManage.getTmEmployee();
        Map map = new HashMap();
        if(ObjectUtils.isNotEmpty(empId)){
            EmployeeManage employeeManage = employeeManageService.queryEmployeeManageByEmId(empId);
            map.put("emp",employeeManage);
        }
        return map;
    }


    @RequestMapping("updateClientManageId")
    @ResponseBody
    public BaseResp updateClientManageId(String randStr,ClientManage clientManage){
        Long cid = getClientIdByOpenId(randStr);
        clientManage.setTmId(cid);
        boolean flag = clientManageService.updateClientManage(clientManage);
        return success(flag);
    }

    @RequestMapping("queryClientManageById")
    @ResponseBody
    public Map<String,Object> queryClientManageById(String randStr){
        Long cid = getClientIdByOpenId(randStr);
        System.out.println(cid);
        ClientManage clientManage = clientManageService.queryClientManageByTmId(cid);
        Map map = new HashMap();
        map.put("user",clientManage);
        return map;
    }

    @RequestMapping("clientManageBindEmployeeManege")
    @ResponseBody
    public BaseResp clientManageBindEmployeeManege(String randStr,Long emId){
        Long cid = getClientIdByOpenId(randStr);
        ClientManage clientManage = clientManageService.queryClientManageByTmId(cid);
        Long eid = clientManage.getTmEmployee();
        if(ObjectUtils.isNotEmpty(eid)){
            return errorForParam("已经绑定过置业顾问");
        }else{
            clientManage.setTmEmployee(emId);
            clientManageService.updateClientManage(clientManage);
            return success("绑定成功");
        }
    }

    @RequestMapping("queryEarlyCanComeIn")
    @ResponseBody
    public Map<String,Object> queryEarlyCanComeIn(String randStr){
        Long cid = getClientIdByOpenId(randStr);
        OpeningVo openingVo = clientManageService.queryEarlyCanComeIn(cid);
        Map map = new HashMap();
        map.put("row",openingVo);
        return map;
    }
}
