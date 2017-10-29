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
public class GoodsGgcrelController extends BaseController {

    @Autowired
    GoodsGgcrelService goodsGgcrelService;

    @RequestMapping("/removeGoodsGgcrel")
    @Function(label="删除商品与赠品关系", description = "删除商品大类与赠品关系", resourse = "goods.removeGoodsGgcrel",sort=3,parentRes="goods.openGoods")
    @ResponseBody
    public BaseResp removeGoodsGgcrel(String ggcIds){
        boolean flag=goodsGgcrelService.deleteGoodsGgcrelByGgcId(ggcIds);
        return success(flag);
    }

    @RequestMapping("/queryGoodsGgcrelLists")
    @Function(label="商品与赠品关系列表", description = "商品与赠品关系列表", resourse = "goods.queryGoodsGgcrelLists",sort=1,parentRes="goods.openGoods")
    @ResponseBody
    public  Map<String,Object> queryGoodsGgcrelLists(Long gid,Long level,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gid",gid);
        map.put("isDelete",0);
        Pagination<GoodsGgcrelVo> GoodsGgcrelPagination = goodsGgcrelService.queryGoodsGgcrelList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",GoodsGgcrelPagination.getTotalCount());
        gtm.put("rows",GoodsGgcrelPagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoodsGgcrel")
    @Function(label="增加商品大类与赠品关系", description = "增加商品大类与赠品关系", resourse = "goods.addGoodsGgcrel",parentRes="goods.openGoods")
    @ResponseBody
    public BaseResp addGoodsGgcrel(String ggcIds,Long gid){
        boolean flag=goodsGgcrelService.saveGoodsGgcrel(ggcIds,gid);
        return success(flag);
    }

}
