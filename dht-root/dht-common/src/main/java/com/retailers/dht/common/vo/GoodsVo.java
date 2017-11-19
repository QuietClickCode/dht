package com.retailers.dht.common.vo;
import com.retailers.dht.common.entity.Goods;

/**
 * Created by Administrator on 2017/9/30.
 */
public class GoodsVo extends Goods {
    private String gclassificationName;
    private String gareaName;
    private String imgUrl;
    private Float minprice;
    private Float maxprice;
    private Long inventory;

    public Float getMinprice() {
        return minprice;
    }

    public void setMinprice(Float minprice) {
        this.minprice = minprice;
    }

    public Float getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(Float maxprice) {
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
