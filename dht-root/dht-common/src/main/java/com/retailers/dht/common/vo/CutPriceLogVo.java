package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.CutPrice;
import com.retailers.dht.common.entity.CutPriceLog;

/**
 * Created by niconiconi on 2017/11/2.
 */
public class CutPriceLogVo extends CutPriceLog {
    private String uname;
    private String uimgurl;
    private Long cpMostperson;
    private Long cpLestperson;
    private Long gdPrice;
    private Long cpPrice;
    private String remark;
    private Long cpInventory;
    private Long isMenberdiscount;
    private Long gclass;

    public Long getGclass() {
        return gclass;
    }

    public void setGclass(Long gclass) {
        this.gclass = gclass;
    }

    public Long getIsMenberdiscount() {
        return isMenberdiscount;
    }

    public void setIsMenberdiscount(Long isMenberdiscount) {
        this.isMenberdiscount = isMenberdiscount;
    }

    public Long getCpInventory() {
        return cpInventory;
    }

    public void setCpInventory(Long cpInventory) {
        this.cpInventory = cpInventory;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCpLestperson() {
        return cpLestperson;
    }

    public void setCpLestperson(Long cpLestperson) {
        this.cpLestperson = cpLestperson;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUimgurl() {
        return uimgurl;
    }

    public void setUimgurl(String uimgurl) {
        this.uimgurl = uimgurl;
    }

    public Long getCpMostperson() {
        return cpMostperson;
    }

    public void setCpMostperson(Long cpMostperson) {
        this.cpMostperson = cpMostperson;
    }

    public Long getGdPrice() {
        return gdPrice;
    }

    public void setGdPrice(Long gdPrice) {
        this.gdPrice = gdPrice;
    }

    public Long getCpPrice() {
        return cpPrice;
    }

    public void setCpPrice(Long cpPrice) {
        this.cpPrice = cpPrice;
    }
}
