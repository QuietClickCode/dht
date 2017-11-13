package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.GoodsGgsval;

/**
 * Created by Administrator on 2017/10/19.
 */
public class GoodsGgsvalOnceVo {
    private Long gsid;
    private String gsname;
    private String gsvidstr;
    private String gsvvalstr;
    private String selectedgsvidstr;

    public Long getGsid() {
        return gsid;
    }

    public void setGsid(Long gsid) {
        this.gsid = gsid;
    }

    public String getGsname() {
        return gsname;
    }

    public void setGsname(String gsname) {
        this.gsname = gsname;
    }

    public String getGsvidstr() {
        return gsvidstr;
    }

    public void setGsvidstr(String gsvidstr) {
        this.gsvidstr = gsvidstr;
    }

    public String getGsvvalstr() {
        return gsvvalstr;
    }

    public void setGsvvalstr(String gsvvalstr) {
        this.gsvvalstr = gsvvalstr;
    }

    public String getSelectedgsvidstr() {
        return selectedgsvidstr;
    }

    public void setSelectedgsvidstr(String selectedgsvidstr) {
        this.selectedgsvidstr = selectedgsvidstr;
    }
}