package com.retailers.dht.common.vo;
import com.retailers.dht.common.entity.Goods;

/**
 * Created by Administrator on 2017/9/30.
 */
public class GoodsVo extends Goods {
    private String gclassificationName;
    private String gareaName;
    private String imgUrl;
    private Long minprice;
    private Long maxprice;
    private Long inventory;
    private Long gendbuy;
    private Long gstartbuy;
    private String gdimgurl;
    private Long bcId;
    private String bcGsval;
    private String bcGsvalids;
    private Float gdPrice;
    private Long gcount;
    private Long isMultiplebuy;
    private Long ufId;
    private Long gdId;

    public Long getGdId() {
        return gdId;
    }

    public void setGdId(Long gdId) {
        this.gdId = gdId;
    }

    public Long getUfId() {
        return ufId;
    }

    public void setUfId(Long ufId) {
        this.ufId = ufId;
    }

    public String getBcGsvalids() {
        return bcGsvalids;
    }

    public void setBcGsvalids(String bcGsvalids) {
        this.bcGsvalids = bcGsvalids;
    }

    public Long getIsMultiplebuy() {
        return isMultiplebuy;
    }

    public void setIsMultiplebuy(Long isMultiplebuy) {
        this.isMultiplebuy = isMultiplebuy;
    }

    public Long getGcount() {
        return gcount;
    }

    public void setGcount(Long gcount) {
        this.gcount = gcount;
    }

    public Float getGdPrice() {
        return gdPrice;
    }

    public void setGdPrice(Float gdPrice) {
        this.gdPrice = gdPrice;
    }

    public String getBcGsval() {
        return bcGsval;
    }

    public void setBcGsval(String bcGsval) {
        this.bcGsval = bcGsval;
    }

    public Long getBcId() {
        return bcId;
    }

    public void setBcId(Long bcId) {
        this.bcId = bcId;
    }

    public String getGdimgurl() {
        return gdimgurl;
    }

    public void setGdimgurl(String gdimgurl) {
        this.gdimgurl = gdimgurl;
    }

    public Long getGstartbuy() {
        return gstartbuy;
    }

    public void setGstartbuy(Long gstartbuy) {
        this.gstartbuy = gstartbuy;
    }

    public Long getGendbuy() {
        return gendbuy;
    }

    public void setGendbuy(Long gendbuy) {
        this.gendbuy = gendbuy;
    }

    public Long getMinprice() {
        return minprice;
    }

    public void setMinprice(Long minprice) {
        this.minprice = minprice;
    }

    public Long getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(Long maxprice) {
        this.maxprice = maxprice;
    }

    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }

    public void setGareaName(String value) {
        this.gareaName = value;
    }

    public String getGareaName() {
        return this.gareaName;
    }

    public void setGclassificationName(String value) {
        this.gclassificationName = value;
    }

    public String getGclassificationName() {
        return this.gclassificationName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
