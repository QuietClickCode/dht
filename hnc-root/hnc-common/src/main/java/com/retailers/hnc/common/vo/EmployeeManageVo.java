package com.retailers.hnc.common.vo;

import com.retailers.hnc.common.entity.EmployeeManage;

/**
 * Created by niconiconi on 2017/12/27.
 */
public class EmployeeManageVo extends EmployeeManage {
    private Integer CurRegisterClientCount;

    private Integer RegisterClientCount;

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
