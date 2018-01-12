package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.Team;
import com.retailers.hnc.common.service.CheckUserService;
import com.retailers.hnc.common.service.TeamService;
import com.retailers.hnc.common.vo.CheckUserVo;
import com.retailers.hnc.manage.base.BaseController;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("checkUser")
public class CheckUserController extends BaseController {

    @Autowired
    CheckUserService checkUserService;

    @Autowired
    TeamService teamService;

    @RequestMapping("/employeeManageMapping")
    @Menu(parentRes = "sys.manager.employee",resourse = "checkUser.checkUserMapping",description = "业绩查询",label = "业绩查询")
    public String employeeManageMapping(){
        return "checkUser";
    }

    @RequestMapping("queryAchievement")
    @ResponseBody
    public Map queryAchievement(Long oid,String empIds,String tids){
        List<Long> emIdList = null;
        List<Long> tidsList = null;
        if(!ObjectUtils.isEmpty(empIds))
            emIdList = StringToList(empIds);
        if(!ObjectUtils.isEmpty(tids))
            tidsList = StringToList(tids);
        Map params = new HashMap();
        if(ObjectUtils.isNotEmpty(tids)){
            params.put("tidList",tidsList);
        }else{
            params.put("emIdList",emIdList);
        }
        params.put("oid",oid);
        Map map = new HashMap();
        List<CheckUserVo> list = checkUserService.queryAchievement(params);
        long id = 30000;
        HashSet<Long> set = new HashSet<Long>();
        for(CheckUserVo checkUserVo:list){
            set.add(checkUserVo.getTid());
            checkUserVo.setTid(checkUserVo.getTid());
            checkUserVo.setTname("");
            checkUserVo.setLevel(2L);
            checkUserVo.setId(id++);
        }
        List<Team> teams = teamService.queryAllTeam();

        if(emIdList != null){
            for (Long aLong:set){
                for (Team team : teams) {
                    if(aLong == team.getTid()){
                        CheckUserVo userVo = new CheckUserVo();
                        userVo.setId(team.getTid());
                        userVo.setTname(team.getTname());
                        userVo.setLevel(1L);
                        setCheckUserCount(userVo,list);
                        list.add(userVo);
                    }
                }
            }
        }else if(tidsList != null){
            for (Long aLong : tidsList) {
                for (Team team : teams) {
                    if(aLong == team.getTid()){
                        CheckUserVo userVo = new CheckUserVo();
                        userVo.setId(team.getTid());
                        userVo.setTname(team.getTname());
                        userVo.setLevel(1L);
                        setCheckUserCount(userVo,list);
                        list.add(userVo);
                    }
                }
            }
        }else if(empIds == null && tids == null){
            for (Team team : teams) {
                CheckUserVo userVo = new CheckUserVo();
                userVo.setId(team.getTid());
                userVo.setTname(team.getTname());
                userVo.setLevel(1L);
                setCheckUserCount(userVo,list);
                list.add(userVo);
            }
        }

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

    public void setCheckUserCount(CheckUserVo userVo,List<CheckUserVo> userVos){
        Long groupUseTotal = 0L;
        Long groupNoUseTotal = 0L;
        Long groupCount = 0L;
        for (CheckUserVo vo : userVos) {
            if(userVo.getId() == vo.getTid()){
                groupUseTotal += vo.getUseNum();
                groupNoUseTotal += vo.getNotuseNum();
                groupCount += vo.getCount();
            }
        }
        userVo.setNotuseNum(groupNoUseTotal);
        userVo.setUseNum(groupUseTotal);
        userVo.setCount(groupUseTotal + groupNoUseTotal);
    }
}
