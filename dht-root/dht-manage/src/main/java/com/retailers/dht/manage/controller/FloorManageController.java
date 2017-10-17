package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.FloorManage;
import com.retailers.dht.common.service.FloorManageService;
import com.retailers.dht.common.vo.FloorManageVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.tools.base.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/10/11.
 */
@Controller
@RequestMapping("floorManage")
public class FloorManageController extends BaseController {
    @Autowired
    FloorManageService floorManageService;

    @RequestMapping("/floorManageMapping")
    @Menu(parentRes = "sys.manager.floorManage",resourse = "floorManage.floorManageMapping",description = "首页楼层管理",label = "楼层管理")
    public String floorManage(){
        return "navigation/floorManage";
    }

    @RequestMapping("/addFloor")
    @Function(label = "添加楼层",description = "添加楼层",resourse = "floorManage.addFloor",sort = 3,parentRes = "floorManage.floorManageMapping")
    @ResponseBody
    public BaseResp addFloor(FloorManage floorManage){
        boolean flag = floorManageService.saveFloorManage(floorManage);
        if (flag)
            return success("新增楼层成功");
        else
            return success("新增楼层失败");
    }

    @RequestMapping("/updateFloor")
    @Function(label = "编辑楼层",parentRes = "floorManage.floorManageMapping",resourse = "floorManage.UpdateFloor",description = "编辑楼层",sort = 2)
    @ResponseBody
    public BaseResp UpdateFloor(FloorManage floorManage){
        FloorManage manage = floorManageService.queryFloorManageByFlId(floorManage.getFlId());
        manage.setFlName(floorManage.getFlName());
        manage.setFlOrder(floorManage.getFlOrder());
        manage.setIsShow(floorManage.getIsShow());
        manage.setParentId(floorManage.getParentId());
        boolean flag = floorManageService.updateFloorManage(manage);
        if (flag)
            return success("修改楼层[" + floorManage.getFlName() + "]成功");
        else
            return errorForSystem("修改楼层[" + floorManage.getFlName() + "]失败");
    }

    @RequestMapping("/deleteFloor")
    @Function(label = "删除楼层",parentRes = "floorManage.floorManageMapping",resourse = "floorManage.deleteFloor",description = "删除楼层",sort = 2)
    @ResponseBody
    public BaseResp deleteFloor(Long flId){
        boolean flag = floorManageService.deleteFloorManageByFlId(flId);
        return success(flag);
    }

    @RequestMapping("/queryFloorsLists")
    @Function(label = "查询所有楼层",parentRes = "floorManage.floorManageMapping",resourse = "floorManage.queryFloorsLists",description = "查询所有楼层",sort = 2)
    @ResponseBody
    public Map<String,Object> queryFloorsListsTest(){
        List<FloorManageVo> floorManageVos = floorManageService.queryFloorTree();
        Map<String,Object> rtn=new HashMap<String, Object>();
        rtn.put("total",1000);
        rtn.put("rows",floorManageVos);
        return rtn;
    }

    @RequestMapping("/queryFloorManageNode")
    @ResponseBody
    public BaseResp queryFloorManageNode(Long flId){
        List<FloorManageVo> floors = floorManageService.queryFloorNode(flId);
        return success(floors);
    }
}
