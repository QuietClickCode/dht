package com.retailers.dht.web.controller;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.Famer;
import com.retailers.dht.common.entity.FamerUser;
import com.retailers.dht.common.service.FamerService;
import com.retailers.dht.common.service.FamerUserService;
import com.retailers.dht.common.vo.FamerVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.utils.ObjectUtils;
import com.sun.tools.internal.xjc.reader.gbind.ElementSets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("famer")
public class FamerController extends BaseController {

    @Autowired
    FamerService famerService;

    @Autowired
    FamerUserService famerUserService;

    @RequestMapping("gotoFamerDetail")
    public String queryIsysjq(Long fid,HttpServletRequest request){
        request.setAttribute("fid",fid);
        return  redirectUrl(request,"ysjq/famerDetail");
    }

    @RequestMapping("gotoFamerList")
    public String gotoFamerList(HttpServletRequest request){
        return  redirectUrl(request,"ysjq/famerList");
    }

    @RequestMapping("gotoFarmerPage")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,redirectUrl = "/loginPage",msg = SystemConstant.USER_UN_LOGIN_ALERT_MSG,isOpenPage =true)
    public String gotoFamerPage(HttpServletRequest request,HttpServletResponse response){
        return  redirectUrl(request,"ysjq/farmerPage");
    }

    @RequestMapping("queryFamerList")
    @ResponseBody
    public Map queryFamerList(Long fid,String fcensus,int pageNo,int pageSize){
        Map map = new HashMap();
        Map params = new HashMap();
        params.put("isDelete",0L);
        params.put("fcensus",fcensus);
        params.put("fid",fid);
        List<FamerVo> famerVoList = famerService.queryFamerList(params,pageNo,pageSize).getData();
        if(ObjectUtils.isNotEmpty(famerVoList)){
            map.put("rows",famerVoList);
        }
        return  map;
    }

    @RequestMapping("queryUserList")
    @ResponseBody
    public Map queryUserList(HttpServletRequest request){
        Map map = new HashMap();
        Map params = new HashMap();
        params.put("isDelete",0L);
        params.put("uid",getCurLoginUserId(request));
        List<FamerUser> famerUsers = famerUserService.queryFamerUserList(params,1,900).getData();
        List<Long> fids = new ArrayList<Long>();
        for(FamerUser famerUser:famerUsers){
            fids.add(famerUser.getFid());
        }
        if(ObjectUtils.isEmpty(fids)){
            fids.add(-1L);
        }
        params.put("fids",fids);
        System.out.println(JSON.toJSONString(fids));
        List<FamerVo> famerVos = famerService.queryFamerList(params,1,900).getData();
        map.put("rows",famerVos);
        return  map;
    }
}
