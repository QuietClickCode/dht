package com.retailers.sbj.web.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.sbj.common.entity.EmRelationship;
import com.retailers.sbj.common.service.EmRelationshipService;
import com.retailers.sbj.common.vo.EmRelationshipVo;
import com.retailers.sbj.common.vo.EmployeeRelationshipVo;
import com.retailers.sbj.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
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
public class EmployeeRelationshipController extends BaseController{
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
    @Function(label = "查询所有分配了预约客户的团队和置业顾问",description = "查询所有分配了预约客户的团队和置业顾问",resourse = "employeeRelationship.queryAllEmployee",sort = 3,parentRes = "employeeRelationship.EmployeeRelationshipMapping")
    @ResponseBody
    public Map<String,Object> queryAllEmployee(Long pId){
        List<EmRelationshipVo> relationshipVos = emRelationshipService.queryOpeningEmployees(pId);
        for(EmRelationshipVo relationshipVo:relationshipVos){
            System.out.println(relationshipVo.getTid()+" "+relationshipVo.getParentId());
        }
        List<EmRelationshipVo> rows = emRelationshipService.queryEmployeeTree(relationshipVos);
        HashMap<String,Object> map=new HashMap<String, Object>();
        map.put("total",1000);
        map.put("rows",rows);
        return map;
    }


    @RequestMapping("/addEmRelationship")
    @Function(label = "添加预约关系",description = "添加预约关系",resourse = "employeeRelationship.addEmRelationship",sort = 3,parentRes = "employeeRelationship.EmployeeRelationshipMapping")
    @ResponseBody
    public BaseResp addEmRelationship(EmRelationship emRelationship){
        emRelationship.setIsDelete(0l);
        boolean flag = emRelationshipService.saveEmRelationship(emRelationship);
        if(flag)
            return success("添加预约关系成功");
        else
            return success("添加预约关系失败");
    }


    @RequestMapping("/queryEmRelationshipVoTreeList")
    @Function(label = "查询所有团队和置业顾问用于前端树型结构展示",description = "查询所有团队和置业顾问用于前端树型结构展示",resourse = "employeeRelationship.queryEmRelationshipVoTreeList",sort = 3,parentRes = "employeeRelationship.EmployeeRelationshipMapping")
    @ResponseBody
    public Map<String,Object> queryEmRelationshipVoTreeList(EmRelationship relationship){
        List<EmployeeRelationshipVo> rows = emRelationshipService.queryReservationInfo(relationship);
        HashMap<String,Object> map=new HashMap<String, Object>();
        map.put("total",1000);
        map.put("rows",rows);
        return map;
    }

    @RequestMapping("/updateEmRelationship")
    @Function(label = "修改预约关系",description = "修改预约关系",resourse = "employeeRelationship.updateEmRelationship",sort = 3,parentRes = "employeeRelationship.EmployeeRelationshipMapping")
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
    @Function(label = "查询是否绑定预约关系",description = "查询是否绑定预约关系",resourse = "employeeRelationship.queryOpeningStatus",sort = 3,parentRes = "employeeRelationship.EmployeeRelationshipMapping")
    @ResponseBody
    public HashMap<String,Integer> queryOpeningStatus(Long pId){
        Integer flag = emRelationshipService.queryOpeningStatus(pId);
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        map.put("flag",flag);
        return map;
    }
}
