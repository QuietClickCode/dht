package com.retailers.hnc.common.vo;

import com.retailers.hnc.common.entity.CheckUser;
import com.retailers.hnc.common.entity.ClientManage;

import java.util.Date;

/**
 * Created by niconiconi on 2017/12/15.
 */
public class CheckUserVo extends CheckUser {
    private Date openingDate;

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }
}
