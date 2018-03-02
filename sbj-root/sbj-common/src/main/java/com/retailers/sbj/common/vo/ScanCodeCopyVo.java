package com.retailers.sbj.common.vo;

import com.retailers.sbj.common.entity.ScanCodeCopy;

/**
 * Created by niconiconi on 2017/12/27.
 */
public class ScanCodeCopyVo extends ScanCodeCopy {
    private String emName;
    private String uploadpersonName;

    public String getEmName() {
        return emName;
    }

    public void setEmName(String emName) {
        this.emName = emName;
    }

    public String getUploadpersonName() {
        return uploadpersonName;
    }

    public void setUploadpersonName(String uploadpersonName) {
        this.uploadpersonName = uploadpersonName;
    }

}
