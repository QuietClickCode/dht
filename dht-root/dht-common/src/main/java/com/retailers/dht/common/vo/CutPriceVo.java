package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.CutPrice;

/**
 * Created by niconiconi on 2017/11/2.
 */
public class CutPriceVo extends CutPrice {
    private long level;
    private String gname;
    private Float minprice;
    private Float maxprice;
    private Long spinventory;
    private String imgurl;
    private Long timestap;
    private Float gprice;
    private String gunitname;
    private String gdescription;
    private Float cpSale;
    private Float minCpSale;

    public Float getMinCpSale() {
        return minCpSale;
    }

    public void setMinCpSale(Float minCpSale) {
        this.minCpSale = minCpSale;
    }

    public Float getCpSale() {
        return cpSale;
    }

    public void setCpSale(Float cpSale) {
        this.cpSale = cpSale;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGdescription() {
        return gdescription;
    }

    public void setGdescription(String gdescription) {
        this.gdescription = gdescription;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public Float getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(Float maxprice) {
        this.maxprice = maxprice;
    }

    public Float getMinprice() {
        return minprice;
    }

    public void setMinprice(Float minprice) {
        this.minprice = minprice;
    }

    public Long getSpinventory() {
        return spinventory;
    }

    public void setSpinventory(Long spinventory) {
        this.spinventory = spinventory;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Long getTimestap() {
        return timestap;
    }

    public void setTimestap(Long timestap) {
        this.timestap = timestap;
    }

    public Float getGprice() {
        return gprice;
    }

    public void setGprice(Float gprice) {
        this.gprice = gprice;
    }

    public String getGunitname() {
        return gunitname;
    }

    public void setGunitname(String gunitname) {
        this.gunitname = gunitname;
    }
}
