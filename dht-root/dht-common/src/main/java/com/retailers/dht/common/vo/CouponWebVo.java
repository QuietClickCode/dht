package com.retailers.dht.common.vo;

import java.util.Date;

/**
 * web端口优惠卷展示数据
 */
public class CouponWebVo {
    /**cpId*/
    private Long cpId;
    /**优惠卷名称*/
    private String cpName;
    /**优惠卷金额类型（0 固定金额，1 随机金额)*/
    private Integer cpCoinType;
    /**优惠类型（0 代金卷，1 折扣卷）*/
    private Integer cpType;
    /**卡卷图片地址*/
    private String cpLogoUrl;
    /**是否限制使用（0 无限制，1限制使用（指定商品种类/ 指定商品）)**/
    private Long cpIsRestricted;
    /**领取条件*/
    private String cpReceiveCondition;
    /**使用条件（直减/直折的时候可填写-1或者不填）*/
    private Long cpUseCondition;
    /**优惠卷 有效时间开始*/
    private Date cpStartDate;
    /**优惠卷 有效时间结束*/
    private Date cpEndDate;
    /**能否叠加使用（0 可以叠加使用，1 不可叠加使用）*/
    private Integer cpIsOverlapUse;
    /**是否优先使用(0 优先使用，1 不是优先使用）*/
    private Integer cpIsFirst;
    /**发送优惠卷张数*/
    private Long cpNum;
    /**发放开始时间*/
    private Date cpSendStartDate;
    /**发放结束时间*/
    private Date cpSendEndDate;
    /**
     * 优惠卷剩余张数
     */
    private Long cpSurplusNum;
    /**拼手气折扣浮动最小值*/
    private Long cpMinDiscount;
    /**拼手气折扣浮动最大值*/
    private Long cpMaxDiscount;
    /**
     * 使用最后期限
     */
    private String useTime;
    /**
     * 优惠额度
     */
    private Double couponVal;
    /**
     * 使用条件 （中文展示）
     */
    private String useCondition;
    /**
     * 优惠卷使用范围
     */
    private String useRange;
    /**
     * 优惠卷使用范围（商品id）
     */
    private String gIds;
    /**
     * 优惠卷使用范围（商品类型id）
     */
    private String ggIds;
    /**
     * 用户领取的优惠对应id
     */
    private Long cpuId;

    public Long getCpId() {
        return cpId;
    }

    public void setCpId(Long cpId) {
        this.cpId = cpId;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public Integer getCpCoinType() {
        return cpCoinType;
    }

    public void setCpCoinType(Integer cpCoinType) {
        this.cpCoinType = cpCoinType;
    }

    public Integer getCpType() {
        return cpType;
    }

    public void setCpType(Integer cpType) {
        this.cpType = cpType;
    }

    public String getCpLogoUrl() {
        return cpLogoUrl;
    }

    public void setCpLogoUrl(String cpLogoUrl) {
        this.cpLogoUrl = cpLogoUrl;
    }

    public Long getCpIsRestricted() {
        return cpIsRestricted;
    }

    public void setCpIsRestricted(Long cpIsRestricted) {
        this.cpIsRestricted = cpIsRestricted;
    }

    public String getCpReceiveCondition() {
        return cpReceiveCondition;
    }

    public void setCpReceiveCondition(String cpReceiveCondition) {
        this.cpReceiveCondition = cpReceiveCondition;
    }

    public Long getCpUseCondition() {
        return cpUseCondition;
    }

    public void setCpUseCondition(Long cpUseCondition) {
        this.cpUseCondition = cpUseCondition;
    }

    public Date getCpStartDate() {
        return cpStartDate;
    }

    public void setCpStartDate(Date cpStartDate) {
        this.cpStartDate = cpStartDate;
    }

    public Date getCpEndDate() {
        return cpEndDate;
    }

    public void setCpEndDate(Date cpEndDate) {
        this.cpEndDate = cpEndDate;
    }

    public Integer getCpIsOverlapUse() {
        return cpIsOverlapUse;
    }

    public void setCpIsOverlapUse(Integer cpIsOverlapUse) {
        this.cpIsOverlapUse = cpIsOverlapUse;
    }

    public Integer getCpIsFirst() {
        return cpIsFirst;
    }

    public void setCpIsFirst(Integer cpIsFirst) {
        this.cpIsFirst = cpIsFirst;
    }

    public Long getCpNum() {
        return cpNum;
    }

    public void setCpNum(Long cpNum) {
        this.cpNum = cpNum;
    }

    public Date getCpSendStartDate() {
        return cpSendStartDate;
    }

    public void setCpSendStartDate(Date cpSendStartDate) {
        this.cpSendStartDate = cpSendStartDate;
    }

    public Date getCpSendEndDate() {
        return cpSendEndDate;
    }

    public void setCpSendEndDate(Date cpSendEndDate) {
        this.cpSendEndDate = cpSendEndDate;
    }

    public Long getCpSurplusNum() {
        return cpSurplusNum;
    }

    public void setCpSurplusNum(Long cpSurplusNum) {
        this.cpSurplusNum = cpSurplusNum;
    }

    public Long getCpMinDiscount() {
        return cpMinDiscount;
    }

    public void setCpMinDiscount(Long cpMinDiscount) {
        this.cpMinDiscount = cpMinDiscount;
    }

    public Long getCpMaxDiscount() {
        return cpMaxDiscount;
    }

    public void setCpMaxDiscount(Long cpMaxDiscount) {
        this.cpMaxDiscount = cpMaxDiscount;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public Double getCouponVal() {
        return couponVal;
    }

    public void setCouponVal(Double couponVal) {
        this.couponVal = couponVal;
    }

    public String getUseCondition() {
        return useCondition;
    }

    public void setUseCondition(String useCondition) {
        this.useCondition = useCondition;
    }

    public String getUseRange() {
        return useRange;
    }

    public void setUseRange(String useRange) {
        this.useRange = useRange;
    }

    public String getgIds() {
        return gIds;
    }

    public void setgIds(String gIds) {
        this.gIds = gIds;
    }

    public String getGgIds() {
        return ggIds;
    }

    public void setGgIds(String ggIds) {
        this.ggIds = ggIds;
    }

    public Long getCpuId() {
        return cpuId;
    }

    public void setCpuId(Long cpuId) {
        this.cpuId = cpuId;
    }
}
