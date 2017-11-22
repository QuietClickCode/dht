package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.dht.common.entity.GoodsCgfrel;
import com.retailers.dht.common.service.GoodsCgfrelService;
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
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("goods")
public class GoodsCgfrelController extends BaseController {

    @Autowired
    GoodsCgfrelService goodsCgfrelService;

    @RequestMapping("/removeGoodsCgfrel")
    @ResponseBody
    public BaseResp removeGoodsCgfrel(Long gglIds){
        boolean flag=goodsCgfrelService.deleteGoodsCgfrelByCgfId(gglIds);
        return success(flag);
    }

    @RequestMapping("/queryGoodsCgfrelLists")
    @ResponseBody
    public  Map<String,Object> queryGoodsCgfrelLists(Long gfId,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gfId",gfId);
        map.put("isDelete",0);
        Pagination<GoodsCgfrel> GoodsCgfrelPagination = goodsCgfrelService.queryGoodsCgfrelList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",GoodsCgfrelPagination.getTotalCount());
        gtm.put("rows",GoodsCgfrelPagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoodsCgfrel")
    @ResponseBody
    public BaseResp addGoodsCgfrel(String cids, Long gfId){
        boolean flag = goodsCgfrelService.saveGoodsCgfrel(cids,gfId);
        return success(flag);
    }



}
