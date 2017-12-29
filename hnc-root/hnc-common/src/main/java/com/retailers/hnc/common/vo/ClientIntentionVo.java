package com.retailers.hnc.common.vo;

import com.retailers.hnc.common.entity.*;

import java.util.List;

/**
 * Created by niconiconi on 2017/12/15.
 */
public class ClientIntentionVo extends ClientIntention {
    private String floorsName;
    private String hoursesName;

    public String getFloorsName() {
        return floorsName;
    }

    public void setFloorsName(String floorsName) {
        this.floorsName = floorsName;
    }

    public String getHoursesName() {
        return hoursesName;
    }

    public void setHoursesName(String hoursesName) {
        this.hoursesName = hoursesName;
    }
}
