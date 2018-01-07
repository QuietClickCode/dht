package com.retailers.hnc.common.vo;

import java.util.List;

/**
 * Created by niconiconi on 2018/1/7.
 */
public class EmployeeRelationshipVo{
    //预约关系表ID
    private Long erid;
    //开盘期数ID
    private Long pid;
    //预约人数
    private Long emReservation;
    //总登记人数
    private Long count;
    //置业顾问ID
    private Long emid;
    //置业顾问姓名
    private String emName;
    //团队ID
    private Long emTeam;
    //团队名称
    private String TeamName;
    //所属该团队的置业顾问
    private List<EmployeeRelationshipVo> employeeManages;

    public Long getErid() {
        return erid;
    }

    public void setErid(Long erid) {
        this.erid = erid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getEmReservation() {
        return emReservation;
    }

    public void setEmReservation(Long emReservation) {
        this.emReservation = emReservation;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getEmid() {
        return emid;
    }

    public void setEmid(Long emid) {
        this.emid = emid;
    }

    public String getEmName() {
        return emName;
    }

    public void setEmName(String emName) {
        this.emName = emName;
    }

    public Long getEmTeam() {
        return emTeam;
    }

    public void setEmTeam(Long emTeam) {
        this.emTeam = emTeam;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public List<EmployeeRelationshipVo> getEmployeeManages() {
        return employeeManages;
    }

    public void setEmployeeManages(List<EmployeeRelationshipVo> employeeManages) {
        this.employeeManages = employeeManages;
    }
}
