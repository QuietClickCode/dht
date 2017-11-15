package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.SalePromotion;
import com.retailers.dht.common.service.SalePromotionService;
import com.retailers.dht.common.vo.SalePromotionVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.omg.CORBA.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/10/30.
 */
@Controller
@RequestMapping("openSalePromotion")
public class SalePromotionController extends BaseController{
    @Autowired
    SalePromotionService promotionService;

    @RequestMapping("/querySalePromotionLists")
    @ResponseBody
    public Map<String,Object> querySalePromotionLists(String now,Long spType,Long gid){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        if(!ObjectUtils.isEmpty(now)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                Date nowDate = sdf.parse(now);
                map.put("nowDate",nowDate);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            return null;
        }
        map.put("spType",spType);
        map.put("gid",gid);
        Pagination<SalePromotionVo> advertisingPagination = promotionService.querySalePromotionListWeb(map,1,2);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("rows",advertisingPagination.getData());
        return gtm;
    }


}
