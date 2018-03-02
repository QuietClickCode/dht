package com.retailers.sbj.common.vo;

import com.retailers.sbj.common.entity.Opening;

/**
 * Created by niconiconi on 2017/12/15.
 */
public class OpeningVo extends Opening {
    private String hasFloors;
    private String fname;
    private Long fid;
    private String selectedfid;
    private String cuValidateCode;

    public String getCuValidateCode() {
        return cuValidateCode;
    }

    public void setCuValidateCode(String cuValidateCode) {
        this.cuValidateCode = cuValidateCode;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getSelectedfid() {
        return selectedfid;
    }

    public void setSelectedfid(String selectedfid) {
        this.selectedfid = selectedfid;
    }

    public String getHasFloors() {
        return hasFloors;
    }

    public void setHasFloors(String hasFloors) {
        this.hasFloors = hasFloors;
    }
}
