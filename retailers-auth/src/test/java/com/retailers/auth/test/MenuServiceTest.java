package com.retailers.auth.test;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.entity.Menu;
import com.retailers.auth.service.MenuService;
import com.retailers.auth.vo.MenuVo;
import com.retailers.auth.base.TestBaseJunit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zpapj on 2017/9/16.
 */
public class MenuServiceTest extends TestBaseJunit {
    @Autowired
    MenuService menuService;

    @Test
    public void queryMenuTree(){
        long time=System.currentTimeMillis();
        menuService.queryMenuTree();
        System.out.println("queryMenuTree执行时间"+(System.currentTimeMillis()- time));
    }
    @Test
    public void findAllMenu(){
        long userId=-1;
        long time=System.currentTimeMillis();
        List<Menu> menu=menuService.queryUserMenu(userId);
        System.out.println("findAllMenu执行时间"+(System.currentTimeMillis()- time));
        System.out.println(JSON.toJSON(menu));
    }
    @Test
    public void queryMenuNode(){
        long time=System.currentTimeMillis();
        List<MenuVo> list = menuService.queryMenuNode(60l);
        System.out.println("queryMenuNode执行时间"+(System.currentTimeMillis()- time));
        System.out.println(JSON.toJSON(list));
    }

}
