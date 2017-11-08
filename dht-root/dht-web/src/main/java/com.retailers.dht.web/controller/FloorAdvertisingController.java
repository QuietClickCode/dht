package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.FloorAdvertising;
import com.retailers.dht.common.service.FloorAdvertisingService;
import com.retailers.dht.common.vo.FloorAdvertisingVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/10/21.
 */
@Controller
@RequestMapping("floorAdvertising")
public class FloorAdvertisingController extends BaseController {
    @Autowired
    FloorAdvertisingService advertisingService;

    @RequestMapping("/queryFloorAdvertisingListByGclassId")
    @ResponseBody
    public Map<String,Object> queryFloorAdvertisingListByGclassId(Long ggId,Long isShow,Long faClient){
        Map params = new HashMap();
        params.put("parentId",ggId);
        params.put("isShow",isShow);
        params.put("faClient",faClient);
        params.put("isDelete",0L);
        List<FloorAdvertisingVo> list = advertisingService.queryFloorAdvertisingListByGclassId(params);
        Map map = new HashMap();
        map.put("rows",list);
        return  map;
    }
}
