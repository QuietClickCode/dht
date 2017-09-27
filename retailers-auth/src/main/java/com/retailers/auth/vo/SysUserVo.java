package com.retailers.auth.vo;


import com.retailers.auth.entity.SysUser;

public class SysUserVo extends SysUser {
    /**
     * 职工所在部门ids
     */
    private String orgIds;
    /**
     * 职工所在部门名称
     */
    private String orgNms;

    public String getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(String orgIds) {
        this.orgIds = orgIds;
    }

    public String getOrgNms() {
        return orgNms;
    }

    public void setOrgNms(String orgNms) {
        this.orgNms = orgNms;
    }
}
