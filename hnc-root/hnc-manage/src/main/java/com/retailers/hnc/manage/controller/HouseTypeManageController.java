package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.FloorRelationship;
import com.retailers.hnc.common.entity.HouseTypeManage;
import com.retailers.hnc.common.service.HouseTypeManageService;
import com.retailers.hnc.common.vo.HouseTypeManageVo;
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
 * Created by niconiconi on 2017/12/18.
 */
@Controller
@RequestMapping("houseManage")
public class HouseTypeManageController extends BaseController{
    @Autowired
    HouseTypeManageService houseTypeManageService;

    @RequestMapping("/houseManageMapping")
    @Menu(parentRes = "sys.manager.housetype",resourse = "houseManage.houseManageMapping",description = "户型管理",label = "户型管理")
    public String houseManage(){
        return "houseType/houseType";
    }

    @RequestMapping("/queryHouseType")
    @Function(label="户型集合", description = "户型集合", resourse = "houseManage.queryFloorList",sort=1,parentRes="houseManage.houseManageMapping")
    @ResponseBody
    public Map<String,Object> queryHouseTypeList(PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        Pagination<HouseTypeManageVo> advertisingPagination = houseTypeManageService.queryHouseTypeManageList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",advertisingPagination.getTotalCount());
        gtm.put("rows",advertisingPagination.getData());
        return gtm;
    }

    @RequestMapping("/addHouseType")
    @Function(label = "添加户型",description = "添加户型",resourse = "houseManage.addHouseType",sort = 3,parentRes = "houseManage.houseManageMapping")
    @ResponseBody
    public BaseResp addHouseType(HouseTypeManage manage){
        boolean flag = houseTypeManageService.saveHouseTypeManage(manage);
        if(flag)
            return success("添加户型成功");
        else
            return success("添加户型失败");
    }

    @RequestMapping("/updateHouseType")
    @Function(label = "修改户型信息",description = "修改户型信息",resourse = "houseManage.updateHouseType",sort = 3,parentRes = "houseManage.houseManageMapping")
    @ResponseBody
    public BaseResp updateHouseType(HouseTypeManage manage){
        boolean flag = houseTypeManageService.updateHouseTypeManage(manage);
        if(flag)
            return success("修改户型信息成功");
        else
            return errorForSystem("修改户型信息失败");
    }

    @RequestMapping("/removeHouseType")
    @Function(label = "删除户型",description = "删除户型",resourse = "houseManage.removeHouseType",sort = 3,parentRes = "houseManage.houseManageMapping")
    @ResponseBody
    public BaseResp removeHouseType(Long htId){
        boolean flag = houseTypeManageService.deleteHouseTypeManageByHtId(htId);
        return  success(flag);
    }

    @RequestMapping("/addFloorRelationship")
    @Function(label = "添加户型",description = "添加户型",resourse = "houseManage.addFloorRelationship",sort = 3,parentRes = "houseManage.houseManageMapping")
    @ResponseBody
    public BaseResp addFloorRelationship(@RequestBody List<FloorRelationship> relationships){
        boolean flag = houseTypeManageService.addFloorRelationship(relationships);
        if(flag)
            return success("添加户型成功");
        else
            return success("添加户型失败");
    }
}
