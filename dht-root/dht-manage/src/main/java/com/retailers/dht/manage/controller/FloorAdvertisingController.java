package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.FloorAdvertising;
import com.retailers.dht.common.service.FloorAdvertisingService;
import com.retailers.dht.common.vo.FloorAdvertisingVo;
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
 * Created by niconiconi on 2017/10/21.
 */
@Controller
@RequestMapping("floorAdvertising")
public class FloorAdvertisingController extends BaseController {
    @Autowired
    FloorAdvertisingService advertisingService;

    @RequestMapping("/floorAdvertisingMapping")
    @Menu(parentRes = "sys.manager.floorManage",resourse = "floorAdvertising.floorAdvertisingMapping",description = "楼层广告设置",label = "楼层广告设置")
    public String floorManage(){
        return "navigation/floorAdvertising";
    }

    @RequestMapping("/addFloorAdvertising")
    @Function(label = "添加楼层",description = "添加楼层",resourse = "floorAdvertising.addFloorAdvertising",sort = 3,parentRes = "floorAdvertising.floorAdvertisingMapping")
    @ResponseBody
    public BaseResp addFloorAdvertising(FloorAdvertising advertising){
        boolean flag = advertisingService.saveFloorAdvertising(advertising);
        if (flag)
            return success("新增楼层成功");
        else
            return success("新增楼层失败");
    }

    @RequestMapping("/updateFloorAdvertising")
    @Function(label = "编辑楼层",parentRes = "floorAdvertising.floorAdvertisingMapping",resourse = "floorAdvertising.updateFloorAdvertising",description = "编辑楼层",sort = 2)
    @ResponseBody
    public BaseResp updateFloorAdvertising(FloorAdvertising advertising){
        boolean flag = advertisingService.updateFloorAdvertising(advertising);
        if (flag)
            return success("修改楼层[" + advertising.getFaName() + "]成功");
        else
            return errorForSystem("修改楼层[" + advertising.getFaName() + "]失败");
    }

    @RequestMapping("/deleteFloorAdvertising")
    @Function(label = "删除楼层",parentRes = "floorAdvertising.floorAdvertisingMapping",resourse = "floorAdvertising.deleteFloorAdvertising",description = "删除楼层",sort = 2)
    @ResponseBody
    public BaseResp deleteFloorAdvertising(Long faId){
        List<FloorAdvertisingVo> floorAdvertisingVos = advertisingService.queryFloorAdvertisingTree();
        for(FloorAdvertisingVo vo:floorAdvertisingVos)
            System.out.println(vo.getFaId()+" "+vo.getParentId()+" "+vo.getFaName());
        boolean flag = advertisingService.deleteFloorAdvertisingByFaId(faId);
        return success(flag);
    }

    @RequestMapping("/queryFloorAdvertisingList")
    @Function(label = "查询所有楼层",parentRes = "floorAdvertising.floorAdvertisingMapping",resourse = "floorAdvertising.queryFloorAdvertisingList",description = "查询所有楼层",sort = 2)
    @ResponseBody
    public Map<String,Object> queryFloorAdvertisingList(){
        List<FloorAdvertisingVo> floorAdvertisingVos = advertisingService.queryFloorAdvertisingTree();
        Map<String,Object> rtn=new HashMap<String, Object>();
        rtn.put("total",1000);
        rtn.put("rows",floorAdvertisingVos);
        return rtn;
    }
}
