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
    @Function(label="删除商品与评论标签关系", description = "删除商品大类与评论标签关系", resourse = "goods.removeGoodsGgclrel",sort=3,parentRes="goods.openGoods")
    @ResponseBody
    public BaseResp removeGoodsGgclrel(String ggclIds){
        boolean flag = goodsGgclrelService.deleteGoodsGgclrelByGgclId(ggclIds);
        return success(flag);
    }

    @RequestMapping("/queryGoodsGgclrelLists")
    @Function(label="商品与评论标签关系列表", description = "商品与评论标签关系列表", resourse = "goods.queryGoodsGgclrelLists",sort=1,parentRes="goods.openGoods")
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
    @Function(label="商品与评论标签关系列表(通过子类查询)", description = "商品与评论标签关系列表(通过子类查询)", resourse = "goods.queryGclassGoodsGgclrelLists",sort=1,parentRes="goods.openGoods")
    @ResponseBody
    public  Map<String,Object> queryGclassGoodsGgclrelLists(Long gid){
        List<GoodsGgclrelVo> list = goodsGgclrelService.queryGclassGoodsGgclrelLists(gid);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("rows",list);
        return gtm;
    }

    @RequestMapping("/addGoodsGgclrel")
    @Function(label="增加商品与评论标签关系", description = "增加商品与评论标签关系", resourse = "goods.addGoodsGgclrel",parentRes="goods.openGoods")
    @ResponseBody
    public BaseResp addGoodsGgclrel(String gclIds,Long gid){
        boolean flag = goodsGgclrelService.saveGoodsGgclrel(gclIds,gid);
        return success(flag);
    }

    @RequestMapping("/addGoodsGgcrelByGclass")
    @Function(label="增加商品与评论标签关系(通过子类)", description = "增加商品与评论标签关系(通过子类)", resourse = "goods.addGoodsGgcrelByGclass",parentRes="goods.openGoods")
    @ResponseBody
    public BaseResp addGoodsGgcrelByGclass(String gclassIds,Long gclId){
        boolean flag = goodsGgclrelService.addGoodsGgcrelByGclass(gclassIds,gclId);
        return success(flag);
    }

    @RequestMapping("/addGoodsGgclrelByGids")
    @Function(label="增加商品与评论标签关系(通过多个商品)", description = "增加商品与评论标签关系(通过多个商品)", resourse = "goods.addGoodsGgclrelByGids",parentRes="goods.openGoods")
    @ResponseBody
    public BaseResp addGoodsGgclrelByGids(String gids,Long gclId){
        boolean flag = goodsGgclrelService.saveGoodsGgclrelByGids(gids,gclId);
        return success(flag);
    }

    @RequestMapping("/saveDeleteGt")
    @Function(label="解除商品与子类关联评论关系", description = "解除商品与子类关联评论关系", resourse = "goods.saveDeleteGt",parentRes="goods.openGoods")
    @ResponseBody
    public BaseResp saveDeleteGt(String gclIds,Long gid){
        boolean flag = goodsGgclrelService.saveDeleteGt(gclIds,gid);
        return success(flag);
    }

    @RequestMapping("/selectGoodsGgclrelLists")
    @Function(label="商品与评论标签关系列表", description = "商品与评论标签关系列表", resourse = "goods.selectGoodsGgclrelLists",sort=1,parentRes="goods.openGoods")
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
