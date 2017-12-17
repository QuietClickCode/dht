package com.retailers.dht.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.UserFootprint;
import com.retailers.dht.common.service.UserFootprintService;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.encrypt.Sha1DESUtils;
import com.retailers.tools.utils.HttpClientUtil;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.wx.common.utils.wx.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("userFootprint")
public class UserFootprintController extends BaseController{

    @Autowired
    UserFootprintService userFootprintService;

    @RequestMapping("/toUserFootprintpage")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY)
    public String toUserFootprintpage(HttpServletRequest request, HttpServletResponse response) {
        return redirectUrl(request,"user/footprint");
    }

    @RequestMapping("/saveUserFootprint")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp saveUserFootprint(HttpServletRequest request, UserFootprint userFootprint) {
        Long uid = getCurLoginUserId(request);
        userFootprint.setIsDelete(0L);
        userFootprint.setUid(uid);
        userFootprint.setCreatTime(new Date());
        boolean flag = userFootprintService.saveUserFootprint(userFootprint);
        return success(flag);
    }

    @RequestMapping("/queryUserFootprint")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public Map<String,Object> queryUserFootprint(HttpServletRequest request,int pageNo,int pageSize,Long isDelete) {
        Long uid = getCurLoginUserId(request);
        Map params = new HashMap();
        params.put("isDelete",isDelete);
        params.put("uid",uid);
        List<GoodsVo> list = userFootprintService.queryUserFootprintVoList(params,pageNo,pageSize);
        Map map = new HashMap();
        map.put("rows",list);
        return map;
    }

    @RequestMapping("/delonefootprint")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp delonefootprint(Long ufId) {
        boolean flag = userFootprintService.deleteUserFootprintByUfId(ufId);
        return success(flag);
    }

    @RequestMapping("/delallfootprint")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp delallfootprint(HttpServletRequest request) {
        Long uid = getCurLoginUserId(request);
        boolean flag = userFootprintService.delallfootprint(uid);
        return success(flag);
    }
}
