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
    @ResponseBody
    public BaseResp removeGoodsSpecilgoodscredential(Long gsgId){
        boolean flag=goodsSpecilgoodscredentialService.deleteGoodsSpecilgoodscredentialByGsgId(gsgId);
        return success(flag);
    }

    @RequestMapping("/editorGoodsSpecilgoodscredential")
    @ResponseBody
    public BaseResp editorGoodsSpecilgoodscredential(GoodsSpecilgoodscredential goodsSpecilgoodscredential){
        boolean flag=goodsSpecilgoodscredentialService.updateGoodsSpecilgoodscredential(goodsSpecilgoodscredential);
        return success(flag);
    }

    @RequestMapping("/queryGoodsSpecilgoodscredentialLists")
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
    @ResponseBody
    public BaseResp addGoodsSpecilgoodscredential(GoodsSpecilgoodscredential goodsSpecilgoodscredential){
        goodsSpecilgoodscredential.setIsDelete(0L);
        boolean flag = goodsSpecilgoodscredentialService.saveGoodsSpecilgoodscredential(goodsSpecilgoodscredential);
        return success(flag);
    }



}
