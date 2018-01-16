package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.FloorManage;
import com.retailers.hnc.common.entity.FloorRelationship;
import com.retailers.hnc.common.service.FloorManageService;
import com.retailers.hnc.common.vo.FloorManageVo;
import com.retailers.hnc.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/12/15.
 */

@Controller
@RequestMapping("floorManage")
public class FloorManageController extends BaseController{
    @Autowired
    FloorManageService manageService;

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
            return errorForSystem("新增楼层失败");
    }

    @RequestMapping("/updateFloor")
    @Function(label = "修改楼栋信息",description = "修改楼栋信息",resourse = "floorManage.updateFloor",sort = 3,parentRes = "floorManage.floorManageMapping")
    @ResponseBody
    public BaseResp updateFloor(FloorManage manage){
        System.out.println(manage.getVersion());
        boolean flag = manageService.updateFloorManage(manage);
        if(flag)
            return success("修改首页导航[" + manage.getFmName() + "]成功");
        else
            return errorForSystem("修改首页导航[" + manage.getFmName() + "]失败");
    }

    @RequestMapping("/removeFloor")
    @Function(label = "删除楼栋",description = "删除楼栋",resourse = "floorManage.removeFloor",sort = 3,parentRes = "floorManage.floorManageMapping")
    @ResponseBody
    public BaseResp removeFloor(Long fmId){
        boolean flag = manageService.deleteFloorManageByFmId(fmId);
        return  success(flag);
    }

    @RequestMapping("/queryFloorList")
    @Function(label="楼栋合集", description = "楼栋合集", resourse = "floorManage.queryFloorList",sort=1,parentRes="floorManage.floorManageMapping")
    @ResponseBody
    public Map<String,Object> queryFloorManageList(PageUtils pageForm,String fmName){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        map.put("fmName",fmName);
        Pagination<FloorManageVo> advertisingPagination = manageService.queryFloorManageList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",advertisingPagination.getTotalCount());
        gtm.put("rows",advertisingPagination.getData());
        return gtm;
    }

    @RequestMapping("/queryAllFloorList")
    @ResponseBody
    public Map<String,Object> queryAllFloorList(PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        Pagination<FloorManageVo> advertisingPagination = manageService.queryFirstFloorManageList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",advertisingPagination.getTotalCount());
        gtm.put("rows",advertisingPagination.getData());
        return gtm;
    }

    @RequestMapping("/addHouseTypeRelationship")
    @Function(label = "添加户型",description = "添加户型",resourse = "floorManage.addHouseTypeRelationship",sort = 3,parentRes = "floorManage.floorManageMapping")
    @ResponseBody
    public BaseResp addHouseTypeRelationship(@RequestBody List<FloorRelationship> relationships){
        for (FloorRelationship relationship:relationships)
            System.out.println(relationship.getFmId()+"  "+relationship.getFrId());
        boolean flag = manageService.addFloorRelationship(relationships);
        if(flag)
            return success("添加户型成功");
        else
            return success("添加户型失败");
    }
}
