package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.GoodsGdsprel;

/**
 * Created by Administrator on 2017/9/30.
 */
public class GoodsGdsprelVo extends GoodsGdsprel {
    private String gsName;
    private Float gdPrice;
    private Long gdResidueinventory;

    public String getGsName() {
        return gsName;
    }

    public void setGsName(String gsName) {
        this.gsName = gsName;
    }

    public Float getGdPrice() {
        return gdPrice;
    }

    public void setGdPrice(Float gdPrice) {
        this.gdPrice = gdPrice;
    }

    public Long getGdResidueinventory() {
        return gdResidueinventory;
    }

    public void setGdResidueinventory(Long gdResidueinventory) {
        this.gdResidueinventory = gdResidueinventory;
    }
}
