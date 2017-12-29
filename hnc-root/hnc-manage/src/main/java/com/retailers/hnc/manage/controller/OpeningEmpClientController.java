package com.retailers.hnc.manage.controller;

import com.retailers.hnc.common.service.OpeningEmpClientService;
import com.retailers.hnc.common.vo.ClientIntentionVo;
import com.retailers.hnc.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by niconiconi on 2017/12/27.
 */
@Controller
@RequestMapping("OpeningEmpClient")
public class OpeningEmpClientController extends BaseController{
    @Autowired
    OpeningEmpClientService openingEmpClientService;

    @RequestMapping("queryNotGivenList")
    @ResponseBody
    public Map<String,Object> queryOpening(Long oid,String cmName, HttpServletRequest request, int pageNo, int pageSize){
        Long eid = getCurLoginUserId(request);
        Map map = new HashMap();
        if(ObjectUtils.isNotEmpty(eid)){
            Map params = new HashMap();
            params.put("oid",oid);
            params.put("cmName",cmName);
            params.put("eid",eid);
            Pagination<ClientIntentionVo> pagination = openingEmpClientService.queryNotGivenList(params,pageNo,pageSize);

            return queryPages(pagination);
        }
        return map;
    }

    @RequestMapping("queryCheckingandpassandnotpassList")
    @ResponseBody
    public Map<String,Object> queryCheckingList(Long oid,Long status, HttpServletRequest request, int pageNo, int pageSize){
        Long eid = getCurLoginUserId(request);
        Map map = new HashMap();
        if(ObjectUtils.isNotEmpty(eid)){
            Map params = new HashMap();
            params.put("oid",oid);
            params.put("eid",eid);
            params.put("status",status);
            Pagination<ClientIntentionVo> pagination = openingEmpClientService.queryCheckingandpassandnotpassList(params,pageNo,pageSize);
            return queryPages(pagination);
        }
        return map;
    }

    @RequestMapping("addCheckClient")
    @ResponseBody
    public BaseResp addCheckClient(Long oid,String cmIds, HttpServletRequest request){
        Long eid = getCurLoginUserId(request);
        boolean flag = openingEmpClientService.addCheckClient(oid,eid,cmIds);
        return success(flag);
    }

    @RequestMapping("changeClientStatus")
    @ResponseBody
    public BaseResp changeClientStatus(Long oid, Long status,String cmIds, HttpServletRequest request){
        Long eid = getCurLoginUserId(request);
        boolean flag = openingEmpClientService.changeClientStatus(oid,eid,cmIds,status);
        return success(flag);
    }
}
