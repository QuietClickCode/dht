package com.retailers.sbj.common.vo;

import com.retailers.sbj.common.entity.ClientIntention;

/**
 * Created by niconiconi on 2017/12/15.
 */
public class ClientIntentionVo extends ClientIntention {
    private String floorsName;
    private String hoursesName;
    private String channelName;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

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
