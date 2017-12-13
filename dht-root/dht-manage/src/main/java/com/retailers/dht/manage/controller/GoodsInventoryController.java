package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.GoodsCgfrel;
import com.retailers.dht.common.service.GoodsCgfrelService;
import com.retailers.dht.common.service.GoodsGgsvalDetailService;
import com.retailers.dht.common.service.GoodsGgsvalService;
import com.retailers.dht.common.vo.GoodsInventoryVo;
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
public class GoodsInventoryController extends BaseController {

    @Autowired
    GoodsGgsvalDetailService goodsGgsvalDetailService;

    @RequestMapping("/openGoodsInventory")
    @Menu(parentRes = "sys.manager.goods",resourse = "goods.openGoodsInventory",description = "商品库存管理",label = "商品库存管理")
    public String floorManage(){
        return "goods/goodsInventory";
    }

    @RequestMapping("/queryGoodsInventoryLists")
    @Function(label="商品库存列表", description = "商品库存列表", resourse = "goods.queryGoodsInventoryLists",sort=1,parentRes="goods.openGoodsInventory")
    @ResponseBody
    public  Map<String,Object> queryGoodsCgfrelLists(String gname,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gname",gname);
        Pagination<GoodsInventoryVo> pagination = goodsGgsvalDetailService.queryGoodsInventoryLists(map,pageForm.getPageNo(),pageForm.getPageSize());
        return queryPages(pagination);
    }

    @RequestMapping("/editGoodsInventory")
    @Function(label="编辑库存", description = "编辑库存", resourse = "goods.editGoodsInventory",sort=1,parentRes="goods.openGoodsInventory")
    @ResponseBody
    public  BaseResp editGoodsInventory(Long gdId, Long inventory, HttpServletRequest request){
        Long uid = getCurLoginUserId(request);
        boolean flag = goodsGgsvalDetailService.editGoodsInventory(gdId,inventory,uid);
        return success(flag);
    }
}
