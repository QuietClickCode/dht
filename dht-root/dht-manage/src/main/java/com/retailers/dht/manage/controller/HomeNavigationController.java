package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.HomeNavigation;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.service.HomeNavigationService;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niconiconi on 2017/10/16.
 */
@Controller
@RequestMapping("openHomeNavigation")
public class HomeNavigationController extends BaseController {
    @Autowired
    HomeNavigationService homeNavigationService;

    @Autowired
    AttachmentService attachmentService;

    @RequestMapping("/homeNavigationMapping")
    @Menu(parentRes = "sys.manager.floorManage",resourse = "openHomeNavigation.homeNavigationMapping",description = "首页导航栏",label = "首页导航栏")
    public String openHomeNavigation(){
        return "navigation/homeNavigation";
    }

    @RequestMapping("/addNavigatorBar")
    @Function(label = "添加首页导航",description = "添加首页导航",resourse = "openHomeNavigation.addNavigatorBar",sort = 3,parentRes = "openHomeNavigation.homeNavigationMapping")
    @ResponseBody
    public BaseResp addNavigatorBar(HomeNavigation navigation){
        boolean flag = homeNavigationService.saveHomeNavigation(navigation);
        if(flag)
            return success("新增楼层成功");
        else
            return success("新增楼层失败");
    }

    @RequestMapping("/updateNavigatorBar")
    @Function(label = "修改首页导航",description = "修改首页导航",resourse = "openHomeNavigation.updateNavigatorBar",sort = 3,parentRes = "openHomeNavigation.homeNavigationMapping")
    @ResponseBody
    public BaseResp updateNavigatorBar(HomeNavigation navigation){
        boolean flag = homeNavigationService.updateHomeNavigation(navigation);
        if(flag)
            return success("修改首页导航[" + navigation.getHnName() + "]成功");
        else
            return errorForSystem("修改首页导航[" + navigation.getHnName() + "]失败");
    }

    @RequestMapping("/removeNavigatorBar")
    @Function(label = "删除首页导航",description = "删除首页导航",resourse = "openHomeNavigation.removeNavigatorBar",sort = 3,parentRes = "openHomeNavigation.homeNavigationMapping")
    @ResponseBody
    public BaseResp removeNavigatorBar(Long hnId){
        boolean flag = homeNavigationService.deleteHomeNavigationByHnId(hnId);
        return  success(flag);
    }

    @RequestMapping("/queryNavigationLists")
    @Function(label="商品大类列表", description = "所有商品大类列表", resourse = "openHomeNavigation.queryNavigationLists",sort=1,parentRes="openHomeNavigation.homeNavigationMapping")
    @ResponseBody
    public Map<String,Object> queryNavigationLists(Long hnStyle,Long hnClient,Long hnCountry, PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("hnStyle",hnStyle);
        map.put("hnClient",hnClient);
        map.put("hnCountry",hnCountry);
        map.put("isDelete",0);
        Pagination<HomeNavigation> goodsTypePagination = homeNavigationService.queryHomeNavigationList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",goodsTypePagination.getTotalCount());
        gtm.put("rows",goodsTypePagination.getData());
        return gtm;
    }
}