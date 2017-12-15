package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.CutPrice;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.service.CutPriceService;
import com.retailers.dht.common.vo.CutPriceVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
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
@RequestMapping("openCutPrice")
public class CutPriceController extends BaseController{
    @Autowired
    CutPriceService cutPriceService;

    @RequestMapping("/queryCutPriceListsByGid")
    @ResponseBody
    public Map<String,Object> queryCutPriceListsByGid(Long gid){
        Map params = new HashMap();
        params.put("gid",gid);
        List<CutPriceVo> list = cutPriceService.queryCutPriceListsByGid(params);
        Map gtm = new HashMap();
        if(!ObjectUtils.isEmpty(list)){
            gtm.put("rows",list);
        }
        return gtm;
    }

    @RequestMapping("/queryCutPriceGoodsList")
    @ResponseBody
    public Map<String,Object> queryCutPriceList(Long cpRegion,int pageNo,int pageSize){
        Map params = new HashMap();
        params.put("cpRegion",cpRegion);
        List<CutPriceVo> list = cutPriceService.queryCutPriceGoodsList(params,pageNo,pageSize).getData();
        Map gtm = new HashMap();
        if(!ObjectUtils.isEmpty(list)){
            gtm.put("rows",list);
        }
        return gtm;
    }

    @RequestMapping("/queryNextCutPriceGoodsList")
    @ResponseBody
    public Map<String,Object> queryNextCutPriceGoodsList(Long cpRegion,int pageNo,int pageSize){
        Map params = new HashMap();
        params.put("cpRegion",cpRegion);
        List<CutPriceVo> list = cutPriceService.queryNextCutPriceGoodsList(params,pageNo,pageSize).getData();
        Map gtm = new HashMap();
        if(!ObjectUtils.isEmpty(list)){
            gtm.put("rows",list);
        }
        return gtm;
    }
}
