package com.retailers.razz.manage.controller;

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

    @Resourse(resourse="sys.position.manage",label="职位管理",sort = 3)
    public String positionManage;

    @Resourse(resourse="sys.manager.article",label="文章管理",sort = 3)
    public String article;
}
