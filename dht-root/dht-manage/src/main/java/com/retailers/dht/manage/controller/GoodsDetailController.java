package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.dht.common.entity.GoodsDetail;
import com.retailers.dht.common.service.GoodsDetailService;
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
public class GoodsDetailController extends BaseController {

    @Autowired
    GoodsDetailService goodsDetailService;


    @RequestMapping("/queryGoodsDetailById")
    @Function(label="商品详情列表", description = "商品详情列表", resourse = "goods.queryGoodsDetailLists",sort=1,parentRes = "goods.openGoods")
    @ResponseBody
    public  Map<String,Object> queryGoodsDetailLists(Long gdId){
        GoodsDetail goodsDetail = goodsDetailService.queryGoodsDetailByGdId(gdId);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("goodsDetail",goodsDetail);
        return gtm;
    }

    @RequestMapping("/addGoodsDetail")
    @Function(label="增加详情", description = "增加商品详情", resourse = "goods.addGoodsDetail",parentRes = "goods.openGoods")
    @ResponseBody
    public Map<String,Object> addGoodsDetail(GoodsDetail goodsDetail){
        goodsDetail.setIsDelete(0L);
        goodsDetail=goodsDetailService.saveGoodsDetail(goodsDetail);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("goodsDetail",goodsDetail);
        System.out.println(goodsDetail.getGdId());
        return gtm;
    }



}
