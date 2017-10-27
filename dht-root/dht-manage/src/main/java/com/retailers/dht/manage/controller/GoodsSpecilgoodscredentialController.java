package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.dht.common.entity.GoodsSpecilgoodscredential;
import com.retailers.dht.common.service.GoodsSpecilgoodscredentialService;
import com.retailers.dht.common.vo.GoodsSpecilgoodscredentialVo;
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
public class GoodsSpecilgoodscredentialController extends BaseController {

    @Autowired
    GoodsSpecilgoodscredentialService goodsSpecilgoodscredentialService;

    @RequestMapping("/removeGoodsSpecilgoodscredential")
    @Function(label="删除商品与特殊商品证件关系", description = "删除商品与特殊商品证件关系", resourse = "goods.removeGoodsSpecilgoodscredential",sort=3,parentRes="goods.openGoods")
    @ResponseBody
    public BaseResp removeGoodsSpecilgoodscredential(Long gsgId){
        boolean flag=goodsSpecilgoodscredentialService.deleteGoodsSpecilgoodscredentialByGsgId(gsgId);
        return success(flag);
    }

    @RequestMapping("/editorGoodsSpecilgoodscredential")
    @Function(label="编辑商品与特殊商品证件关系", description = "编辑商品与特殊商品证件关系", resourse = "goods.editorGoodsSpecilgoodscredential",sort=3,parentRes="goods.openGoods")
    @ResponseBody
    public BaseResp editorGoodsSpecilgoodscredential(GoodsSpecilgoodscredential goodsSpecilgoodscredential){
        boolean flag=goodsSpecilgoodscredentialService.updateGoodsSpecilgoodscredential(goodsSpecilgoodscredential);
        return success(flag);
    }

    @RequestMapping("/queryGoodsSpecilgoodscredentialLists")
    @Function(label="商品与特殊商品证件关系列表", description = "商品与特殊商品证件关系列表", resourse = "goods.queryGoodsSpecilgoodscredentialLists",sort=1,parentRes="goods.openGoods")
    @ResponseBody
    public  Map<String,Object> queryGoodsSpecilgoodscredentialLists(Long gid,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gid",gid);
        map.put("isDelete",0);
        Pagination<GoodsSpecilgoodscredentialVo> goodsSpecilgoodscredentialPagination = goodsSpecilgoodscredentialService.queryGoodsSpecilgoodscredentialList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",goodsSpecilgoodscredentialPagination.getTotalCount());
        gtm.put("rows",goodsSpecilgoodscredentialPagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoodsSpecilgoodscredential")
    @Function(label="增加商品与特殊商品证件关系", description = "增加商品与特殊商品证件关系", resourse = "goods.addGoodsSpecilgoodscredential",parentRes="goods.openGoods")
    @ResponseBody
    public BaseResp addGoodsSpecilgoodscredential(GoodsSpecilgoodscredential goodsSpecilgoodscredential){
        goodsSpecilgoodscredential.setIsDelete(0L);
        boolean flag = goodsSpecilgoodscredentialService.saveGoodsSpecilgoodscredential(goodsSpecilgoodscredential);
        return success(flag);
    }



}
