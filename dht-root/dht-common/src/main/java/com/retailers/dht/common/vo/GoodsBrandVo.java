package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.GoodsBrand;
import com.retailers.dht.common.entity.GoodsClassification;

/**
 * Created by Administrator on 2017/9/30.
 */
public class GoodsBrandVo extends GoodsBrand {
    private String imgUrl;

    public void setImgUrl(String value) {
        this.imgUrl = value;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }


}
