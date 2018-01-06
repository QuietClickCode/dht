package com.retailers.hnc.web.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.ClientManage;
import com.retailers.hnc.common.entity.EmRelationship;
import com.retailers.hnc.common.entity.Opening;
import com.retailers.hnc.common.service.ClientManageService;
import com.retailers.hnc.common.service.EmRelationshipService;
import com.retailers.hnc.common.service.OpeningService;
import com.retailers.hnc.common.vo.ClientManageVo;
import com.retailers.hnc.common.vo.EmRelationshipVo;
import com.retailers.hnc.web.annotation.CheckOpenId;
import com.retailers.hnc.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/12/28.
 */
@Controller
@RequestMapping("employeeRelationship")
public class EmployeeRelationshipController extends BaseController{
    @Autowired
    EmRelationshipService emRelationshipService;
    @Autowired
    ClientManageService clientManageService;
    @Autowired
    OpeningService openingService;
    @RequestMapping("/queryAllClient")
    @ResponseBody
    public Map<String,Object> queryAllClient(){
        List<EmRelationshipVo> relationshipVos = emRelationshipService.queryEmRelationshipVoList();
        for(EmRelationshipVo relationshipVo:relationshipVos){
            System.out.println(relationshipVo.getTid()+" "+relationshipVo.getParentId());
        }
        List<EmRelationshipVo> rows = emRelationshipService.queryEmployeeTree(relationshipVos);
        HashMap<String,Object> map=new HashMap<String, Object>();
        map.put("total",1000);
        map.put("rows",rows);
        return map;
    }


    @RequestMapping("/queryAllEmployee")
    @ResponseBody
    public Map<String,Object> queryAllEmployee(){
        HashMap<String,Object> map=new HashMap<String, Object>();
        Opening opening = openingService.queryEarlyOpening();
        if (ObjectUtils.isNotEmpty(opening)){
            Long pid = opening.getOid();
            List<EmRelationshipVo> relationshipVos = emRelationshipService.queryOpeningEmployees(pid);
            List<EmRelationshipVo> rows = emRelationshipService.queryEmployeeTree(relationshipVos);
            List<ClientManageVo> clientManageVos = clientManageService.queryClientManagerCount();
            for(EmRelationshipVo emRelationshipVo:rows){
                for(ClientManageVo clientManageVo:clientManageVos){
                    Long eid1 = emRelationshipVo.getEmId();
                    Long eid2 = clientManageVo.getTmEmployee();
                    if(eid1==eid2){
                        emRelationshipVo.setCount(clientManageVo.getCount());
                        break;
                    }
                }
            }

            map.put("total",1000);
            map.put("rows",rows);
        }

        return map;
    }


    @RequestMapping("/addEmRelationship")
    @ResponseBody
    public BaseResp addEmRelationship(EmRelationship emRelationship){
        emRelationship.setIsDelete(0l);
        boolean flag = emRelationshipService.saveEmRelationship(emRelationship);
        if(flag)
            return success("添加预约关系成功");
        else
            return success("添加预约关系失败");
    }

    @RequestMapping("/updateEmRelationship")
    @ResponseBody
    public BaseResp updateEmRelationship(EmRelationship emRelationship){
        emRelationship.setIsDelete(0l);
        boolean flag = emRelationshipService.updateEmRelationship(emRelationship);
        if(flag)
            return success("修改预约关系成功");
        else
            return errorForSystem("修改预约关系失败");
    }


    @RequestMapping("/queryOpeningStatus")
    @ResponseBody
    public HashMap<String,Integer> queryOpeningStatus(Long pId){
        Integer flag = emRelationshipService.queryOpeningStatus(pId);
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        map.put("flag",flag);
        return map;
    }
}
