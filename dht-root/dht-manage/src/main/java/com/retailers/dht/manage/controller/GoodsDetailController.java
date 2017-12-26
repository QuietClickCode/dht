package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.dht.common.entity.GoodsDetail;
import com.retailers.dht.common.service.GoodsDetailService;
import com.retailers.dht.common.service.GoodsGgsvalDetailService;
import com.retailers.dht.common.service.GoodsGgsvalService;
import com.retailers.dht.common.vo.GoodsDetailVo;
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
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("goods")
public class GoodsDetailController extends BaseController {

    @Autowired
    GoodsDetailService goodsDetailService;
    @Autowired
    GoodsGgsvalDetailService goodsGgsvalDetailService;

    @RequestMapping("/queryGoodsDetailById")
    @ResponseBody
    public  Map<String,Object> queryGoodsDetailLists(Long gdId){
        GoodsDetail goodsDetail = goodsDetailService.queryGoodsDetailByGdId(gdId);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("goodsDetail",goodsDetail);
        return gtm;
    }

    @RequestMapping("/addGoodsDetail")
    @ResponseBody
    public Map<String,Object> addGoodsDetail(GoodsDetail goodsDetail,HttpServletRequest request){
        Long uid = getCurLoginUserId(request);
        goodsDetail.setIsDelete(0L);
        goodsDetail=goodsDetailService.saveGoodsDetail(goodsDetail,uid);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("goodsDetail",goodsDetail);
        return gtm;
    }

    @RequestMapping("/queryGoodsDetailOnce")
    @ResponseBody
    public  Map<String,Object> queryGoodsDetailOnce(Long gid){
        List<GoodsDetailVo> lsit = goodsDetailService.queryGoodsDetailOnce(gid);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("rows",lsit);
        return gtm;
    }

    @RequestMapping("/addMyData")
    @ResponseBody
    public BaseResp addMyData(String uploaddata,Long gid, HttpServletRequest request){
        Long uid = getCurLoginUserId(request);
        goodsGgsvalDetailService.clearAllGgsrel(gid,getCurLoginUserId(request));
        boolean flag = goodsDetailService.addMyData(uploaddata,gid,uid);
        return success(flag);
    }
}
