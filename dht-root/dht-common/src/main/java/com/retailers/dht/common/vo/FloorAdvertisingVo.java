package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.FloorAdvertising;

/**
 * Created by niconiconi on 2017/10/23.
 */
public class FloorAdvertisingVo extends FloorAdvertising {

    private Long level;

    private String imageUrl;

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
