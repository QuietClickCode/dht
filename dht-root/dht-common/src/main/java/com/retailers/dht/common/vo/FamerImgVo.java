package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.FamerImg;

/**
 * 农户背景图片关联表
 */
public class FamerImgVo extends FamerImg {
    // 图片路径
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
