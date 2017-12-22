package com.retailers.hnc.common.vo;

import com.retailers.hnc.common.entity.Project;
import com.retailers.hnc.common.entity.ProjectImg;

import java.util.List;

/**
 * Created by niconiconi on 2017/12/15.
 */
public class ProjectImgVo extends ProjectImg {
    private String piUrl;

    public String getPiUrl() {
        return piUrl;
    }

    public void setPiUrl(String piUrl) {
        this.piUrl = piUrl;
    }
}
