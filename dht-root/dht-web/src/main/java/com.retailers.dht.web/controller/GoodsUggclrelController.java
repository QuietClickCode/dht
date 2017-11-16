package com.retailers.dht.web.controller;

import com.retailers.dht.common.entity.GoodsConfig;
import com.retailers.dht.common.entity.GoodsUggclrel;
import com.retailers.dht.common.service.GoodsConfigService;
import com.retailers.dht.common.service.GoodsUggclrelService;
import com.retailers.dht.common.vo.GoodsUggclrelVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
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
@RequestMapping("goodsUggclrel")
public class GoodsUggclrelController extends BaseController {

    @Autowired
    GoodsUggclrelService goodsUggclrelService;

    @RequestMapping("/queryGoodsUggclrel")
    @ResponseBody
    public  Map<String,Object> queryGoodsUggclrel(Long gid,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gid",gid);
        map.put("isDelete",0L);
        Pagination<GoodsUggclrelVo> pagination = goodsUggclrelService.queryGoodsUggclrelListVo(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        if(!ObjectUtils.isEmpty(pagination.getData())){
            gtm.put("rows",pagination.getData());
        }
        return gtm;
    }

    @RequestMapping("/queryGoodsclsumandavg")
    @ResponseBody
    public  Map<String,Object> queryGoodsclsumandavg(Long gid){
        GoodsUggclrelVo goodsUggclrelVo = goodsUggclrelService.queryGoodsclsumandavg(gid);
        Map<String,Object> gtm = new HashMap<String,Object>();
        if(!ObjectUtils.isEmpty(goodsUggclrelVo)){
            gtm.put("row",goodsUggclrelVo);
        }
        return gtm;
    }

    @RequestMapping("/queryHasGoodscls")
    @ResponseBody
    public  Map<String,Object> queryHasGoodscls(Long gid){
        List<GoodsUggclrelVo> list = goodsUggclrelService.queryHasGoodscls(gid);
        Map<String,Object> gtm = new HashMap<String,Object>();
        if(!ObjectUtils.isEmpty(list)){
            gtm.put("rows",list);
        }
        return gtm;
    }
}
