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
    @Function(label="删除城市与运费关系", description = "删除城市与运费关系", resourse = "goods.removeGoodsCgfrel",sort=3,parentRes="goods.openGoodsFreight")
    @ResponseBody
    public BaseResp removeGoodsCgfrel(Long gglIds){
        boolean flag=goodsCgfrelService.deleteGoodsCgfrelByCgfId(gglIds);
        return success(flag);
    }

    @RequestMapping("/queryGoodsCgfrelLists")
    @Function(label="城市与运费关系列表", description = "城市与运费关系列表", resourse = "goods.queryGoodsCgfrelLists",sort=1,parentRes="goods.openGoodsFreight")
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
    @Function(label="增加城市与运费关系", description = "增加城市与运费关系", resourse = "goods.addGoodsCgfrel",parentRes="goods.openGoodsFreight")
    @ResponseBody
    public BaseResp addGoodsCgfrel(String cids, Long gfId){
        boolean flag = goodsCgfrelService.saveGoodsCgfrel(cids,gfId);
        return success(flag);
    }



}
