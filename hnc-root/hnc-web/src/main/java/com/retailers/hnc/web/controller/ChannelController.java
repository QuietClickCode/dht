package com.retailers.hnc.web.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.Channel;
import com.retailers.hnc.common.service.ChannelService;
import com.retailers.hnc.web.base.BaseController;
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
 * Created by niconiconi on 2017/12/26.
 */
@Controller
@RequestMapping("channel")
public class ChannelController extends BaseController {
    @Autowired
    ChannelService channelService;

    @RequestMapping("/queryChannelList")
    @ResponseBody
    public Map<String,Object> queryChannelList(PageUtils pageForm,String channelVal){
        System.out.println("--------------------------------"+channelVal);
//        Map<String,Object> gtm = new HashMap<String,Object>();
//        gtm.put("channelVal",channelVal);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        Pagination<Channel> teamPagination = channelService.queryChannelList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",teamPagination.getTotalCount());
        gtm.put("rows",teamPagination.getData());
        return gtm;
    }

}
