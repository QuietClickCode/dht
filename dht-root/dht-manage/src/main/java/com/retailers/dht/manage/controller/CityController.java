package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.City;
import com.retailers.dht.common.service.CityService;
import com.retailers.dht.manage.base.BaseController;
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
@RequestMapping("city")
public class CityController extends BaseController {

    @Autowired
    CityService cityService;

    @RequestMapping("/queryCityLists")
    @ResponseBody
    public  Map<String,Object> queryCityLists(){
        Map<String,Object> map = new HashMap<String,Object>();
        Pagination<City> CityPagination = cityService.queryCityList(map,1,100);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",CityPagination.getTotalCount());
        gtm.put("rows",CityPagination.getData());
        return gtm;
    }

}
