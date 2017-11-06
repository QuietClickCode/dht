package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.GoodsGglrel;

/**
 * Created by Administrator on 2017/9/30.
 */
public class GoodsGglrelVo extends GoodsGglrel {
    private String labelname;
    private String gname;
    private  String gclassname;

    public void setLabelname(String value) {
        this.labelname = value;
    }

    public String getLabelname() {
        return this.labelname;
    }

    public void setGname(String value) {
        this.gname = value;
    }

    public String getGname() {
        return this.gname;
    }

    public String getGclassname() {
        return gclassname;
    }

    public void setGclassname(String gclassname) {
        this.gclassname = gclassname;
    }
}
