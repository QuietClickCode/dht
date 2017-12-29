package com.retailers.hnc.manage.controller;

import com.retailers.hnc.common.entity.ClientIntention;
import com.retailers.hnc.common.service.ClientIntentionService;
import com.retailers.hnc.common.vo.ClientIntentionVo;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
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
public class ClientIntentionController {
    @Autowired
    ClientIntentionService clientIntentionService;

    @RequestMapping("/queryClientIntentionList")
    @ResponseBody
    public Map<String,Object> queryTeamList(Long cmId){
        Map<String,Object> map = new HashMap<String,Object>();
        List<ClientIntentionVo> list = clientIntentionService.queryClientIntentionVoListByCmId(cmId);
        if(ObjectUtils.isNotEmpty(list)){
            map.put("rows",list);
        }
        return map;
    }


}
