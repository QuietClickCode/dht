package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.GoodsGtgbrel;
import com.retailers.dht.common.entity.GoodsType;
import com.retailers.dht.common.service.GoodsClassificationService;
import com.retailers.dht.common.service.GoodsGtgbrelService;
import com.retailers.dht.common.service.GoodsTypeService;
import com.retailers.dht.common.vo.GoodsGtgbrelVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("goods")
public class GoodsGtgbrelController extends BaseController {

    @Autowired
    GoodsGtgbrelService goodsGtgbrelService;
    @Autowired
    GoodsClassificationService goodsClassificationService;
    @Autowired
    GoodsTypeService goodsTypeService;

    @RequestMapping("/removeGoodsGtgbrel")
    @ResponseBody
    public BaseResp removeGoodsGtgbrel( String gtgbIds){
        boolean flag=goodsGtgbrelService.deleteGoodsGtgbrelByGtgbId(gtgbIds);
        return success(flag);
    }

    @RequestMapping("/queryGoodsGtgbrelLists")
    @ResponseBody
    public  Map<String,Object> queryGoodsGtgbrelLists(Long gtId,String gbName,Long ggId,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gbName",gbName);
        if (!ObjectUtils.isEmpty(ggId)){
            gtId = goodsClassificationService.queryGoodsClassificationByGgId(ggId).getGgHome();
            GoodsType goodsType = goodsTypeService.queryGoodsTypeByGtId(gtId);
            if (goodsType.getIsTrademark()==0) {
                Map<String,Object> gtm = new HashMap<String,Object>();
                gtm.put("rows",null);
                return gtm;
            }
            map.put("gtId",gtId);
        }else{
            map.put("gtId",gtId);
        }
        map.put("isDelete",0);
        Pagination<GoodsGtgbrelVo> GoodsGtgbrelPagination = goodsGtgbrelService.queryGoodsGtgbrelList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",GoodsGtgbrelPagination.getTotalCount());
        gtm.put("rows",GoodsGtgbrelPagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoodsGtgbrel")
    @ResponseBody
    public BaseResp addGoodsGtgbrel(String gbIds,Long gtId){
        boolean flag=goodsGtgbrelService.saveGoodsGtgbrel(gbIds,gtId);
        return success(flag);
    }

}
