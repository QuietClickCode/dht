package com.retailers.dht.common.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by niconiconi on 2017/10/23.
 */
public class FloorAdvertisingVo {
    /**faId*/
    @NotEmpty
    private Long faId;
    /**父类ID*/
    @NotEmpty
    private Long parentId;
    /**楼层广告名称*/
    @NotEmpty
    @Length(min = 1, max = 200)
    private String faName;
    /**排序*/
    @NotEmpty
    private Long faOrder;
    /**是否显示*/
    @NotEmpty
    private Long isShow;
    /**版本号*/
    @NotEmpty
    private Long version;

    private Long level;

    private Long isDelete;

    private Long imageId;

    private String imageUrl;


    public String getFaName() {
        return faName;
    }

    public void setFaName(String faName) {
        this.faName = faName;
    }

    public Long getFaOrder() {
        return faOrder;
    }

    public void setFaOrder(Long faOrder) {
        this.faOrder = faOrder;
    }

    public Long getIsShow() {
        return isShow;
    }

    public void setIsShow(Long isShow) {
        this.isShow = isShow;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
    }

    public Long getFaId() {
        return faId;
    }

    public void setFaId(Long faId) {
        this.faId = faId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
