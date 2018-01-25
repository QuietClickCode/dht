package com.retailers.hnc.common.vo;

import com.retailers.hnc.common.entity.EmployeeManageCopy;
import com.retailers.hnc.common.entity.OpeningCopy;

/**
 * Created by niconiconi on 2017/12/27.
 */
public class OpeningCopyVo extends OpeningCopy {
    private String uploadpersonName;

    public String getUploadpersonName() {
        return uploadpersonName;
    }

    public void setUploadpersonName(String uploadpersonName) {
        this.uploadpersonName = uploadpersonName;
    }

}
