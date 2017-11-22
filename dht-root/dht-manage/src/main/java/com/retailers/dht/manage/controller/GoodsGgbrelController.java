package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.dht.common.service.GoodsGgbrelService;
import com.retailers.dht.common.vo.GoodsGgbrelVo;
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
public class GoodsGgbrelController extends BaseController {

    @Autowired
    GoodsGgbrelService goodsGgbrelService;

    @RequestMapping("/removeGoodsGgbrel")
    @Function(label="删除商品与品牌关系", description = "删除商品大类与品牌关系", resourse = "goods.removeGoodsGgbrel",sort=3,parentRes="goods.openGoods")
    @ResponseBody
    public BaseResp removeGoodsGgbrel( String ggbIds){
        boolean flag=goodsGgbrelService.deleteGoodsGgbrelByGgbId(ggbIds);
        return success(flag);
    }

    @RequestMapping("/queryGoodsGgbrelLists")
    @ResponseBody
    public  Map<String,Object> queryGoodsGgbrelLists(Long gid,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gid",gid);
        map.put("isDelete",0);
        Pagination<GoodsGgbrelVo> GoodsGgbrelPagination = goodsGgbrelService.queryGoodsGgbrelList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",GoodsGgbrelPagination.getTotalCount());
        gtm.put("rows",GoodsGgbrelPagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoodsGgbrel")
    @ResponseBody
    public BaseResp addGoodsGgbrel(String gbIds,Long gid){
        boolean flag=goodsGgbrelService.saveGoodsGgbrel(gbIds,gid);
        return success(flag);
    }

}
