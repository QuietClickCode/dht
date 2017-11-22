package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Resourse;
import org.springframework.stereotype.Controller;

/**
 * 层级树结构
 */
@Controller
public class BaseMenuController {
    @Resourse(resourse = "sys.manager.permissions",label = "系统设置",sort = 0)
    public String permissions;
    @Resourse(resourse = "sys.manager.goods",label = "商品管理",sort = 2)
    public String goodsManaage;
    @Resourse(resourse="sys.manager.org",label="部门管理",parentRes = "sys.manager.permissions",sort = 2)
    public String menu2;
    @Resourse(resourse="sys.user.manager",label="人员管理",changeRes="sys.manager.user",parentRes = "sys.manager.permissions",sort = 3)
    public String userManange;
    @Resourse(resourse="sys.menu.manager",label="资源管理",parentRes = "sys.manager.permissions",sort = 1)
    public String menuManage;
    @Resourse(resourse = "sys.manager.promotion",label = "促销方式",sort =3)
    public String promotion;
    @Resourse(resourse = "sys.manager.customer",label = "会员管理",sort = 1)
    public String customer;

    @Resourse(resourse = "sys.manager.floorManage",label = "导航/广告设置",sort = 5)
    public String floorManage;

    @Resourse(resourse = "sys.manager.articleManage",label = "文章管理",sort = 6)
    public String articleManage;
}
