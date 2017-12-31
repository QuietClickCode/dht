package com.retailers.hnc.common.vo;

import com.retailers.hnc.common.entity.ClientIntention;
import com.retailers.hnc.common.entity.ClientManage;

/**
 * Created by niconiconi on 2017/12/15.
 */
public class ClientManageVo extends ClientManage {
    private String empName;
    private Long oecId;
    private String oecMsg;

    public String getOecMsg() {
        return oecMsg;
    }

    public void setOecMsg(String oecMsg) {
        this.oecMsg = oecMsg;
    }

    public Long getOecId() {
        return oecId;
    }

    public void setOecId(Long oecId) {
        this.oecId = oecId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
