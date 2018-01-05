package com.retailers.hnc.common.vo;

import com.retailers.hnc.common.entity.CheckUser;
import com.retailers.hnc.common.entity.ClientManage;

import java.util.Date;

/**
 * Created by niconiconi on 2017/12/15.
 */
public class CheckUserVo extends CheckUser {
    private Date openingDate;
    private String clientName;
    private String clientPhone;
    private String headImgUrl;
    private Long count;
    private String empName;
    private Long empId;
    private String tname;
    private Long tid;
    private Long useNum;
    private Long notuseNum;

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Long getUseNum() {
        return useNum;
    }

    public void setUseNum(Long useNum) {
        this.useNum = useNum;
    }

    public Long getNotuseNum() {
        return notuseNum;
    }

    public void setNotuseNum(Long notuseNum) {
        this.notuseNum = notuseNum;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }
}
