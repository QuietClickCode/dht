package com.retailers.dht.manage.controller;

import com.retailers.dht.common.entity.GoodsGdcprel;
import com.retailers.dht.common.service.GoodsGdcprelService;
import com.retailers.dht.common.vo.GoodsGdcprelVo;
import com.retailers.dht.manage.base.BaseController;
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

    @RequestMapping("/addGoodsGdcprel")
    @ResponseBody
    public BaseResp addGoodsGdcprel(GoodsGdcprel GoodsGdcprel){
        GoodsGdcprel.setIsDelete(0L);
        boolean flag = goodsGdcprelService.saveGoodsGdcprel(GoodsGdcprel);
        return success(flag);
    }

    @RequestMapping("/addGoodsGdcprelByJson")
    @ResponseBody
    public BaseResp addGoodsGdcprelByJson(String data){
        boolean flag = goodsGdcprelService.saveGoodsGdcprelByJson(data);
        return success(flag);
    }

    @RequestMapping("/updateGoodsGdcprel")
    @ResponseBody
    public BaseResp updateGoodsGdcprel(GoodsGdcprel GoodsGdcprel){
        boolean flag = goodsGdcprelService.updateGoodsGdcprel(GoodsGdcprel);
        if(flag)
            return success("修改成功");
        else
            return errorForSystem("修改失败");
    }

    @RequestMapping("/queryGoodsGdcprelListsByGid")
    @ResponseBody
    public Map<String,Object> queryGoodsGdcprelListsByGid(Long gid,Long cpId){
        List<GoodsGdcprelVo> list = new ArrayList<GoodsGdcprelVo>();
        if(!ObjectUtils.isEmpty(gid)){
            list = goodsGdcprelService.queryGoodsGdcprelListsByGid(gid,cpId);
        }
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("rows",list);
        return gtm;
    }
    
}
