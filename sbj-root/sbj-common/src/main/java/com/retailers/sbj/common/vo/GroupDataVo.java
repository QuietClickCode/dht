package com.retailers.sbj.common.vo;

import java.util.List;

/**
 * Created by niconiconi on 2017/12/15.
 */
public class GroupDataVo  {
    private Long todayTotal;
    private Long noPresent;
    private Long regisTotal;
    private List<GroupItemVo> group;

    public List<GroupItemVo> getGroup() {
        return group;
    }

    public void setGroup(List<GroupItemVo> group) {
        this.group = group;
    }

    public Long getNoPresent() {
        return noPresent;
    }

    public void setNoPresent(Long noPresent) {
        this.noPresent = noPresent;
    }

    public Long getRegisTotal() {
        return regisTotal;
    }

    public void setRegisTotal(Long regisTotal) {
        this.regisTotal = regisTotal;
    }

    public Long getTodayTotal() {
        return todayTotal;
    }

    public void setTodayTotal(Long todayTotal) {
        this.todayTotal = todayTotal;
    }
}
