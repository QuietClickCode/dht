package com.retailers.hnc.web.controller;

import com.retailers.hnc.common.entity.ClientManage;
import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.service.CheckUserService;
import com.retailers.hnc.common.service.ClientManageService;
import com.retailers.hnc.common.service.EmployeeManageService;
import com.retailers.hnc.common.vo.CheckUserVo;
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
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("checkUser")
public class CheckUserController extends BaseController {

    @Autowired
    CheckUserService checkUserService;

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
        List list = checkUserService.queryCheckUserList(params,1,999).getData();
        Map map = new HashMap();
        map.put("rows",list);
        return map;
    }

    @RequestMapping("queryCheckUserValidateCode")
    @ResponseBody
    public Map queryCheckUserValidateCode(String randStr){
        Long cid = getClientIdByOpenId(randStr);
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
        Long eid = getEmpIdByWxPhone(phone);
        Map params = new HashMap();
        params.put("isDelete",0L);
        params.put("isUse",isUse);
        params.put("oid",oid);
        if(ObjectUtils.isEmpty(isManage)){
            params.put("eid",eid);
        }
        List<CheckUserVo> checkUserVos = checkUserService.queryCheckUserVoList(params,pageNo,pageSize);
        Map map = new HashMap();
        map.put("rows",checkUserVos);
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
        Map map = new HashMap();
        List<CheckUserVo> list = checkUserService.queryAchievement(params);
        map.put("rows",list);
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
