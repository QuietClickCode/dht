package com.retailers.dht.web.controller;

import com.retailers.dht.common.entity.GoodsGdcprel;
import com.retailers.dht.common.service.GoodsGdcprelService;
import com.retailers.dht.common.vo.GoodsGdcprelVo;
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
@RequestMapping("openGoodsGdcprel")
public class GoodsGdcprelController extends BaseController{
    @Autowired
    GoodsGdcprelService goodsGdcprelService;

    @RequestMapping("/queryGoodsGdcprelVoList")
    @ResponseBody
    public Map<String,Object> queryGoodsGdcprelListsByGid(Long gid,String gsvalIds,Long parentId){
        Map params = new HashMap();
        params.put("gid",gid);
        params.put("parentId",parentId);
        if(!ObjectUtils.isEmpty(gsvalIds)){
            String[] gsvalIdsArr = gsvalIds.split(",");
            List<Long> l = new ArrayList<Long>();
            for(String str:gsvalIdsArr){
                Long a = Long.parseLong(str);
                l.add(a);
            }
            params.put("gsvId",l);
        }
        GoodsGdcprelVo goodsGdsprelVo = goodsGdcprelService.queryGoodsGdcprelVoLists(params);

        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("row",goodsGdsprelVo);
        return gtm;
    }
    
}
