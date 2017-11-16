package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.GoodsLabel;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Administrator on 2017/9/30.
 */
public class GoodsLabelVo extends GoodsLabel {

    /**父类ID*/
    @NotEmpty
    private Long parentId;
    /**上传图片返回的ID*/
    @NotEmpty
    private Long imageId;
    /**图片的链接*/
    @NotEmpty
    @Length(min = 1, max = 500)
    private String url;
    /**排序*/
    @NotEmpty
    private Long glOrder;
    /**推送对象*/
    @NotEmpty
    private Long glCountry;
    private String imgUrl;
    private Long level;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getGlOrder() {
        return glOrder;
    }

    public void setGlOrder(Long glOrder) {
        this.glOrder = glOrder;
    }

    public Long getGlCountry() {
        return glCountry;
    }

    public void setGlCountry(Long glCountry) {
        this.glCountry = glCountry;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }
}

