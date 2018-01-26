package com.retailers.hnc.common.vo;

import com.retailers.hnc.common.entity.EmployeeManageCopy;

/**
 * Created by niconiconi on 2017/12/27.
 */
public class EmployeeManageCopyVo extends EmployeeManageCopy {
    private String tname;
    private String uploadpersonName;

    public String getUploadpersonName() {
        return uploadpersonName;
    }

    public void setUploadpersonName(String uploadpersonName) {
        this.uploadpersonName = uploadpersonName;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
}
