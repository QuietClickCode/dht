package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.GoodsFreight;
import com.retailers.dht.common.service.GoodsFreightService;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("goodsFreight")
public class GoodsFreightController extends BaseController {

    @Autowired
    GoodsFreightService goodsFreightService;
    @RequestMapping("/queryFreightByAddress")
    @ResponseBody
    public Map<String,Object> queryFreightByAddress(String address){
        GoodsFreight goodsFreight = goodsFreightService.queryFreightByAddress(address);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("row",goodsFreight);
        return gtm;
    }
}
