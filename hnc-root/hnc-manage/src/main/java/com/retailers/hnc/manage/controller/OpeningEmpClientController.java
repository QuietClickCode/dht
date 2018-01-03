package com.retailers.hnc.manage.controller;

import com.retailers.hnc.common.entity.EmRelationship;
import com.retailers.hnc.common.entity.OpeningEmpClient;
import com.retailers.hnc.common.service.EmRelationshipService;
import com.retailers.hnc.common.service.OpeningEmpClientService;
import com.retailers.hnc.common.vo.ClientIntentionVo;
import com.retailers.hnc.common.vo.ClientManageVo;
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
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/12/27.
 */
@Controller
@RequestMapping("OpeningEmpClient")
public class OpeningEmpClientController extends BaseController{
    @Autowired
    OpeningEmpClientService openingEmpClientService;
    @Autowired
    EmRelationshipService emRelationshipService;

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
            Pagination<ClientManageVo> pagination = openingEmpClientService.queryNotGivenList(params,pageNo,pageSize);

            return queryPages(pagination);
        }
        return map;
    }

    @RequestMapping("queryCheckingandpassandnotpassList")
    @ResponseBody
    public Map<String,Object> queryCheckingList(String isManage,Long oid,Long status,String cmName, HttpServletRequest request, int pageNo, int pageSize){
        Long eid = getCurLoginUserId(request);
        Map map = new HashMap();
        if(ObjectUtils.isNotEmpty(eid)){
            Map params = new HashMap();
            params.put("oid",oid);
            params.put("status",status);
            params.put("cmName",cmName);
            if(ObjectUtils.isEmpty(isManage)){
                params.put("eid",eid);
            }
            Pagination<ClientManageVo> pagination = openingEmpClientService.queryCheckingandpassandnotpassList(params,pageNo,pageSize);
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

    @RequestMapping("updateOpeningEmpClient")
    @ResponseBody
    public BaseResp updateOpeningEmpClient(String oecIds,Long status,String msg){
        boolean flag = openingEmpClientService.updateOpeningEmpClientByOecIds(oecIds,status,msg);
        return success(flag);
    }

    @RequestMapping("loadThisOpeningPersonNum")
    @ResponseBody
    public Map<String,Object> loadThisOpeningPersonNum(Long oid,HttpServletRequest request){
        Long eid = getCurLoginUserId(request);
        Map params = new HashMap();
        params.put("isDelete",0L);
        params.put("pid",oid);
        params.put("emId",eid);
        List<EmRelationship> list = emRelationshipService.queryEmRelationshipList(params,1,1).getData();

        Map params1 = new HashMap();
        params1.put("isDelete",0L);
        params1.put("oid",oid);
        params1.put("eid",eid);
        List<OpeningEmpClient> openingEmpClients = openingEmpClientService.queryOpeningEmpClientList(params1,1,9999).getData();
        int index = 0;
        for(OpeningEmpClient openingEmpClient:openingEmpClients){
            if(openingEmpClient.getOecStatus()!=3){
                index ++;
            }
        }
        Map returnMap = new HashMap();
        if(ObjectUtils.isNotEmpty(list)){
            returnMap.put("personNum",list.get(0).getEmReservation());
        }else{
            returnMap.put("personNum",0);
        }
        returnMap.put("usePersonNum",index);
        return returnMap;
    }
}
