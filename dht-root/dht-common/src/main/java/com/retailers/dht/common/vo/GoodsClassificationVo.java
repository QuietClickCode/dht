package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.GoodsClassification;

/**
 * Created by Administrator on 2017/9/30.
 */
public class GoodsClassificationVo extends GoodsClassification {
    private Long level;

    private String parentName;

    private String homeName;

    private String imgUrl;

    public void setImgUrl(String value) {
        this.imgUrl = value;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setLevel(Long value) {
        this.level = value;
    }

    public Long getLevel() {
        return this.level;
    }

    public void setParentName(String value) {
        this.parentName = value;
    }

    public String getParentName() {
        return this.parentName;
    }

    public void setHomeName(String value) {
        this.homeName = value;
    }

    public String getHomeName() {
        return this.homeName;
    }
}
