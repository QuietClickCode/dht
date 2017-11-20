package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.dht.common.entity.GoodsGdsprel;
import com.retailers.dht.common.service.GoodsGdsprelService;
import com.retailers.dht.common.vo.GoodsGdsprelVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/10/30.
 */
@Controller
@RequestMapping("openGoodsGdsprel")
public class GoodsGdsprelController extends BaseController{
    @Autowired
    GoodsGdsprelService goodsGdsprelService;

    @RequestMapping("/queryGoodsGdsprelVoLists")
    @ResponseBody
    public Map<String,Object> queryGoodsGdsprelListsByGid(Long gid,String gsvalIds){
        Map params = new HashMap();
        params.put("gid",gid);
        if(!ObjectUtils.isEmpty(gsvalIds)){
            String[] gsvalIdsArr = gsvalIds.split(",");
            List<Long> l = new ArrayList<Long>();
            for(String str:gsvalIdsArr){
                Long a = Long.parseLong(str);
                l.add(a);
            }
            params.put("gsvId",l);
        }
        GoodsGdsprelVo goodsGdsprelVo = goodsGdsprelService.queryGoodsGdsprelVoLists(params);

        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("row",goodsGdsprelVo);
        return gtm;
    }
    
}
