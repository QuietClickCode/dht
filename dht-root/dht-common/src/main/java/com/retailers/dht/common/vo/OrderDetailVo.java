package com.retailers.dht.common.vo;

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

    public Long getOdGoodsPrice() {
        return odGoodsPrice;
    }

    public void setOdGoodsPrice(Long odGoodsPrice) {
        this.odGoodsPrice = odGoodsPrice;
    }

    public Long getOdActualPrice() {
        return odActualPrice;
    }

    public void setOdActualPrice(Long odActualPrice) {
        this.odActualPrice = odActualPrice;
    }

    public Long getOdMenberPrice() {
        return odMenberPrice;
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

    public Long getGdPrice() {
        return gdPrice;
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
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getgImgUrl() {
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
