package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.dht.common.entity.GoodsGgsval;
import com.retailers.dht.common.service.GoodsGgsvalService;
import com.retailers.dht.common.vo.GoodsGgsvalOnceVo;
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
public class GoodsGgsvalController extends BaseController {

    @Autowired
    GoodsGgsvalService goodsGgsvalService;


    @RequestMapping("/removeGoodsGgsval")
    @Function(label="删除商品与规格值关系", description = "删除商品与规格值关系", resourse = "goods.removeGoodsGgsval",sort=3,parentRes = "goods.openGoods")
    @ResponseBody
    public BaseResp removeGoodsGgsval(Long ggsId){
        boolean flag=goodsGgsvalService.deleteGoodsGgsvalByGgsId(ggsId);
        return success(flag);
    }

    @RequestMapping("/queryGoodsGgsvalLists")
    @Function(label="商品与规格值关系列表", description = "商品与规格值关系列表", resourse = "goods.queryGoodsGgsvalLists",sort=1,parentRes = "goods.openGoods")
    @ResponseBody
    public  Map<String,Object> queryGoodsGgsvalLists(Long gsId,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gsId",gsId);
        map.put("isDelete",0);
        Pagination<GoodsGgsval> goodsGgsvalPagination = goodsGgsvalService.queryGoodsGgsvalList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",goodsGgsvalPagination.getTotalCount());
        gtm.put("rows",goodsGgsvalPagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoodsGgsval")
    @Function(label="增加商品与规格值关系", description = "增加商品与规格值关系", resourse = "goods.addGoodsGgsval",parentRes = "goods.openGoods")
    @ResponseBody
    public BaseResp addGoodsGgsval(GoodsGgsval goodsGgsval){
        goodsGgsval.setIsDelete(0L);
        boolean flag=goodsGgsvalService.saveGoodsGgsval(goodsGgsval);
        return success(flag);
    }

    @RequestMapping("/queryGgsrelLists")
    @Function(label="商品与规格关系列表", description = "商品与规格值关系列表", resourse = "goods.queryGgsrelLists",sort=1,parentRes = "goods.openGoods")
    @ResponseBody
    public  Map<String,Object> queryGgsrelLists(Long gid,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        System.out.println(gid);
        map.put("gid",gid);
        map.put("isDelete",0);
        Pagination<GoodsGgsval> goodsGgsvalPagination = goodsGgsvalService.queryGoodsGgsvalList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",goodsGgsvalPagination.getTotalCount());
        gtm.put("rows",goodsGgsvalPagination.getData());
        return gtm;
    }

    @RequestMapping("/queryGgsrelListsOnce")
    @Function(label="一次性加载商品与规格值关系列表", description = "一次性加载商品与规格值关系列表", resourse = "goods.queryGgsrelListsOnce",sort=1,parentRes = "goods.openGoods")
    @ResponseBody
    public  Map<String,Object> queryGgsrelListsOnce(Long gid){
        List<GoodsGgsvalOnceVo> list = goodsGgsvalService.queryGgsrelListsOnce(gid);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("rows",list);
        return gtm;
    }

    @RequestMapping("/addGoodsGgsvalByMyData")
    @Function(label="批量增加商品与规格值关系", description = "批量增加商品与规格值关系", resourse = "goods.addGoodsGgsvalByMyData",parentRes = "goods.openGoods")
    @ResponseBody
    public BaseResp addGoodsGgsvalByMyData(String gsIds,String gsvalids,Long gid){
        boolean flag = goodsGgsvalService.addGoodsGgsvalByMyData(gsIds,gsvalids,gid);
        return success(flag);
    }


}
