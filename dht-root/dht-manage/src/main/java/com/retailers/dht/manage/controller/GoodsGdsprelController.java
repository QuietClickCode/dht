package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.dht.common.entity.GoodsGdsprel;
import com.retailers.dht.common.service.GoodsGdsprelService;
import com.retailers.dht.common.vo.GoodsGdsprelVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/10/30.
 */
@Controller
@RequestMapping("openGoodsGdsprel")
public class GoodsGdsprelController extends BaseController{
    @Autowired
    GoodsGdsprelService goodsGdsprelService;

    @RequestMapping("/addGoodsGdsprel")
    @Function(label = "添加特价与商品详情关系",description = "添加特价与商品详情关系",resourse = "openGoodsGdsprel.addGoodsGdsprel",sort = 3,parentRes = "openSalePromotion.salePromotionMapping")
    @ResponseBody
    public BaseResp addGoodsGdsprel(GoodsGdsprel goodsGdsprel){
        goodsGdsprel.setIsDelete(0L);
        boolean flag = goodsGdsprelService.saveGoodsGdsprel(goodsGdsprel);
        if(flag)
            return success("新增成功");
        else
            return success("新增失败");
    }

    @RequestMapping("/updateGoodsGdsprel")
    @Function(label = "修改特价与商品详情关系",description = "修改特价与商品详情关系",resourse = "openGoodsGdsprel.updateGoodsGdsprel",sort = 3,parentRes = "openSalePromotion.salePromotionMapping")
    @ResponseBody
    public BaseResp updateGoodsGdsprel(GoodsGdsprel goodsGdsprel){
        boolean flag = goodsGdsprelService.updateGoodsGdsprel(goodsGdsprel);
        if(flag)
            return success("修改成功");
        else
            return errorForSystem("修改失败");
    }

    @RequestMapping("/queryGoodsGdsprelListsByGid")
    @Function(label="特价与商品详情关系集合", description = "特价与商品详情关系集合", resourse = "openGoodsGdsprel.queryGoodsGdsprelListsByGid",sort=1,parentRes="openSalePromotion.salePromotionMapping")
    @ResponseBody
    public Map<String,Object> queryGoodsGdsprelListsByGid(Long gid){
        List<GoodsGdsprelVo> list = new ArrayList<GoodsGdsprelVo>();
        if(!ObjectUtils.isEmpty(gid)){
            list = goodsGdsprelService.queryGoodsGdsprelListsByGid(gid);
        }
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("rows",list);
        return gtm;
    }
    
}
