package com.retailers.dht.web.controller;
import com.retailers.dht.common.service.HomeAdvertisingService;
import com.retailers.dht.common.vo.HomeAdvertisingVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niconiconi on 2017/10/18.
 */
@Controller
@RequestMapping("openHomeAdvertising")
public class HomeAdvertisingController extends BaseController {
    @Autowired
    HomeAdvertisingService advertisingService;

    @RequestMapping("/queryAdvertisingLists")
    @ResponseBody
    public Map<String,Object> queryNavigationLists(String haName,Long haClient,Long haCountry,Long isShow,Long haRegion,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        map.put("haName",haName);
        map.put("haClient", haClient);
        map.put("haCountry", haCountry);
        map.put("haRegion",haRegion);
        map.put("isShow",isShow);
        Pagination<HomeAdvertisingVo> advertisingPagination = advertisingService.queryHomeAdvertisingList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",advertisingPagination.getPageSize());
        gtm.put("rows",advertisingPagination.getData());
        return gtm;
    }
}
