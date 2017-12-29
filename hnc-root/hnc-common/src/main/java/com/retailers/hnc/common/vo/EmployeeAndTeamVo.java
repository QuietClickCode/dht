package com.retailers.hnc.common.vo;

import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.entity.Team;

/**
 * Created by niconiconi on 2017/12/28.
 */
public class EmployeeAndTeamVo {
    private String teamName;
    private String employeeName;
    private Long tId;
    private Team team;
    private EmployeeManage employeeManage;
    private Long level;
    private Long parentId;
    private Long reservationCount;
    private Long isDelete;

    public long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
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

    public Long getReservationCount() {
        return reservationCount;
    }

    public void setReservationCount(Long reservationCount) {
        this.reservationCount = reservationCount;
    }
}
