package com.retailers.sbj.common.vo;

import com.retailers.sbj.common.entity.Project;

import java.util.List;

/**
 * Created by niconiconi on 2017/12/15.
 */
public class ProjectVo extends Project {
    private String logoImgUrl;
    private List<String> imgsList;

    public List<String> getImgsList() {
        return imgsList;
    }

    public void setImgsList(List<String> imgsList) {
        this.imgsList = imgsList;
    }

    public String getLogoImgUrl() {
        return logoImgUrl;
    }

    public void setLogoImgUrl(String logoImgUrl) {
        this.logoImgUrl = logoImgUrl;
    }
}
