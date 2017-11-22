package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.entity.GoodsType;
import com.retailers.dht.common.service.GoodsGgcrelService;
import com.retailers.dht.common.vo.GoodsGgcrelVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("goods")
public class GoodsGgcrelController extends BaseController {

    @Autowired
    GoodsGgcrelService goodsGgcrelService;

    @RequestMapping("/removeGoodsGgcrel")
    @ResponseBody
    public BaseResp removeGoodsGgcrel(String ggcIds){
        boolean flag=goodsGgcrelService.deleteGoodsGgcrelByGgcId(ggcIds);
        return success(flag);
    }

    @RequestMapping("/removeGclassGoodsGgcrel")
    @ResponseBody
    public BaseResp removeGclassGoodsGgcrel(String gcIds,Long gid){
        boolean flag=goodsGgcrelService.deleteGclassGoodsGgcrelByGgcId(gcIds,gid);
        return success(flag);
    }

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

    @RequestMapping("/addGoodsGgcrel")
    @ResponseBody
    public BaseResp addGoodsGgcrel(String ggcIds,Long gid,String gids,Long gcId){
        boolean flag = false;
        if(!ObjectUtils.isEmpty(ggcIds)){
            flag = goodsGgcrelService.saveGoodsGgcrel(ggcIds,gid);
        }else{
            flag = goodsGgcrelService.saveGoodsGgcrels(gids,gcId);
        }

        return success(flag);
    }

    @RequestMapping("/addGoodsGgcrelByGc")
    @ResponseBody
    public BaseResp addGoodsGgcrelByGc(String gclassIds,Long gcId){
        boolean flag=goodsGgcrelService.saveGoodsGgcrelByGc(gclassIds,gcId);
        return success(flag);
    }

    @RequestMapping("/clearGoodsGgcrel")
    @ResponseBody
    public BaseResp clearGoodsGgcrel(Long gcId){
        boolean flag=goodsGgcrelService.clearGoodsGgcrel(gcId);
        return success(flag);
    }

    @RequestMapping("/querydeletedgclass")
    @ResponseBody
    public Map<String,Object> querydeletedgclass(Long gid){
        List<GoodsGgcrelVo> rows = goodsGgcrelService.querydeletedgclass(gid);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("rows",rows);
        return gtm;
    }
}
