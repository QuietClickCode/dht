package com.retailers.hnc.web.controller;

import com.retailers.hnc.common.entity.ClientIntention;
import com.retailers.hnc.common.service.ClientIntentionService;
import com.retailers.hnc.common.vo.ClientIntentionVo;
import com.retailers.hnc.web.annotation.CheckOpenId;
import com.retailers.hnc.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/12/27.
 */
@Controller
@RequestMapping("clientIntention")
public class ClientIntentionController extends BaseController{
    @Autowired
    ClientIntentionService clientIntentionService;

    @RequestMapping("/queryClientIntentionList")
    @CheckOpenId
    @ResponseBody
    public Map<String,Object> queryClientIntentionList(String randStr){
        Long cid = getClientIdByOpenId(randStr);
        Map<String,Object> map = new HashMap<String,Object>();
        List<ClientIntentionVo> list = clientIntentionService.queryClientIntentionVoListByCmId(cid);
        if(ObjectUtils.isNotEmpty(list)){
            map.put("rows",list);
        }
        return map;
    }

    @RequestMapping("/deleteClientIntention")
    @CheckOpenId
    @ResponseBody
    public BaseResp deleteClientIntention(Long iid){
        boolean flag = clientIntentionService.deleteClientIntentionByIid(iid);
        return success(flag);
    }

    @RequestMapping("/saveClientIntention")
    @CheckOpenId
    @ResponseBody
    public BaseResp saveClientIntention(ClientIntention clientIntention,String randStr){
        Long cmId = getClientIdByOpenId(randStr);
        clientIntention.setCmId(cmId);
        clientIntention.setIsDelete(0L);
        boolean flag = clientIntentionService.saveClientIntention(clientIntention);
        return success(flag);
    }

    @RequestMapping("/updateClientIntention")
    @CheckOpenId
    @ResponseBody
    public BaseResp updateClientIntention(ClientIntention clientIntention,String randStr){
        Long cmId = getClientIdByOpenId(randStr);
        clientIntention.setCmId(cmId);
        clientIntention.setIsDelete(0L);
        boolean flag = clientIntentionService.updateClientIntention(clientIntention);
        return success(flag);
    }
}
