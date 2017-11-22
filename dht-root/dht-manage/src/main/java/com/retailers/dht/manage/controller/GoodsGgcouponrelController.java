package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.dht.common.service.GoodsGgcouponrelService;
import com.retailers.dht.common.vo.GoodsGgcouponrelVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("goods")
public class GoodsGgcouponrelController extends BaseController {

    @Autowired
    GoodsGgcouponrelService goodsGgcouponrelService;

    @RequestMapping("/removeGoodsGgcouponrel")
    @ResponseBody
    public BaseResp removeGoodsGgcouponrel(String ggcIds){
        boolean flag=goodsGgcouponrelService.deleteGoodsGgcouponrelByGgcId(ggcIds);
        return success(flag);
    }

    @RequestMapping("/queryGoodsGgcouponrelLists")
    @ResponseBody
    public  Map<String,Object> queryGoodsGgcouponrelLists(Long gid,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gid",gid);
        map.put("isDelete",0);
        Pagination<GoodsGgcouponrelVo> GoodsGgcouponrelPagination = goodsGgcouponrelService.queryGoodsGgcouponrelList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",GoodsGgcouponrelPagination.getTotalCount());
        gtm.put("rows",GoodsGgcouponrelPagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoodsGgcouponrel")
    @ResponseBody
    public BaseResp addGoodsGgcouponrel(String gcIds,Long gid){
        boolean flag = goodsGgcouponrelService.saveGoodsGgcouponrel(gcIds,gid);
        return success(flag);
    }



}
