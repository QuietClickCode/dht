package com.retailers.dht.web.controller;

import com.retailers.dht.common.service.GoodsGgcrelService;
import com.retailers.dht.common.vo.GoodsComplimentaryVo;
import com.retailers.dht.common.vo.GoodsGgcrelVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
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
@RequestMapping("goodsGgcrel")
public class GoodsGgcrelController extends BaseController {

    @Autowired
    GoodsGgcrelService goodsGgcrelService;

    @RequestMapping("/queryGoodsGgcrelLists")
    @ResponseBody
    public  Map<String,Object> queryGoodsGgcrelLists(Long gid,Long gcId,Long isClass,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gid",gid);
        map.put("gcId",gcId);
        map.put("isClass",isClass);
        map.put("isDelete",0);
        Pagination<GoodsGgcrelVo> GoodsGgcrelPagination = goodsGgcrelService.queryGoodsGgcrelList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",GoodsGgcrelPagination.getTotalCount());
        gtm.put("rows",GoodsGgcrelPagination.getData());
        return gtm;
    }

    @RequestMapping("/queryGclassGoodsGgcrelLists")
    @ResponseBody
    public  Map<String,Object> queryGclassGoodsGgcrelLists(Long gid){
        List<GoodsGgcrelVo> list = goodsGgcrelService.queryGclassGoodsGgcrelLists(gid);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("rows",list);
        return gtm;
    }

    @RequestMapping("/queryAllGoodsGgcrelLists")
    @ResponseBody
    public  Map<String,Object> queryAllGoodsGgcrelLists(Long gid){
        List<GoodsComplimentaryVo> list = goodsGgcrelService.queryAllGoodsGgcrelLists(gid);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("rows",list);
        return gtm;
    }
}
