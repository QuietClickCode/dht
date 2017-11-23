package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.SecspepAdvertising;
import com.retailers.dht.common.service.SecspepAdvertisingService;
import com.retailers.dht.common.vo.SecspepAdvertisingVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
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
@RequestMapping("secspepAdvertising")
public class SecsepAdvertisingController extends BaseController {
    @Autowired
    SecspepAdvertisingService secspepAdvertisingService;

    @RequestMapping("/secspepAdvertisingMapping")
    @Menu(parentRes = "sys.manager.floorManage",resourse = "secspepAdvertising.secspepAdvertisingMapping",description = "特价/秒杀广告设置",label = "特价/秒杀广告设置")
    public String secspepManage(){
        return "navigation/secspepAdvertising";
    }

    @RequestMapping("/addsecspepAdvertising")
    @Function(label = "添加特价/秒杀广告",description = "添加特价/秒杀广告",resourse = "secspepAdvertising.addsecspepAdvertising",sort = 3,parentRes = "secspepAdvertising.secspepAdvertisingMapping")
    @ResponseBody
    public BaseResp addsecspepAdvertising(SecspepAdvertising secspepAdvertising){
        boolean flag = secspepAdvertisingService.saveSecspepAdvertising(secspepAdvertising);
        if (flag)
            return success("添加特价广告成功");
        else
            return success("添加特价广告失败");
    }

    @RequestMapping("/updatesecspepAdvertising")
    @Function(label = "编辑特价/秒杀广告",parentRes = "secspepAdvertising.secspepAdvertisingMapping",resourse = "secspepAdvertising.updatesecspepAdvertising",description = "编辑特价/秒杀广告",sort = 2)
    @ResponseBody
    public BaseResp updatesecspepAdvertising(SecspepAdvertising secspepAdvertising){
        System.out.println(secspepAdvertising.getSaId());
        boolean flag = secspepAdvertisingService.updateSecspepAdvertising(secspepAdvertising);
        if (flag)
            return success("修改特价/秒杀广告[" + secspepAdvertising.getSaName() + "]成功");
        else
            return errorForSystem("修改特价/秒杀广告[" + secspepAdvertising.getSaName() + "]失败");
    }

    @RequestMapping("/deletesecspepAdvertising")
    @Function(label = "删除特价/秒杀广告",parentRes = "secspepAdvertising.secspepAdvertisingMapping",resourse = "secspepAdvertising.deletesecspepAdvertising",description = "删除特价/秒杀广告",sort = 2)
    @ResponseBody
    public BaseResp deletesecspepAdvertising(Long saId){
        boolean flag = secspepAdvertisingService.deleteSecspepAdvertisingBySaId(saId);
        return success(flag);
    }

    @RequestMapping("/querysecspepAdvertisingList")
    @Function(label = "查询所有特价/秒杀广告",parentRes = "secspepAdvertising.secspepAdvertisingMapping",resourse = "secspepAdvertising.querysecspepAdvertisingList",description = "查询所有特价/秒杀广告",sort = 2)
    @ResponseBody
    public Map<String,Object> querysecspepAdvertisingList(String saName){
        Map params = new HashMap();
        params.put("saName",saName);
        params.put("isDelete",0L);
        Pagination<SecspepAdvertisingVo> pagination = secspepAdvertisingService.querySecspepAdvertisingList(params,1,100);
        Map<String,Object> rtn=new HashMap<String, Object>();
        rtn.put("total",1000);
        rtn.put("rows",pagination.getData());
        return rtn;
    }

}
