package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.Famer;

/**
 * Created by Administrator on 2017/9/30.
 */
public class FamerVo extends Famer {
    private String imgUrl;
    private Long uid;
    private Boolean isJq;//是否结亲

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Boolean getJq() {
        return isJq;
    }

    public void setJq(Boolean jq) {
        isJq = jq;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
