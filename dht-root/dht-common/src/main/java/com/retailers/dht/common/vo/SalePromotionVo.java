package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.SalePromotion;

/**
 * Created by niconiconi on 2017/11/2.
 */
public class SalePromotionVo extends SalePromotion {
    private long level;

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }
}
