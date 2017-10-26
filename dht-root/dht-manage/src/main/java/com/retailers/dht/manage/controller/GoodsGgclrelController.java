package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.entity.GoodsClassification;
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
    public BaseResp removeGoodsGgclrel(String ggclIds, HttpServletRequest request){
        boolean flag=goodsGgclrelService.deleteGoodsGgclrelByGgclId(ggclIds,getCurLoginUserId(request));
        return success(flag);
    }

    @RequestMapping("/queryGoodsGgclrelLists")
    @Function(label="商品与评论标签关系列表", description = "商品与评论标签关系列表", resourse = "goods.queryGoodsGgclrelLists",sort=1,parentRes="goods.openGoods")
    @ResponseBody
    public  Map<String,Object> queryGoodsGgclrelLists(Long gid,Long level,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gid",gid);
        map.put("gclLevel",level);
        map.put("isDelete",0);
        Goods goods = goodsService.queryGoodsByGid(gid);
        GoodsClassification goodsClassification = goodsClassificationService.queryGoodsClassificationByGgId(goods.getGclassification());
        GoodsType goodsType = goodsTypeService.queryGoodsTypeByGtId(goodsClassification.getGgHome());
        Long isParams = goodsType.getIsParams();
        if(isParams==0){
            Map<String,Object> gtm = new HashMap<String,Object>();
            gtm.put("rows",null);
            return gtm;
        }
        Pagination<GoodsGgclrelVo> GoodsGgclrelPagination = goodsGgclrelService.queryGoodsGgclrelList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",GoodsGgclrelPagination.getTotalCount());
        gtm.put("rows",GoodsGgclrelPagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoodsGgclrel")
    @Function(label="增加商品大类与评论标签关系", description = "增加商品大类与评论标签关系", resourse = "goods.addGoodsGgclrel",parentRes="goods.openGoods")
    @ResponseBody
    public BaseResp addGoodsGgclrel(String gclIds,Long level,Long gid,HttpServletRequest request){
        boolean flag=goodsGgclrelService.saveGoodsGgclrel(gclIds,gid,level,getCurLoginUserId(request));
        return success(flag);
    }

}
