package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.entity.GoodsGgclrel;
import com.retailers.dht.common.entity.GoodsType;
import com.retailers.dht.common.service.GoodsClassificationService;
import com.retailers.dht.common.service.GoodsGgclrelService;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.dht.common.service.GoodsTypeService;
import com.retailers.dht.common.vo.GoodsGgclrelVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
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
public class GoodsGgclrelController extends BaseController {

    @Autowired
    GoodsGgclrelService goodsGgclrelService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsClassificationService goodsClassificationService;
    @Autowired
    GoodsTypeService goodsTypeService;

    @RequestMapping("/removeGoodsGgclrel")
    @ResponseBody
    public BaseResp removeGoodsGgclrel(String ggclIds){
        boolean flag = goodsGgclrelService.deleteGoodsGgclrelByGgclId(ggclIds);
        return success(flag);
    }

    @RequestMapping("/queryGoodsGgclrelLists")
    @ResponseBody
    public  Map<String,Object> queryGoodsGgclrelLists(Long gid,PageUtils pageForm){
        Map map = new HashMap();
        map.put("gid",gid);
        map.put("isDelete",0L);
        List<GoodsGgclrelVo> list = goodsGgclrelService.queryMyGoodsGgclrelList(map);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("rows",list);
        return gtm;
    }

    @RequestMapping("/queryGclassGoodsGgclrelLists")
    @ResponseBody
    public  Map<String,Object> queryGclassGoodsGgclrelLists(Long gid){
        List<GoodsGgclrelVo> list = goodsGgclrelService.queryGclassGoodsGgclrelLists(gid);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("rows",list);
        return gtm;
    }

    @RequestMapping("/addGoodsGgclrel")
    @ResponseBody
    public BaseResp addGoodsGgclrel(String gclIds,Long gid){
        boolean flag = goodsGgclrelService.saveGoodsGgclrel(gclIds,gid);
        return success(flag);
    }

    @RequestMapping("/addGoodsGgcrelByGclass")
    @ResponseBody
    public BaseResp addGoodsGgcrelByGclass(String gclassIds,Long gclId){
        boolean flag = goodsGgclrelService.addGoodsGgcrelByGclass(gclassIds,gclId);
        return success(flag);
    }

    @RequestMapping("/addGoodsGgclrelByGids")
    @ResponseBody
    public BaseResp addGoodsGgclrelByGids(String gids,Long gclId){
        boolean flag = goodsGgclrelService.saveGoodsGgclrelByGids(gids,gclId);
        return success(flag);
    }

    @RequestMapping("/saveDeleteGt")
    @ResponseBody
    public BaseResp saveDeleteGt(String gclIds,Long gid){
        boolean flag = goodsGgclrelService.saveDeleteGt(gclIds,gid);
        return success(flag);
    }

    @RequestMapping("/selectGoodsGgclrelLists")
    @ResponseBody
    public  Map<String,Object> selectGoodsGgclrelLists(Long gid,Long gclId,Long isClass,PageUtils pageForm){
        Map map = new HashMap();
        map.put("gid",gid);
        map.put("isClass",isClass);
        map.put("gclId",gclId);
        map.put("isDelete",0L);
        List<GoodsGgclrel> paginationList = goodsGgclrelService.queryGoodsGgclrelList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("rows",paginationList);
        return gtm;
    }
}
