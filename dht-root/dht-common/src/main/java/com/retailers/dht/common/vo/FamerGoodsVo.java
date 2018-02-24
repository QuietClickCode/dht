package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.FamerGoods;

public class FamerGoodsVo extends FamerGoods {
    private String goodsName;
    private String fname;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
