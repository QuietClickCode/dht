package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.service.EmployeeManageService;
import com.retailers.hnc.common.vo.EmployeeManageVo;
import com.retailers.hnc.manage.base.BaseController;
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

    @RequestMapping("/employeeManageMapping")
    @Menu(parentRes = "sys.manager.employee",resourse = "employeeManage.employeeManageMapping",description = "员工管理",label = "员工管理")
    public String employeeManageMapping(){
        return "employee/employee_manage";
    }

    @RequestMapping("/queryEmployeeManageList")
    @Function(label="员工集合", description = "员工集合", resourse = "employeeManage.queryEmployeeManageList",sort=1,parentRes="employeeManage.employeeManageMapping")
    @ResponseBody
    public Map<String,Object> queryEmployeeManageList(PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        Pagination<EmployeeManageVo> teamPagination = employeeManageService.queryEmployeeManageList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",teamPagination.getTotalCount());
        gtm.put("rows",teamPagination.getData());
        return gtm;
    }

    @RequestMapping("/addEmployee")
    @Function(label = "添加员工",description = "添加员工",resourse = "employeeManage.addEmployee",sort = 3,parentRes = "employeeManage.employeeManageMapping")
    @ResponseBody
    public BaseResp addEmployee(EmployeeManage employeeManage,String emEntryTimes,String emRemoveTimes){
        employeeManage.setEmEntryTime(dateFormat(emEntryTimes));
        employeeManage.setEmRemoveTime(dateFormat(emRemoveTimes));
        boolean flag = employeeManageService.saveEmployeeManage(employeeManage);
        if(flag)
            return success("添加员工成功");
        else
            return success("添加员工失败");
    }

    @RequestMapping("/updateEmployee")
    @Function(label = "修改员工信息",description = "修改员工信息",resourse = "employeeManage.updateEmployee",sort = 3,parentRes = "employeeManage.employeeManageMapping")
    @ResponseBody
    public BaseResp updateEmployee(EmployeeManage employeeManage,String emEntryTimes,String emRemoveTimes){
        employeeManage.setEmEntryTime(dateFormat(emEntryTimes));
        employeeManage.setEmRemoveTime(dateFormat(emRemoveTimes));
        boolean flag = employeeManageService.updateEmployeeManage(employeeManage);
        if(flag)
            return success("修改员工[" + employeeManage.getEmName() + "]成功");
        else
            return errorForSystem("修改员工[" + employeeManage.getEmName() + "]失败");
    }

    @RequestMapping("/removeEmployee")
    @Function(label = "删除该员工",description = "删除该员工",resourse = "employeeManage.removeEmployee",sort = 3,parentRes = "employeeManage.employeeManageMapping")
    @ResponseBody
    public BaseResp removeEmployee(Long emId){
        boolean flag = employeeManageService.deleteEmployeeManageByEmId(emId);
        return  success(flag);
    }

    public EmployeeManage addDate(EmployeeManage employeeManage, String emEntryTime, String emRemoveTime ){
        if (!ObjectUtils.isEmpty(emEntryTime) && !ObjectUtils.isEmpty(emRemoveTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                Date spStarttime = sdf.parse(emEntryTime);
                Date spEndtime = sdf.parse(emRemoveTime);
                employeeManage.setEmEntryTime(spStarttime);
                employeeManage.setEmRemoveTime(spEndtime);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return employeeManage;
    }

    public Date dateFormat(String tmRegisterTimes){
        Date registerTime = null;
        if (!ObjectUtils.isEmpty(tmRegisterTimes)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                registerTime = sdf.parse(tmRegisterTimes);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return registerTime;
    }
}
