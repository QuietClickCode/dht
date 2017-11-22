package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.dht.common.entity.GoodsGtgsrel;
import com.retailers.dht.common.service.GoodsGtgsrelService;
import com.retailers.dht.common.vo.GoodsGtgsrelVo;
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
public class GoodsGtgsrelController extends BaseController {

    @Autowired
    GoodsGtgsrelService goodsGtgsrelService;

    @RequestMapping("/removeGoodsGtgsrel")
    @ResponseBody
    public BaseResp removeGoodsGtgsrel(String gtgsIds){
        boolean flag=goodsGtgsrelService.deleteGoodsGtgsrelByGtgsId(gtgsIds);
        return success(flag);
    }

    @RequestMapping("/queryGoodsGtgsrelLists")
    @ResponseBody
    public  Map<String,Object> queryGoodsGtgsrelLists(Long gtId,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gtId",gtId);
        map.put("isDelete",0);
        Pagination<GoodsGtgsrelVo> GoodsGtgsrelPagination = goodsGtgsrelService.queryGoodsGtgsrelList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",GoodsGtgsrelPagination.getTotalCount());
        gtm.put("rows",GoodsGtgsrelPagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoodsGtgsrel")
    @ResponseBody
    public BaseResp addGoodsGtgsrel(String gsIds,Long gtId){
        boolean flag=goodsGtgsrelService.saveGoodsGtgsrel(gsIds,gtId);
        return success(flag);
    }

}
