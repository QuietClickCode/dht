package com.retailers.hnc.common.vo;

import com.retailers.hnc.common.entity.Opening;
import com.retailers.hnc.common.entity.Project;

import java.util.List;

/**
 * Created by niconiconi on 2017/12/15.
 */
public class OpeningVo extends Opening {
    private String hasFloors;

    public String getHasFloors() {
        return hasFloors;
    }

    public void setHasFloors(String hasFloors) {
        this.hasFloors = hasFloors;
    }
}
