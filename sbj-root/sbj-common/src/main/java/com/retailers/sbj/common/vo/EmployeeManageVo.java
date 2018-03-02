package com.retailers.sbj.common.vo;

import com.retailers.sbj.common.entity.EmployeeManage;

/**
 * Created by niconiconi on 2017/12/27.
 */
public class EmployeeManageVo extends EmployeeManage {
    private Integer CurRegisterClientCount;
    private Integer RegisterClientCount;
    private String headImgUrl;
    private String tname;

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Integer getCurRegisterClientCount() {
        return CurRegisterClientCount;
    }

    public void setCurRegisterClientCount(Integer curRegisterClientCount) {
        CurRegisterClientCount = curRegisterClientCount;
    }

    public Integer getRegisterClientCount() {
        return RegisterClientCount;
    }

    public void setRegisterClientCount(Integer registerClientCount) {
        RegisterClientCount = registerClientCount;
    }
}
