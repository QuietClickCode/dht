package com.retailers.hnc.web.controller;

import com.retailers.hnc.common.entity.ClientManage;
import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.service.CheckUserService;
import com.retailers.hnc.common.service.ClientManageService;
import com.retailers.hnc.common.service.EmployeeManageService;
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
@RequestMapping("checkUser")
public class CheckUserController extends BaseController {

    @Autowired
    CheckUserService checkUserService;

    @RequestMapping("updateCheckUser")
    @CheckOpenId
    @ResponseBody
    public Map updateCheckUser(String validateCode,String phone){
        Long eid = getEmpIdByWxPhone(phone);
        Map map = checkUserService.checkUser(validateCode,eid);
        return map;
    }
}
