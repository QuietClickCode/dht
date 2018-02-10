package com.retailers.dht.web.controller;

import com.retailers.dht.common.service.FamerService;
import com.retailers.dht.common.vo.FamerVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("gotoFamerDetail")
    public String queryIsysjq(Long fid,HttpServletRequest request){
        request.setAttribute("fid",fid);
        return  redirectUrl(request,"ysjq/famerDetail");
    }

    @RequestMapping("gotoFamerList")
    public String gotoFamerList(HttpServletRequest request){
        return  redirectUrl(request,"ysjq/famerList");
    }

    @RequestMapping("queryFamerList")
    @ResponseBody
    public Map queryFamerList(Long fid,int pageNo,int pageSize){
        Map map = new HashMap();
        Map params = new HashMap();
        params.put("isDelete",0L);
        params.put("fid",fid);
        List<FamerVo> famerVoList = famerService.queryFamerList(params,pageNo,pageSize).getData();
        if(ObjectUtils.isNotEmpty(famerVoList)){
            map.put("rows",famerVoList);
        }
        return  map;
    }
}
