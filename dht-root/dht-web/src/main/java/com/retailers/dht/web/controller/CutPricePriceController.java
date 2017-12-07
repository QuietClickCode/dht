package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.CutPricePrice;
import com.retailers.dht.common.service.CutPricePriceService;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/10/30.
 */
@Controller
@RequestMapping("openCutPricePrice")
public class CutPricePriceController extends BaseController{
    @Autowired
    CutPricePriceService cutPricePriceService;

    @RequestMapping("/saveCutPricePrice")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp queryCutPriceListsByGid(CutPricePrice cutPricePrice, HttpServletRequest request){
        cutPricePrice.setIsDelete(0L);
        cutPricePrice.setUsId(getCurLoginUserId(request));
        boolean flag = cutPricePriceService.saveCutPricePrice(cutPricePrice);
        return success(flag);
    }
    @RequestMapping("/queryCutPricePrice")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public Map<String,Object> queryCutPricePrice(Long gid, HttpServletRequest request){
        Long uid = getCurLoginUserId(request);
        Map params = new HashMap();
        params.put("gid",gid);
        params.put("uid",uid);
        Pagination<CutPricePrice> pagination = cutPricePriceService.queryCutPricePriceList(params,1,1);
        return queryPages(pagination);
    }
}
