package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.BuyCar;
import com.retailers.dht.common.service.BuyCarService;
import com.retailers.dht.common.service.GoodsGdsprelService;
import com.retailers.dht.common.vo.GoodsGdsprelVo;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by niconiconi on 2017/10/30.
 */
@Controller
@RequestMapping("buyCar")
public class BuyCarController extends BaseController{
    @Autowired
    BuyCarService buyCarService;

    @RequestMapping("/gotoShoppingCar")
    public String gotoShoppingCar(HttpServletRequest request){
        return  redirectUrl(request,"user/shopping-car");
    }


    @RequestMapping("/saveBuyCar")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp saveBuyCar(BuyCar buyCar, HttpServletRequest request){
        buyCar.setIsDelete(0L);
        buyCar.setBcTimmer(new Date());
        buyCar.setUid(getCurLoginUserId(request));
        boolean flag = buyCarService.saveBuyCar(buyCar);
        return  success(flag);
    }

    @RequestMapping("/queryBuyCarList")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public Map<String,Object> queryBuyCar(HttpServletRequest request,int pageNo,int pageSize,Long isPutway){
        Long uid = getCurLoginUserId(request);
        Map params = new HashMap();
        params.put("isDelete",0L);
        params.put("uid",uid);
        params.put("isPutway",isPutway);

        List<GoodsVo> list = buyCarService.queryGoodsVoList(params,pageNo,pageSize);
        Map map = new HashMap();
        if(!ObjectUtils.isEmpty(list)){
            map.put("rows",list);
        }
        return  map;
    }

    @RequestMapping("/updateBuyCar")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp updateBuyCar(BuyCar buyCar){
        boolean flag = buyCarService.updateBuyCar(buyCar);
        return success(flag);
    }

    @RequestMapping("/deleteBuyCar")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp deleteBuyCar(String buyCarIds){
        boolean flag = buyCarService.deleteBuyCarByBcIds(buyCarIds);
        return success(flag);
    }
    
}
