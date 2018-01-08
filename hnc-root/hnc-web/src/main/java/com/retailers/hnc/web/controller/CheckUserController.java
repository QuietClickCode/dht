package com.retailers.hnc.web.controller;

import com.retailers.hnc.common.entity.ClientManage;
import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.entity.Opening;
import com.retailers.hnc.common.service.CheckUserService;
import com.retailers.hnc.common.service.ClientManageService;
import com.retailers.hnc.common.service.EmployeeManageService;
import com.retailers.hnc.common.service.OpeningService;
import com.retailers.hnc.common.vo.CheckUserVo;
import com.retailers.hnc.common.vo.EmpDataVo;
import com.retailers.hnc.common.vo.GroupDataVo;
import com.retailers.hnc.common.vo.GroupItemVo;
import com.retailers.hnc.web.annotation.CheckOpenId;
import com.retailers.hnc.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
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
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("checkUser")
public class CheckUserController extends BaseController {

    @Autowired
    CheckUserService checkUserService;
    @Autowired
    OpeningService openingService;

    @RequestMapping("updateCheckUser")
    @ResponseBody
    public Map updateCheckUser(String validateCode,String phone){
        Long eid = getEmpIdByWxPhone(phone);
        Map map = checkUserService.checkUser(validateCode,eid);
        return map;
    }

    @RequestMapping("queryUsedOrNotUse")
    @ResponseBody
    public Map queryUsedOrNotUse(String randStr,Long isUse){
        Long cid = getClientIdByOpenId(randStr);
        Map params = new HashMap();
        params.put("isDelete",0L);
        params.put("cid",cid);
        params.put("isUse",isUse);
        List list = checkUserService.queryUsedOrNotUse(params,1,999);
        Map map = new HashMap();
        map.put("rows",list);
        return map;
    }

    @RequestMapping("queryCheckUserValidateCode")
    @ResponseBody
    public Map queryCheckUserValidateCode(String randStr){
        Long cid = getClientIdByOpenId(randStr);
        System.out.println(cid);
        CheckUserVo checkUserVo = checkUserService.queryCheckUserValidateCode(cid);
        Map map = new HashMap();
        if(ObjectUtils.isNotEmpty(checkUserVo)){
            map.put("row",checkUserVo);
        }
        return map;
    }

    @RequestMapping("queryCheckUserVoList")
    @ResponseBody
    public Map queryCheckUserVoList(String phone,Long isUse,String isManage,Long oid,int pageNo,int pageSize){
        Map map = new HashMap();
        if(ObjectUtils.isEmpty(oid)){
            Opening opening = openingService.queryLastOpening();
            if(ObjectUtils.isEmpty(opening)){
                return map;
            }else{
                oid = opening.getOid();
            }
        }
        Map params = new HashMap();
        params.put("isDelete",0L);
        params.put("isUse",isUse);
        params.put("oid",oid);
        if(ObjectUtils.isNotEmpty(phone)){
            Long eid = getEmpIdByWxPhone(phone);
            params.put("eid",eid);
        }
        Pagination<CheckUserVo> checkUserVos = checkUserService.queryCheckUserVoList(params,pageNo,pageSize);

        map.put("rows",checkUserVos.getData());
        map.put("total",checkUserVos.getTotalCount());
        return map;
    }

    @RequestMapping("queryCheckUserNum")
    @ResponseBody
    public Map queryCheckUserNum(Long oid){
        return checkUserService.queryCheckUserNum(oid);
    }

    @RequestMapping("queryAchievement")
    @ResponseBody
    public Map queryAchievement(Long oid,String empIds,String tids){
        List<Long> emIdList = StringToList(empIds);
        List<Long> tidsList = StringToList(tids);
        Map params = new HashMap();
        if(ObjectUtils.isNotEmpty(tids)){
            params.put("tidList",tidsList);
        }else{
            params.put("emIdList",emIdList);
        }
        params.put("oid",oid);
        Map map = new HashMap();
        List<CheckUserVo> list = checkUserService.queryAchievement(params);

        //封装成前端所需要的对象
        GroupDataVo groupDataVo = new GroupDataVo();
        List<GroupItemVo> groupItemVoList = new ArrayList<GroupItemVo>();

        for(CheckUserVo checkUserVo:list){
            Long tid = checkUserVo.getTid();
            String tname = checkUserVo.getTname();
            GroupItemVo groupItemVo = new GroupItemVo();
            groupItemVo.setTid(tid);
            groupItemVo.setTname(tname);
            if(ObjectUtils.isNotEmpty(groupItemVoList)){
                boolean flag = true;
                for(GroupItemVo groupItemVo1:groupItemVoList){
                    if(groupItemVo1.getTid()==tid){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    groupItemVoList.add(groupItemVo);
                }
            }else{
                groupItemVoList.add(groupItemVo);
            }
        }

        Long todayTotal = 0L;
        Long noPresent = 0L;
        Long regisTotal = 0L;

        if(ObjectUtils.isNotEmpty(groupItemVoList)){
            for(GroupItemVo groupItemVo:groupItemVoList){
                Long groupTodayTotal = 0L;
                Long groupNoPresent = 0L;
                Long groupRegisTotal = 0L;
                List<EmpDataVo> empDataVos = new ArrayList<EmpDataVo>();
                for(CheckUserVo checkUserVo:list){
                    Long tid1 = checkUserVo.getTid();
                    Long tid2 = groupItemVo.getTid();
                    if(tid1==tid2){
                        EmpDataVo empDataVo = new EmpDataVo();
                        Long useNum = checkUserVo.getUseNum();
                        Long notuseNum = checkUserVo.getNotuseNum();
                        Long total = useNum + notuseNum;
                        String empName = checkUserVo.getEmpName();
                        empDataVo.setAgentTodayPresent(checkUserVo.getUseNum());
                        empDataVo.setAgentNoPresent(notuseNum);
                        empDataVo.setAgentRegisTotal(total);
                        empDataVo.setEmpName(empName);
                        empDataVo.setTid(tid1);
                        empDataVos.add(empDataVo);
                        groupTodayTotal += useNum;
                        groupNoPresent += notuseNum;
                        groupRegisTotal += total;
                    }
                }
                groupItemVo.setGroupItem(empDataVos);
                groupItemVo.setGroupNoPresent(groupNoPresent);
                groupItemVo.setGroupTodayTotal(groupTodayTotal);
                groupItemVo.setGroupRegisTotal(groupRegisTotal);
                todayTotal += groupTodayTotal;
                noPresent += groupNoPresent;
                regisTotal += groupRegisTotal;
            }
        }
        groupDataVo.setGroup(groupItemVoList);
        groupDataVo.setNoPresent(noPresent);
        groupDataVo.setTodayTotal(todayTotal);
        groupDataVo.setRegisTotal(regisTotal);

        map.put("rows",groupDataVo);
        return map;
    }

    public List<Long> StringToList(String str){
        String[] strArr = str.split(",");
        if(ObjectUtils.isNotEmpty(strArr)){
            List<Long> list = new ArrayList<Long>();
            for(String strStr:strArr){
                Long l = Long.parseLong(strStr);
                list.add(l);
            }
            return list;
        }
        return null;
    }
}
