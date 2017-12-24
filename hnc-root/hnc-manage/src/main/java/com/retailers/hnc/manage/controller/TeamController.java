package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.Team;
import com.retailers.hnc.common.service.TeamService;
import com.retailers.hnc.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niconiconi on 2017/12/22.
 */
@Controller
@RequestMapping("team")
public class TeamController extends BaseController {
    @Autowired
    TeamService teamService;

    @RequestMapping("/teamMapping")
    @Menu(parentRes = "sys.manager.employee",resourse = "team.teamMapping",description = "团队管理",label = "团队管理")
    public String teamMapping(){
        return "employee/team";
    }

    @RequestMapping("/queryTeamList")
    @Function(label="团队集合", description = "团队集合", resourse = "team.queryTeamList",sort=1,parentRes="team.teamMapping")
    @ResponseBody
    public Map<String,Object> queryTeamList(PageUtils pageForm,String name){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        map.put("tname",name);
        Pagination<Team> teamPagination = teamService.queryTeamList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",teamPagination.getTotalCount());
        gtm.put("rows",teamPagination.getData());
        return gtm;
    }

    @RequestMapping("/addTeam")
    @Function(label = "添加团队",description = "添加团队",resourse = "team.addTeam",sort = 3,parentRes = "team.teamMapping")
    @ResponseBody
    public BaseResp addTeam(Team team){
        boolean flag = teamService.saveTeam(team);
        if(flag)
            return success("添加团队成功");
        else
            return success("添加团队失败");
    }

    @RequestMapping("/updateTeam")
    @Function(label = "修改团队信息",description = "修改团队信息",resourse = "team.updateTeam",sort = 3,parentRes = "team.teamMapping")
    @ResponseBody
    public BaseResp updateTeam(Team team){
        boolean flag = teamService.updateTeam(team);
        if(flag)
            return success("添加团队[" + team.getTname() + "]成功");
        else
            return errorForSystem("修改团队[" + team.getTname() + "]失败");
    }

    @RequestMapping("/removeTeam")
    @Function(label = "删除该团队",description = "删除该团队",resourse = "team.removeTeam",sort = 3,parentRes = "team.teamMapping")
    @ResponseBody
    public BaseResp removeTeam(Long tid){
        boolean flag = teamService.deleteTeamByTid(tid);
        return  success(flag);
    }
}
