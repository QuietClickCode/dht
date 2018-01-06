package com.retailers.hnc.web.controller;

import com.retailers.hnc.common.entity.EmRelationship;
import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.entity.Opening;
import com.retailers.hnc.common.entity.OpeningEmpClient;
import com.retailers.hnc.common.service.*;
import com.retailers.hnc.common.vo.ClientManageVo;
import com.retailers.hnc.web.annotation.CheckOpenId;
import com.retailers.hnc.web.base.BaseController;
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
    @Autowired
    ClientManageService clientManageService;
    @Autowired
    EmployeeManageService employeeManageService;
    @Autowired
    OpeningService openingService;

    @RequestMapping("queryNotGivenListWeb")
    @ResponseBody
    public Map<String,Object> queryNotGivenListWeb(String phone, int pageNo, int pageSize){
        Map map = new HashMap();
        Long eid = getEmpIdByWxPhone(phone);
        Map params1 = new HashMap();
        params1.put("isDelete",0L);
        params1.put("eid",eid);
        Pagination<ClientManageVo> pagination = openingEmpClientService.queryNotGivenListWeb(params1,pageNo,pageSize);
        map.put("rows",pagination.getData());
        map.put("notgivenNum",pagination.getTotalCount());
        return map;
    }

    @RequestMapping("queryCheckingandpassandnotpassListWeb")
    @ResponseBody
    public Map<String,Object> queryCheckingandpassandnotpassListWeb(String isManage,Long status,String phone, int pageNo, int pageSize){
        Map map = new HashMap();

        if(ObjectUtils.isNotEmpty(phone)){
            Map params1 = new HashMap();
            params1.put("status",status);
            if(ObjectUtils.isEmpty(isManage)){
                Long eid = getEmpIdByWxPhone(phone);
                params1.put("eid",eid);
            }
            Pagination<ClientManageVo> pagination = openingEmpClientService.queryCheckingandpassandnotpassListWeb(params1,pageNo,pageSize);
            List<ClientManageVo> list  = pagination.getData();
            map.put("total",pagination.getTotalCount());
            map.put("rows",list);
        }
        return map;
    }

    @RequestMapping("queryCheckingandpassandnotpassNumWeb")
    @ResponseBody
    public Map<String,Object> queryCheckingandpassandnotpassNumWeb(String isManage,String phone){
        Map map = new HashMap();
        Opening opening = openingService.queryEarlyOpening();
        Long oid = opening.getOid();
        if(ObjectUtils.isNotEmpty(phone)){
            Map params = new HashMap();
            params.put("oid",oid);
            if(ObjectUtils.isNotEmpty(phone)){
                Long eid = getEmpIdByWxPhone(phone);
                params.put("eid",eid);
                //通过oid eid查询分配名额
                Map paramstotalNumberParams = new HashMap();
                paramstotalNumberParams.put("pid",oid);
                paramstotalNumberParams.put("emId",eid);
                paramstotalNumberParams.put("isDelete",0L);
                List<EmRelationship> list = emRelationshipService.queryEmRelationshipList(paramstotalNumberParams,1,1).getData();
                if(ObjectUtils.isNotEmpty(list)){
                    EmRelationship emRelationship = list.get(0);
                    Long total = emRelationship.getEmReservation();
                    if(ObjectUtils.isNotEmpty(total)){
                        map.put("total",total);
                    }else{
                        map.put("total",0);
                    }
                }
            }else{
                map.put("total",opening.getOmenberNum());
            }
            map.putAll(openingEmpClientService.queryCheckingandpassandnotpassNumWeb(params));
        }

        return map;
    }

    @RequestMapping("addCheckClient")
    @ResponseBody
    public BaseResp addCheckClient(String cmIds,String phone){
        Opening opening = openingService.queryEarlyOpening();
        Long oid = opening.getOid();
        Long eid = getEmpIdByWxPhone(phone);
        boolean flag = openingEmpClientService.addCheckClient(oid,eid,cmIds);
        return success(flag);
    }

    @RequestMapping("updateOpeningEmpClient")
    @ResponseBody
    public BaseResp updateOpeningEmpClient( String oecIds,Long status,String msg){
        boolean flag = openingEmpClientService.updateOpeningEmpClientByOecIds(oecIds,status,msg);
        return success(flag);
    }


//
//    @RequestMapping("loadThisOpeningPersonNum")
//    @ResponseBody
//    public Map<String,Object> loadThisOpeningPersonNum(Long oid,HttpServletRequest request){
//        Long eid = getCurLoginUserId(request);
//        Map params = new HashMap();
//        params.put("isDelete",0L);
//        params.put("pid",oid);
//        params.put("emId",eid);
//        List<EmRelationship> list = emRelationshipService.queryEmRelationshipList(params,1,1).getData();
//
//        Map params1 = new HashMap();
//        params1.put("isDelete",0L);
//        params1.put("oid",oid);
//        params1.put("eid",eid);
//        List<OpeningEmpClient> openingEmpClients = openingEmpClientService.queryOpeningEmpClientList(params1,1,9999).getData();
//        int index = 0;
//        for(OpeningEmpClient openingEmpClient:openingEmpClients){
//            if(openingEmpClient.getOecStatus()!=3){
//                index ++;
//            }
//        }
//        Map returnMap = new HashMap();
//        if(ObjectUtils.isNotEmpty(list)){
//            returnMap.put("personNum",list.get(0).getEmReservation());
//        }else{
//            returnMap.put("personNum",0);
//        }
//        returnMap.put("usePersonNum",index);
//        return returnMap;
//    }


}
