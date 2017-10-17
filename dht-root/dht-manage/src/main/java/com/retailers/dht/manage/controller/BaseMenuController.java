package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Resourse;
import org.springframework.stereotype.Controller;

/**
 * 层级树结构
 */
@Controller
public class BaseMenuController {
    @Resourse(resourse = "sys.manager.permissions",label = "系统设置")
    public String permissions;
    @Resourse(resourse = "sys.manager.goods",label = "商品管理")
    public String goodsManaage;
    @Resourse(resourse="sys.manager.org",label="部门管理",parentRes = "sys.manager.permissions",sort = 2)
    public String menu2;
    @Resourse(resourse="sys.user.manager",label="人员管理",changeRes="sys.manager.user",parentRes = "sys.manager.permissions",sort = 3)
    public String userManange;
    @Resourse(resourse="sys.menu.manager",label="资源管理",parentRes = "sys.manager.permissions",sort = 1)
    public String menuManage;
    @Resourse(resourse="sys.menu.test",label="测试菜单")
    public String menu6;
    @Resourse(resourse="sys.menu.test1",label="测试菜单1",parentRes="sys.menu.test")
    public String menu7;

    @Resourse(resourse="sys.menu.test2",label="测试菜单2",parentRes="sys.menu.test1")
    public String menu8;
    @Resourse(resourse="sys.menu.test3",label="测试菜单3",parentRes="sys.menu.test2")
    public String menu9;
    @Resourse(resourse="sys.menu.test4",label="测试菜单4",parentRes="sys.menu.test3")
    public String menu10;
    @Resourse(resourse="sys.menu.test5",label="测试菜单5",parentRes="sys.menu.test4")
    public String menu11;
    @Resourse(resourse="sys.menu.test6",label="测试菜单6",parentRes="sys.menu.test5")
    public String menu12;
    @Resourse(resourse="sys.menu.test7",label="测试菜单7",parentRes="sys.menu.test6")
    public String menu13;
    @Resourse(resourse="sys.menu.test8",label="测试菜单8",parentRes="sys.menu.test7")
    public String menu14;
    @Resourse(resourse="sys.menu.test9",label="测试菜单9",parentRes="sys.menu.test7")
    public String menu15;

    @Resourse(resourse = "sys.manager.promotion",label = "促销方式",sort =2)
    public String promotion;
    @Resourse(resourse = "sys.manager.customer",label = "会员管理",sort = 3)
    public String customer;

    @Resourse(resourse = "sys.manager.floorManage",label = "导航/广告设置",sort = 5)
    public String floorManage;
}
