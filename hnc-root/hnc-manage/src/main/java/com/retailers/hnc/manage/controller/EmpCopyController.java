package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.EmployeeManageCopy;
import com.retailers.hnc.common.service.EmployeeManageCopyService;
import com.retailers.hnc.common.vo.EmployeeManageCopyVo;
import com.retailers.hnc.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niconiconi on 2017/12/22.
 */
@Controller
@RequestMapping("EmployeeManageCopy")
public class EmpCopyController extends BaseController {
    @Autowired
    EmployeeManageCopyService employeeManageCopyService;

    @RequestMapping("/EmployeeManageCopyMapping")
    @Menu(parentRes = "sys.manager.log",resourse = "EmployeeManageCopy.EmployeeManageCopyMapping",description = "职员日志",label = "职员日志")
    public String EmployeeManageCopyMapping(){
        return "log/empManageLog";
    }

    @RequestMapping("/queryEmployeeManageCopyList")
    @Function(label="职员日志集合", description = "职员日志集合", resourse = "EmployeeManageCopy.queryEmployeeManageCopyList",sort=1,parentRes="EmployeeManageCopy.EmployeeManageCopyMapping")
    @ResponseBody
    public Map<String,Object> queryEmployeeManageCopyList(PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        Pagination<EmployeeManageCopyVo> EmployeeManageCopyPagination = employeeManageCopyService.queryEmployeeManageCopyList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",EmployeeManageCopyPagination.getTotalCount());
        gtm.put("rows",EmployeeManageCopyPagination.getData());
        return gtm;
    }


}
