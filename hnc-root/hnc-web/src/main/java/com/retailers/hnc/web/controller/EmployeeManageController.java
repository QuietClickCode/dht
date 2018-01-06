package com.retailers.hnc.web.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.service.EmployeeManageService;
import com.retailers.hnc.common.vo.EmployeeManageVo;
import com.retailers.hnc.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by niconiconi on 2017/12/25.
 */
@Controller
@RequestMapping("employeeManage")
public class EmployeeManageController extends BaseController{
    @Autowired
    EmployeeManageService employeeManageService;


    @RequestMapping("/queryEmployeeManageByPhone")
    @ResponseBody
    public Map<String,Object> queryEmployeeManageByPhone(String phone){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        map.put("wxPhone",phone);
        Pagination<EmployeeManageVo> teamPagination = employeeManageService.queryFirstEmployeeManageList(map,1,1);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",teamPagination.getTotalCount());
        gtm.put("rows",teamPagination.getData());
        return gtm;
    }

    @RequestMapping("/queryEmployeeManageByNotInPhone")
    @ResponseBody
    public Map<String,Object> queryEmployeeManageByNotInPhone(String phone,int pageNo,int pageSize){
        Long eid = getEmpIdByWxPhone(phone);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("eid",eid);
        Pagination<EmployeeManageVo> teamPagination = employeeManageService.queryEmployeeManageByNotInPhone(map,pageNo,pageSize);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",teamPagination.getTotalCount());
        gtm.put("rows",teamPagination.getData());
        return gtm;
    }

    @RequestMapping("/queryEmployeeManageList")
    @ResponseBody
    public Map<String,Object> queryEmployeeManageList(int pageNo,int pageSize){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("emType",1);
        map.put("isDelete",0);
        Pagination<EmployeeManageVo> teamPagination = employeeManageService.queryFirstEmployeeManageList(map,pageNo,pageSize);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",teamPagination.getTotalCount());
        gtm.put("rows",teamPagination.getData());
        return gtm;
    }
}
