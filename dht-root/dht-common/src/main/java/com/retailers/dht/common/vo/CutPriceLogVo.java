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
    private Float gdPrice;
    private Float cpPrice;

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

    public Float getGdPrice() {
        return gdPrice;
    }

    public void setGdPrice(Float gdPrice) {
        this.gdPrice = gdPrice;
    }

    public Float getCpPrice() {
        return cpPrice;
    }

    public void setCpPrice(Float cpPrice) {
        this.cpPrice = cpPrice;
    }
}
