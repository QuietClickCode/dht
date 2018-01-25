package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.Resourse;
import org.springframework.stereotype.Controller;

/**
 * 层级树结构
 */
@Controller
public class BaseMenuController {
    @Resourse(resourse = "sys.manager.permissions",label = "系统设置",sort = 0)
    public String permissions;
    @Resourse(resourse = "sys.menu.manager",label = "菜单设置",sort = 0)
    public String menuManager;
    @Resourse(resourse = "sys.manager.opening",label = "开盘管理",sort = 2)
    public String openingManaage;
    @Resourse(resourse = "sys.manager.appointment",label = "预约管理",sort = 2)
    public String appointmentManaage;
    @Resourse(resourse = "sys.manager.client",label = "客户管理",sort = 2)
    public String clientManaage;
    @Resourse(resourse = "sys.manager.project",label = "项目管理",sort = 2)
    public String projectManaage;
    @Resourse(resourse = "sys.manager.floor",label = "楼栋管理",sort = 2)
    public String floorManaage;
    @Resourse(resourse="sys.manager.employee",label="人员管理",sort = 2)
    public String employeeManager;
    @Resourse(resourse="sys.manager.housetype",label="户型管理",sort = 3)
    public String housetypeManange;
    @Resourse(resourse="sys.manager.org",label="部门管理",parentRes = "sys.manager.permissions",sort = 2)
    public String menu2;
    @Resourse(resourse="sys.manager.log",label="日志管理",sort = 4)
    public String logManage;

}
