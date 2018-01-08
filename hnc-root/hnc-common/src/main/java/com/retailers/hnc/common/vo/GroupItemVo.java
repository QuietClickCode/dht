package com.retailers.hnc.common.vo;

import java.util.List;

/**
 * Created by niconiconi on 2017/12/15.
 */
public class GroupItemVo {
    private Long tid;
    private String tname;
    private Long groupTodayTotal;
    private Long groupNoPresent;
    private Long groupRegisTotal;
    private List<EmpDataVo> groupItem;

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public List<EmpDataVo> getGroupItem() {
        return groupItem;
    }

    public void setGroupItem(List<EmpDataVo> groupItem) {
        this.groupItem = groupItem;
    }

    public Long getGroupNoPresent() {
        return groupNoPresent;
    }

    public void setGroupNoPresent(Long groupNoPresent) {
        this.groupNoPresent = groupNoPresent;
    }

    public Long getGroupRegisTotal() {
        return groupRegisTotal;
    }

    public void setGroupRegisTotal(Long groupRegisTotal) {
        this.groupRegisTotal = groupRegisTotal;
    }

    public Long getGroupTodayTotal() {
        return groupTodayTotal;
    }

    public void setGroupTodayTotal(Long groupTodayTotal) {
        this.groupTodayTotal = groupTodayTotal;
    }
}
