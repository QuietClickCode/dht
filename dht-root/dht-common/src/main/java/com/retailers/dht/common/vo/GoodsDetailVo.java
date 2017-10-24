package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.GoodsDetail;
import com.retailers.dht.common.entity.GoodsGgsval;

/**
 * Created by Administrator on 2017/10/19.
 */
public class GoodsDetailVo extends GoodsDetail {
    private String imgUrl;

    public void setImgUrl(String value) {
        this.imgUrl = value;
    }

    public String getImgUrl() {
        return this.imgUrl;

    }
}