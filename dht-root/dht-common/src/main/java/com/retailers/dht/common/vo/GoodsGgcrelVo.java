package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.GoodsGgcrel;

/**
 * Created by Administrator on 2017/9/30.
 */
public class GoodsGgcrelVo extends GoodsGgcrel {
    private String gcname;
    private String gclassName;
    private String gname;

    public void setGcname(String value) {
        this.gcname = value;
    }
    public String getGcname() {
        return this.gcname;
    }

    public String getGclassName() {
        return gclassName;
    }
    public void setGclassName(String gclassName) {
        this.gclassName = gclassName;
    }

    public String getGname() {
        return gname;
    }
    public void setGname(String gname) {
        this.gname = gname;
    }
}
