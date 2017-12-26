package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.Channel;
import com.retailers.hnc.common.service.ChannelService;
import com.retailers.hnc.manage.base.BaseController;
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

    @RequestMapping("/channelMapping")
    @Menu(parentRes = "sys.manager.client",resourse = "channel.channelMapping",description = "来访渠道管理",label = "来访渠道管理")
    public String channelMapping(){
        return "clientManage/channel";
    }

    @RequestMapping("/queryChannelList")
    @Function(label="来访渠道集合", description = "来访渠道集合", resourse = "channel.queryChannelList",sort=1,parentRes="channel.channelMapping")
    @ResponseBody
    public Map<String,Object> queryChannelList(PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        Pagination<Channel> teamPagination = channelService.queryChannelList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",teamPagination.getTotalCount());
        gtm.put("rows",teamPagination.getData());
        return gtm;
    }

    @RequestMapping("/addChannel")
    @Function(label = "添加来访渠道",description = "添加来访渠道",resourse = "channel.addChannel",sort = 3,parentRes = "channel.channelMapping")
    @ResponseBody
    public BaseResp addChannel(Channel channel){
        boolean flag = channelService.saveChannel(channel);
        if(flag)
            return success("添加来访渠道成功");
        else
            return success("添加来访渠道失败");
    }

    @RequestMapping("/updateChannel")
    @Function(label = "修改来访渠道",description = "修改来访渠道",resourse = "channel.updateChannel",sort = 3,parentRes = "channel.channelMapping")
    @ResponseBody
    public BaseResp updateChannel(Channel channel){
        boolean flag = channelService.updateChannel(channel);
        if(flag)
            return success("添加渠道[" + channel.getCchannel() + "]成功");
        else
            return errorForSystem("修改渠道[" + channel.getCchannel() + "]失败");
    }

    @RequestMapping("/removeChannel")
    @Function(label = "删除该渠道",description = "删除该渠道",resourse = "channel.removeChannel",sort = 3,parentRes = "channel.channelMapping")
    @ResponseBody
    public BaseResp removeChannel(Long cid){
        boolean flag = channelService.deleteChannelByCid(cid);
        return  success(flag);
    }
}
