package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.dht.common.entity.GoodsConfig;
import com.retailers.dht.common.service.GoodsConfigService;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("goodsConfig")
public class GoodsConfigController extends BaseController {

    @Autowired
    GoodsConfigService goodsConfigService;

    @RequestMapping("/queryGoodsConfigBygid")
    @ResponseBody
    public  Map<String,Object> queryGoodsConfigLists(Long gid,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gid",gid);
        Pagination<GoodsConfig> goodsConfigPagination = goodsConfigService.queryGoodsConfigList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        if(!ObjectUtils.isEmpty(goodsConfigPagination.getData())){
            gtm.put("goodsConfig",goodsConfigPagination.getData().get(0));
        }
        return gtm;
    }


}
