package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.dht.common.entity.GoodsGgsvalDetail;
import com.retailers.dht.common.service.GoodsGgsvalDetailService;
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
public class GoodsGgsvalDetailController extends BaseController {

    @Autowired
    GoodsGgsvalDetailService goodsGgsvalDetailService;


    @RequestMapping("/removeGoodsGgsvalDetail")
    @ResponseBody
    public BaseResp removeGoodsGgsvalDetail(Long ggsId){
        boolean flag=goodsGgsvalDetailService.deleteGoodsGgsvalDetailByGgdId(ggsId);
        return success(flag);
    }

    @RequestMapping("/queryGoodsGgsvalDetailLists")
    @ResponseBody
    public  Map<String,Object> queryGoodsGgsvalDetailLists(Long gsvId,Long gid,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        System.out.println("gid:"+gid);
        System.out.println("gsvId:"+gsvId);
        map.put("gid",gid);
        map.put("gsvId",gsvId);
        map.put("isDelete",0);
        Pagination<GoodsGgsvalDetail> GoodsGgsvalDetailPagination = goodsGgsvalDetailService.queryGoodsGgsvalDetailList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",GoodsGgsvalDetailPagination.getTotalCount());
        gtm.put("rows",GoodsGgsvalDetailPagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoodsGgsvalDetail")
    @ResponseBody
    public BaseResp addGoodsGgsvalDetail(GoodsGgsvalDetail goodsGgsvalDetail){
        goodsGgsvalDetail.setIsDelete(0L);
        boolean flag=goodsGgsvalDetailService.saveGoodsGgsvalDetail(goodsGgsvalDetail);
        return success(flag);
    }

    @RequestMapping("/clearAllGgsrel")
    @ResponseBody
    public BaseResp clearAllGgsrel(Long gid, HttpServletRequest request){
        goodsGgsvalDetailService.clearAllGgsrel(gid,getCurLoginUserId(request));
        return success(true);
    }

}
