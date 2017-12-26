package com.retailers.dht.common.vo;

import com.retailers.tools.utils.NumberUtils;

import java.util.Date;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/27
 */
public class RecommendStatisticsVo {
    /**rsId*/
    private Long rsId;
    /**推荐类型（0 首单推荐，1 消费推荐）*/
    private Integer rsType;
    /**状态（0，未结算，1 己结算）*/
    private Integer rsStatus;
    /**推荐消费订单id*/
    private Long rsOid;
    /**
     * 订单号
     */
    private String rsOrderNo;
    /**推荐消费订单详情id*/
    private Long rsOdId;
    //商品名称
    private String goodsNm;
    /**购买用户*/
    private Long rsUid;
    /**
     * 购买人
     */
    private String rsNm;
    /**推荐人*/
    private Long rsRecommendUid;
    /**
     * 推荐人姓名
     */
    private String rsRecommendNm;
    /**推荐人类型（1 兼职人员，2推广人员）*/
    private Long rsUtype;
    /**消费金额*/
    private Long rsSalesPrice;
    /**提成比例*/
    private Long rsRatio;
    /**rsPrice*/
    private Long rsPrice;
    /**创建时间*/
    private Date rsTime;

    public Long getRsId() {
        return rsId;
    }

    public void setRsId(Long rsId) {
        this.rsId = rsId;
    }

    public Integer getRsType() {
        return rsType;
    }

    public void setRsType(Integer rsType) {
        this.rsType = rsType;
    }

    public Integer getRsStatus() {
        return rsStatus;
    }

    public void setRsStatus(Integer rsStatus) {
        this.rsStatus = rsStatus;
    }

    public Long getRsOid() {
        return rsOid;
    }

    public void setRsOid(Long rsOid) {
        this.rsOid = rsOid;
    }

    public String getRsOrderNo() {
        return rsOrderNo;
    }

    public void setRsOrderNo(String rsOrderNo) {
        this.rsOrderNo = rsOrderNo;
    }

    public Long getRsOdId() {
        return rsOdId;
    }

    public void setRsOdId(Long rsOdId) {
        this.rsOdId = rsOdId;
    }

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    public Long getRsUid() {
        return rsUid;
    }

    public void setRsUid(Long rsUid) {
        this.rsUid = rsUid;
    }

    public String getRsNm() {
        return rsNm;
    }

    public void setRsNm(String rsNm) {
        this.rsNm = rsNm;
    }

    public Long getRsRecommendUid() {
        return rsRecommendUid;
    }

    public void setRsRecommendUid(Long rsRecommendUid) {
        this.rsRecommendUid = rsRecommendUid;
    }

    public String getRsRecommendNm() {
        return rsRecommendNm;
    }

    public void setRsRecommendNm(String rsRecommendNm) {
        this.rsRecommendNm = rsRecommendNm;
    }

    public Long getRsUtype() {
        return rsUtype;
    }

    public void setRsUtype(Long rsUtype) {
        this.rsUtype = rsUtype;
    }

    public String getRsSalesPrice() {
        return NumberUtils.formaterNumberPower(rsSalesPrice);
    }

    public void setRsSalesPrice(Long rsSalesPrice) {
        this.rsSalesPrice = rsSalesPrice;
    }

    public Long getRsRatio() {
        return rsRatio;
    }

    public void setRsRatio(Long rsRatio) {
        this.rsRatio = rsRatio;
    }

    public String getRsPrice() {
        return NumberUtils.formaterNumberPower(rsPrice);
    }

    public void setRsPrice(Long rsPrice) {
        this.rsPrice = rsPrice;
    }

    public Date getRsTime() {
        return rsTime;
    }

    public void setRsTime(Date rsTime) {
        this.rsTime = rsTime;
    }
}
