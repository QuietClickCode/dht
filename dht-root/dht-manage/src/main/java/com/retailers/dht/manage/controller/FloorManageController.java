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
    @Menu(parentRes = "sys.manager.goods",resourse = "floorManage.floorManageMapping",description = "首页楼层管理",label = "楼层管理")
    public String floorManage(){
        return "goods/floorManage";
    }

    @RequestMapping("/addFloor")
    @Function(label = "添加楼层",description = "添加楼层",resourse = "goods.addFloor",sort = 3,parentRes = "floorManage.floorManageMapping")
    @ResponseBody
    public BaseResp addFloor(){
        FloorManage manage = new FloorManage();
        manage.setFlName("新鲜水果");
        manage.setFlOrder(new Long(2));
        boolean b = floorManageService.saveFloorManage(manage);
        return success(b);
    }

    @RequestMapping("/updateFloor")
    @Function(label = "编辑楼层",parentRes = "floorManage.floorManageMapping",resourse = "goods.UpdateFloor",description = "编辑楼层",sort = 2)
    @ResponseBody
    public BaseResp UpdateFloor(FloorManage floorManage){
        FloorManage manage = floorManageService.queryFloorManageByFlId(floorManage.getFlId());
        manage.setFlName(floorManage.getFlName());
        manage.setFlOrder(floorManage.getFlOrder());
        manage.setIsShow(floorManage.getIsShow());
        boolean flag = floorManageService.updateFloorManage(manage);
        if (flag)
            return success("修改楼层[" + floorManage.getFlName() + "]成功");
        else
            return errorForSystem("修改楼层[" + floorManage.getFlName() + "]失败");
    }

    @RequestMapping("/deleteFloor")
    @Function(label = "删除楼层",parentRes = "floorManage.floorManageMapping",resourse = "goods.deleteFloor",description = "删除楼层",sort = 2)
    @ResponseBody
    public BaseResp deleteFloor(Long flId){
        boolean flag = floorManageService.deleteFloorManageByFlId(flId);
        return success(flag);
    }

    @RequestMapping("/queryFloorsLists")
    @Function(label = "查询所有楼层",parentRes = "floorManage.floorManageMapping",resourse = "goods.queryFloorsLists",description = "查询所有楼层",sort = 2)
    @ResponseBody
    public Map<String,Object> queryFloorsListsTest(){
        List<FloorManageVo> floorManageVos = floorManageService.queryFloorTree();
        Map<String,Object> rtn=new HashMap<String, Object>();
        rtn.put("total",1000);
        rtn.put("rows",floorManageVos);
        return rtn;
    }
}
