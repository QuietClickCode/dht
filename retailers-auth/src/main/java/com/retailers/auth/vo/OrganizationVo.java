package com.retailers.auth.vo;


import com.retailers.auth.entity.Organization;

/**
 * 组织机构用于前端展示
 */
public class OrganizationVo extends Organization {
    private Long level;

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }
}
