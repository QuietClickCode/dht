package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.GoodsGdcprel;

/**
 * Created by Administrator on 2017/9/30.
 */
public class GoodsGdcprelVo extends GoodsGdcprel {
    private String gsName;
    private Long gdPrice;
    private Long gdResidueinventory;
    private Long sumcount;
    private String imgurl;
    private String gunitname;


    public String getGsName() {
        return gsName;
    }

    public void setGsName(String gsName) {
        this.gsName = gsName;
    }

    public Long getGdPrice() {
        return gdPrice;
    }

    public void setGdPrice(Long gdPrice) {
        this.gdPrice = gdPrice;
    }

    public Long getGdResidueinventory() {
        return gdResidueinventory;
    }

    public void setGdResidueinventory(Long gdResidueinventory) {
        this.gdResidueinventory = gdResidueinventory;
    }

    public Long getSumcount() {
        return sumcount;
    }

    public void setSumcount(Long sumcount) {
        this.sumcount = sumcount;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getGunitname() {
        return gunitname;
    }

    public void setGunitname(String gunitname) {
        this.gunitname = gunitname;
    }
}
