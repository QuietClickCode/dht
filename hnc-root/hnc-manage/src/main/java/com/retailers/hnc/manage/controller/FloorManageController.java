package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.FloorManage;
import com.retailers.hnc.common.service.FloorManageService;
import com.retailers.hnc.common.service.FloorRelationshipService;
import com.retailers.hnc.common.vo.FloorManageVo;
import com.retailers.hnc.manage.base.BaseController;
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
 * Created by niconiconi on 2017/12/15.
 */

@Controller
@RequestMapping("floorManage")
public class FloorManageController extends BaseController{
    @Autowired
    FloorManageService manageService;

    @Autowired
    FloorRelationshipService relationship;

    @RequestMapping("/floorManageMapping")
    @Menu(parentRes = "sys.manager.floor",resourse = "floorManage.floorManageMapping",description = "楼栋管理",label = "楼栋管理")
    public String floorManage(){
        return "floorManage/floorManage";
    }


    @RequestMapping("/addFloor")
    @Function(label = "添加楼栋",description = "添加楼栋",resourse = "floorManage.addFloor",sort = 3,parentRes = "floorManage.floorManageMapping")
    @ResponseBody
    public BaseResp addFloor(FloorManage manage){
        boolean flag = manageService.saveFloorManage(manage);
        if(flag)
            return success("新增楼层成功");
        else
            return success("新增楼层失败");
    }

    /*@RequestMapping("/updateAdvertising")
    @Function(label = "修改首页广告",description = "修改首页广告",resourse = "openHomeAdvertising.updateNavigatorBar",sort = 3,parentRes = "openHomeAdvertising.homeAdvertisingMapping")
    @ResponseBody
    public BaseResp updateNavigatorBar(HomeAdvertising advertising){
        boolean flag = advertisingService.updateHomeAdvertising(advertising);
        if(flag)
            return success("修改首页导航[" + advertising.getHaName() + "]成功");
        else
            return errorForSystem("修改首页导航[" + advertising.getHaName() + "]失败");
    }

    @RequestMapping("/removeAdvertising")
    @Function(label = "删除首页广告",description = "删除首页广告",resourse = "openHomeAdvertising.removeNavigatorBar",sort = 3,parentRes = "openHomeAdvertising.homeAdvertisingMapping")
    @ResponseBody
    public BaseResp removeAdvertising(Long haId){
        boolean flag = advertisingService.deleteHomeAdvertisingByHaId(haId);
        return  success(flag);
    }*/

    @RequestMapping("/queryFloorList")
    @Function(label="楼栋合集", description = "楼栋合集", resourse = "floorManage.queryFloorList",sort=1,parentRes="floorManage.floorManageMapping")
    @ResponseBody
    public Map<String,Object> queryNavigationLists(PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        Pagination<FloorManageVo> advertisingPagination = manageService.queryFloorManageList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",advertisingPagination.getPageSize());
        gtm.put("rows",advertisingPagination.getData());
        return gtm;
    }
}
