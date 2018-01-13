package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.GoodsGsval;
import com.retailers.dht.common.service.GoodsGsvalService;
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
public class GoodsGsvalController extends BaseController {

    @Autowired
    GoodsGsvalService goodsGsvalService;

    @RequestMapping("editGoodsGsval")
    @ResponseBody
    public BaseResp editGoodsGsval(GoodsGsval goodsGsval){
        goodsGsval.setVersion(goodsGsvalService.queryGoodsGsvalByGsvId(goodsGsval.getGsvId()).getVersion());
        boolean flag = goodsGsvalService.updateGoodsGsval(goodsGsval);
        if(flag){
            return success("修改商品規格值成功");
        }else{
            return errorForSystem("修改商品規格值失败");
        }
    }

    @RequestMapping("/removeGoodsGsval")
    @ResponseBody
    public BaseResp removeGoodsGsval(Long gsvId){
        boolean flag=goodsGsvalService.deleteGoodsGsvalByGsvId(gsvId);
        return success(flag);
    }

    @RequestMapping("/queryGoodsGsvalLists")
    @ResponseBody
    public  Map<String,Object> queryGoodsGsvalLists(Long gsId,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gsId",gsId);
        map.put("isDelete",0);
        map.put("gid",-1);
        Pagination<GoodsGsval> GoodsGsvalPagination = goodsGsvalService.queryGoodsGsvalList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",GoodsGsvalPagination.getTotalCount());
        gtm.put("rows",GoodsGsvalPagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoodsGsval")
    @ResponseBody
    public Map addGoodsGsval(GoodsGsval goodsGsval){
        goodsGsval.setIsDelete(0L);
        goodsGsval=goodsGsvalService.saveGoodsGsval(goodsGsval);
        Map map = new HashMap();
        map.put("row",goodsGsval);
        return map;
    }

}
