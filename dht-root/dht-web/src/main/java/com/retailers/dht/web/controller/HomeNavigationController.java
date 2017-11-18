package com.retailers.dht.web.controller;

import com.retailers.dht.common.service.HomeNavigationService;
import com.retailers.dht.common.vo.HomeNavigationVo;
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
 * Created by niconiconi on 2017/10/16.
 */
@Controller
@RequestMapping("openHomeNavigation")
public class HomeNavigationController extends BaseController {
    @Autowired
    HomeNavigationService homeNavigationService;

    @RequestMapping("/queryNavigationLists")
    @ResponseBody
    public Map<String,Object> queryNavigationLists(String hnName,Long hnStyle,Long hnClient,Long hnCountry, PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("hnStyle",hnStyle);
        map.put("hnClient",hnClient);
        map.put("hnCountry",hnCountry);
        map.put("hnName",hnName);
        map.put("isDelete",0);
        Pagination<HomeNavigationVo> goodsTypePagination = homeNavigationService.queryHomeNavigationList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",goodsTypePagination.getTotalCount());
        gtm.put("rows",goodsTypePagination.getData());
        return gtm;
    }
}
