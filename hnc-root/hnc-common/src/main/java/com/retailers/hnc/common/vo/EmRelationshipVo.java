package com.retailers.hnc.common.vo;

import com.retailers.hnc.common.entity.EmRelationship;
import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.entity.Team;

import java.util.List;

/**
 * Created by niconiconi on 2017/12/29.
 */
public class EmRelationshipVo extends EmRelationship {
    private String teamName;
    private String employeeName;
    private Long tid;
    private Team team;
    private EmployeeManage employeeManage;
    private List<EmployeeManage> employeeManages;
    private Long level;
    private Long count;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public EmployeeManage getEmployeeManage() {
        return employeeManage;
    }

    public void setEmployeeManage(EmployeeManage employeeManage) {
        this.employeeManage = employeeManage;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public List<EmployeeManage> getEmployeeManages() {
        return employeeManages;
    }

    public void setEmployeeManages(List<EmployeeManage> employeeManages) {
        this.employeeManages = employeeManages;
    }
}
