package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.annotation.Function;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.City;
import com.retailers.dht.common.service.CityService;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
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

    @RequestMapping("/queryCityListsByCityName")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirect = "http://www.baidu.com")
    @ResponseBody
    public  Map<String,Object> queryCityLists(String cityname){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("cityname",cityname);
        Pagination<City> CityPagination = cityService.queryCityList(map,1,100);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",CityPagination.getTotalCount());
        if(!ObjectUtils.isEmpty(CityPagination.getData())){
            gtm.put("row",CityPagination.getData().get(0));
        }
        return gtm;
    }

}
