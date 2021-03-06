package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.GoodsSpecification;
import com.retailers.dht.common.service.GoodsSpecificationService;
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
public class GoodsSpecificationController extends BaseController {

    @Autowired
    GoodsSpecificationService goodsSpecificationService;

    @RequestMapping("/openGoodsSpecification")
    @Menu(parentRes = "sys.manager.goods",resourse = "goods.openGoodsSpecification",description = "商品规格管理",sort = 1,label = "商品规格管理")
    public String openGoodsSpecification(){
        return "goods/goodsSpecification";
    }

    @RequestMapping("editGoodsSpecification")
    @Function(label = "编辑商品规格",parentRes = "goods.openGoodsSpecification",resourse = "goods.editGoodsSpecification",description = "编辑商品规格",sort = 2)
    @ResponseBody
    public BaseResp editGoodsSpecification(GoodsSpecification goodsSpecification){
        boolean flag = goodsSpecificationService.updateGoodsSpecification(goodsSpecification);
        if(flag){
            return success("修改商品大类["+goodsSpecification.getGsName()+"]成功");
        }else{
            return errorForSystem("修改商品大类["+goodsSpecification.getGsName()+"]失败");
        }
    }

    @RequestMapping("/removeGoodsSpecification")
    @Function(label="删除商品规格", description = "删除商品规格", resourse = "goods.removeGoodsSpecification",sort=3,parentRes="goods.openGoodsSpecification")
    @ResponseBody
    public BaseResp removeGoodsSpecification(Long gsId){
        boolean flag=goodsSpecificationService.deleteGoodsSpecificationByGsId(gsId);
        return success(flag);
    }

    @RequestMapping("/queryGoodsSpecificationLists")
    @Function(label="商品规格列表", description = "所有商品规格列表", resourse = "goods.queryGoodsSpecificationLists",sort=1,parentRes="goods.openGoodsSpecification")
    @ResponseBody
    public  Map<String,Object> queryGoodsSpecificationLists(String gsName,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gsName",gsName);
        map.put("isDelete",0);
        Pagination<GoodsSpecification> GoodsSpecificationPagination = goodsSpecificationService.queryGoodsSpecificationList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",GoodsSpecificationPagination.getTotalCount());
        gtm.put("rows",GoodsSpecificationPagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoodsSpecification")
    @Function(label="增加商品规格", description = "增加商品规格", resourse = "goods.addGoodsSpecification",parentRes="goods.openGoodsSpecification")
    @ResponseBody
    public Map<String,Object> addGoodsSpecification(GoodsSpecification goodsSpecification){
        goodsSpecification.setIsDelete(0L);
        goodsSpecification = goodsSpecificationService.saveGoodsSpecification(goodsSpecification);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("goodsSpecification",goodsSpecification);
        return gtm;
    }

}
