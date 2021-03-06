package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.GoodsGdsprel;

/**
 * Created by Administrator on 2017/9/30.
 */
public class GoodsGdsprelVo extends GoodsGdsprel {
    private String gsName;
    private Long gdPrice;
    private Long gdResidueinventory;
    private Long sumcount;
    private String imgurl;
    private String gunitname;
    /**
     *会员是否打折 0不打折 1打折
     */
    private Long isMenberdiscount;
    /**
     * 商品所属子类
     */
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
