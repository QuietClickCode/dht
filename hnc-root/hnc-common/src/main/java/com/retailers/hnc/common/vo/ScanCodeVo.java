package com.retailers.hnc.common.vo;

import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.entity.ScanCode;
import com.retailers.hnc.common.entity.Team;

/**
 * Created by niconiconi on 2017/12/31.
 */
public class ScanCodeVo extends ScanCode {
    private EmployeeManage employeeManage;
    private String emName;
    private String TeamName;
    private Team team;
    public EmployeeManage getEmployeeManage() {
        return employeeManage;
    }

    public void setEmployeeManage(EmployeeManage employeeManage) {
        this.employeeManage = employeeManage;
    }

    public String getEmName() {
        return emName;
    }

    public void setEmName(String emName) {
        this.emName = emName;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
