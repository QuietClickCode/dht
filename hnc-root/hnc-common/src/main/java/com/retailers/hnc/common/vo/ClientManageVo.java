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
    private String hids;
    private String fids;
    private String floorsName;
    private String hoursesName;
    private String imgurl;

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getHoursesName() {
        return hoursesName;
    }

    public void setHoursesName(String hoursesName) {
        this.hoursesName = hoursesName;
    }

    public String getFloorsName() {
        return floorsName;
    }

    public void setFloorsName(String floorsName) {
        this.floorsName = floorsName;
    }

    public String getFids() {
        return fids;
    }

    public void setFids(String fids) {
        this.fids = fids;
    }

    public String getHids() {
        return hids;
    }

    public void setHids(String hids) {
        this.hids = hids;
    }

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
