package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.GoodsFreight;
import com.retailers.dht.common.service.GoodsFreightService;
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
public class GoodsFreightController extends BaseController {

    @Autowired
    GoodsFreightService goodsFreightService;

    @RequestMapping("/openGoodsFreight")
    @Menu(parentRes = "sys.manager.goods",resourse = "goods.openGoodsFreight",description = "商品运费管理",sort = 1,label = "商品运费管理")
    public String openGoodsFreight(){
        return "goods/GoodsFreight";
    }

    @RequestMapping("editGoodsFreight")
    @Function(label = "编辑商品运费",parentRes = "goods.openGoodsFreight",resourse = "goods.editGoodsFreight",description = "编辑商品运费",sort = 2)
    @ResponseBody
    public BaseResp editGoodsFreight(GoodsFreight goodsFreight){
        boolean flag = goodsFreightService.updateGoodsFreight(goodsFreight);
        if(flag){
            return success("修改商品大类["+goodsFreight.getGfName()+"]成功");
        }else{
            return errorForSystem("修改商品大类["+goodsFreight.getGfName()+"]失败");
        }
    }

    @RequestMapping("/removeGoodsFreight")
    @Function(label="删除商品运费", description = "删除商品运费", resourse = "goods.removeGoodsFreight",sort=3,parentRes="goods.openGoodsFreight")
    @ResponseBody
    public BaseResp removeGoodsFreight(Long gfId){
        boolean flag=goodsFreightService.deleteGoodsFreightByGfId(gfId);
        return success(flag);
    }

    @RequestMapping("/queryGoodsFreightLists")
    @Function(label="商品运费列表", description = "所有商品运费列表", resourse = "goods.queryGoodsFreightLists",sort=1,parentRes="goods.openGoodsFreight")
    @ResponseBody
    public  Map<String,Object> queryGoodsFreightLists(String gfName,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gfName",gfName);
        map.put("isDelete",0);
        Pagination<GoodsFreight> GoodsFreightPagination = goodsFreightService.queryGoodsFreightList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",GoodsFreightPagination.getTotalCount());
        gtm.put("rows",GoodsFreightPagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoodsFreight")
    @Function(label="增加商品运费", description = "增加商品运费", resourse = "goods.addGoodsFreight",parentRes="goods.openGoodsFreight")
    @ResponseBody
    public BaseResp addGoodsFreight(GoodsFreight goodsFreight){
        goodsFreight.setIsDelete(0L);
        boolean flag = goodsFreightService.saveGoodsFreight(goodsFreight);
        return success(flag);
    }

}
