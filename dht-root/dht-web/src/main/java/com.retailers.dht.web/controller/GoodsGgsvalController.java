package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.dht.common.entity.GoodsGgsval;
import com.retailers.dht.common.service.GoodsGgsvalService;
import com.retailers.dht.common.vo.GoodsGgsvalOnceVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
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
@RequestMapping("goodsGgsrel")
public class GoodsGgsvalController extends BaseController {
    @Autowired
    GoodsGgsvalService goodsGgsvalService;

    @RequestMapping("/queryGgsrelListsOnce")
    @ResponseBody
    public  Map<String,Object> queryGgsrelListsOnce(Long gid){
        List<GoodsGgsvalOnceVo> list = goodsGgsvalService.queryGgsrelListsOnce(gid);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("rows",list);
        return gtm;
    }

}
