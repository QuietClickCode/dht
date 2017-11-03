package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.SalePromotion;
import com.retailers.dht.common.service.SalePromotionService;
import com.retailers.dht.common.vo.SalePromotionVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/10/30.
 */
@Controller
@RequestMapping("openSalePromotion")
public class SalePromotionController extends BaseController{
    @Autowired
    SalePromotionService promotionService;

    @RequestMapping("/salePromotionMapping")
    @Menu(parentRes = "sys.manager.promotion",resourse = "openSalePromotion.salePromotionMapping",description = "特价/秒杀",label = "特价/秒杀")
    public String salePromotionMapping(){
        return "promotion/sale_promotion";
    }

    @RequestMapping("/addSalePromotion")
    @Function(label = "添加特价名单",description = "添加特价名单",resourse = "openSalePromotion.addSalePromotion",sort = 3,parentRes = "openSalePromotion.salePromotionMapping")
    @ResponseBody
    public BaseResp addSalePromotion(SalePromotion promotion){
        boolean flag = promotionService.saveSalePromotion(promotion);
        if(flag)
            return success("新增楼层成功");
        else
            return success("新增楼层失败");
    }

    @RequestMapping("/updateSalePromotion")
    @Function(label = "修改特价名单",description = "修改特价名单",resourse = "openSalePromotion.updateSalePromotion",sort = 3,parentRes = "openSalePromotion.salePromotionMapping")
    @ResponseBody
    public BaseResp updateSalePromotion(SalePromotion promotion){
        boolean flag = promotionService.updateSalePromotion(promotion);
        if(flag)
            return success("修改首页导航[" + promotion.getGoodsName() + "]成功");
        else
            return errorForSystem("修改首页导航[" + promotion.getGoodsName() + "]失败");
    }

    @RequestMapping("/removeSalePromotion")
    @Function(label = "删除特价名单",description = "删除特价名单",resourse = "openSalePromotion.removeSalePromotion",sort = 3,parentRes = "openSalePromotion.salePromotionMapping")
    @ResponseBody
    public BaseResp removeSalePromotion(Long spId){
        boolean flag = promotionService.deleteSalePromotionBySpId(spId);
        return  success(flag);
    }

    @RequestMapping("/querySalePromotionLists")
    @Function(label="特价名单集合", description = "特价名单集合", resourse = "openSalePromotion.querySalePromotionLists",sort=1,parentRes="openSalePromotion.salePromotionMapping")
    @ResponseBody
    public Map<String,Object> querySalePromotionLists(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        Pagination<SalePromotionVo> advertisingPagination = promotionService.querySalePromotionList(map,1,10);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",1000);
        List<SalePromotionVo> promotionVos = promotionService.querySalePromotionTree(advertisingPagination.getData());
        gtm.put("rows",promotionVos);
        return gtm;
    }
}
