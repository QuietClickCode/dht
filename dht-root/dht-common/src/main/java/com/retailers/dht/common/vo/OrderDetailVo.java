package com.retailers.dht.common.vo;

import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.tools.utils.NumberUtils;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;

/**
 * 订单详情
 */
public class OrderDetailVo {
    private  Long id;
    private Long odOrderId;
    private Long odGoodsPrice;
    private Long odActualPrice;
    private Long odMenberPrice;
    private Long odBuyNumber;
    private String remark;
    private Long gId;
    private Long gdPrice;
    private String gName;
    private Integer type;
    private String imgUrl;
    private String gImgUrl;
    private String gsName;
    private String orderType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOdOrderId() {
        return odOrderId;
    }

    public void setOdOrderId(Long odOrderId) {
        this.odOrderId = odOrderId;
    }

    public String getOdGoodsPrice() {
        return NumberUtils.formaterNumberPower(odGoodsPrice);
    }

    public void setOdGoodsPrice(Long odGoodsPrice) {
        this.odGoodsPrice = odGoodsPrice;
    }

    public String getOdActualPrice() {
        return NumberUtils.formaterNumberPower(odActualPrice);
    }

    public void setOdActualPrice(Long odActualPrice) {
        this.odActualPrice = odActualPrice;
    }

    public String getOdMenberPrice() {
        return NumberUtils.formaterNumberPower(odMenberPrice);
    }

    public void setOdMenberPrice(Long odMenberPrice) {
        this.odMenberPrice = odMenberPrice;
    }

    public Long getOdBuyNumber() {
        return odBuyNumber;
    }

    public void setOdBuyNumber(Long odBuyNumber) {
        this.odBuyNumber = odBuyNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getgId() {
        return gId;
    }

    public void setgId(Long gId) {
        this.gId = gId;
    }

    public String getGdPrice() {
        return NumberUtils.formaterNumberPower(gdPrice);
    }

    public void setGdPrice(Long gdPrice) {
        this.gdPrice = gdPrice;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImgUrl() {
        if(ObjectUtils.isNotEmpty(imgUrl)){
            if(imgUrl.indexOf(AttachmentConstant.IMAGE_SHOW_URL)==-1&&imgUrl.indexOf("http://")==-1){
                return StringUtils.concat(AttachmentConstant.IMAGE_SHOW_URL,imgUrl);
            }
        }
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getgImgUrl() {

        if(ObjectUtils.isNotEmpty(gImgUrl)){
            if(gImgUrl.indexOf(AttachmentConstant.IMAGE_SHOW_URL)==-1&&gImgUrl.indexOf("http://")==-1){
                return StringUtils.concat(AttachmentConstant.IMAGE_SHOW_URL,gImgUrl);
            }
        }
        return gImgUrl;
    }

    public void setgImgUrl(String gImgUrl) {
        this.gImgUrl = gImgUrl;
    }

    public String getGsName() {
        return gsName;
    }

    public void setGsName(String gsName) {
        this.gsName = gsName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
