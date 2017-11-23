package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.SecspepAdvertising;
import com.retailers.dht.common.service.SecspepAdvertisingService;
import com.retailers.dht.common.vo.SecspepAdvertisingVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/10/21.
 */
@Controller
@RequestMapping("secspepAdvertising")
public class SecsepAdvertisingController extends BaseController {
    @Autowired
    SecspepAdvertisingService secspepAdvertisingService;

    @RequestMapping("/querysecspepAdvertisingList")
    @Function(label = "查询所有特价/秒杀广告",parentRes = "secspepAdvertising.secspepAdvertisingMapping",resourse = "secspepAdvertising.querysecspepAdvertisingList",description = "查询所有特价/秒杀广告",sort = 2)
    @ResponseBody
    public Map<String,Object> querysecspepAdvertisingList(){
        Map params = new HashMap();
        params.put("isDelete",0L);
        params.put("isShow",1L);
        Pagination<SecspepAdvertisingVo> pagination = secspepAdvertisingService.querySecspepAdvertisingList(params,1,100);
        Map<String,Object> rtn=new HashMap<String, Object>();
        rtn.put("total",1000);
        rtn.put("rows",pagination.getData());
        return rtn;
    }


}
