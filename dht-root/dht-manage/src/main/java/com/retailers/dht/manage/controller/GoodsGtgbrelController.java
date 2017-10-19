package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.GoodsGtgbrel;
import com.retailers.dht.common.service.GoodsGtgbrelService;
import com.retailers.dht.common.vo.GoodsGtgbrelVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("goods")
public class GoodsGtgbrelController extends BaseController {

    @Autowired
    GoodsGtgbrelService goodsGtgbrelService;

    @RequestMapping("/removeGoodsGtgbrel")
    @Function(label="删除商品大类与品牌关系", description = "删除商品大类与品牌关系", resourse = "goods.removeGoodsGtgbrel",sort=3,parentRes="goods.openGoodsType")
    @ResponseBody
    public BaseResp removeGoodsGtgbrel( String gtgbIds){
        boolean flag=goodsGtgbrelService.deleteGoodsGtgbrelByGtgbId(gtgbIds);
        return success(flag);
    }

    @RequestMapping("/queryGoodsGtgbrelLists")
    @Function(label="商品大类与品牌关系列表", description = "商品大类与品牌关系列表", resourse = "goods.queryGoodsGtgbrelLists",sort=1,parentRes="goods.openGoodsType")
    @ResponseBody
    public  Map<String,Object> queryGoodsGtgbrelLists(Long gtId,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gtId",gtId);
        map.put("isDelete",0);
        Pagination<GoodsGtgbrelVo> GoodsGtgbrelPagination = goodsGtgbrelService.queryGoodsGtgbrelList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",GoodsGtgbrelPagination.getTotalCount());
        gtm.put("rows",GoodsGtgbrelPagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoodsGtgbrel")
    @Function(label="增加商品大类与品牌关系", description = "增加商品大类与品牌关系", resourse = "goods.addGoodsGtgbrel",parentRes="goods.openGoodsType")
    @ResponseBody
    public BaseResp addGoodsGtgbrel(String gbIds,Long gtId){
        boolean flag=goodsGtgbrelService.saveGoodsGtgbrel(gbIds,gtId);
        return success(flag);
    }

}
