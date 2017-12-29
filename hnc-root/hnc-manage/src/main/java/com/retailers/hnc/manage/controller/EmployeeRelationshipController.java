package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.service.EmRelationshipService;
import com.retailers.hnc.common.vo.EmployeeAndTeamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/12/28.
 */
@Controller
@RequestMapping("employeeRelationship")
public class EmployeeRelationshipController {
    @Autowired
    EmRelationshipService emRelationshipService;

    @RequestMapping("/EmployeeRelationshipMapping")
    @Menu(parentRes = "sys.manager.appointment",resourse = "employeeRelationship.EmployeeRelationshipMapping",description = "分配预约名额",label = "分配预约名额")
    public String employeeRelationshipMapping(){
        return "reservation/allocationResevation";
    }

    @RequestMapping("/queryAllClient")
    @Function(label = "查询所有团队和置业顾问",description = "查询所有团队和置业顾问",resourse = "employeeRelationship.queryAllClient",sort = 3,parentRes = "employeeRelationship.EmployeeRelationshipMapping")
    @ResponseBody
    public Map<String,Object> queryAllClient(){
        List<EmployeeAndTeamVo> relationshipVos = emRelationshipService.queryEmRelationshipVoList();
        for(EmployeeAndTeamVo relationshipVo:relationshipVos){
            System.out.println(relationshipVo.gettId()+" "+relationshipVo.getParentId());
        }
        List<EmployeeAndTeamVo> rows = emRelationshipService.queryEmployeeTree(relationshipVos);
        HashMap<String,Object> map=new HashMap<String, Object>();
        map.put("total",1000);
        map.put("rows",rows);
        return map;
    }
}
