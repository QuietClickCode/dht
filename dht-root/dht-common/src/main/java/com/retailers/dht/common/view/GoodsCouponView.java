package com.retailers.dht.common.view;

import com.alibaba.fastjson.annotation.JSONField;
import com.retailers.tools.utils.NumberUtils;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 商品优惠 web 端展示
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/16
 */
public class GoodsCouponView implements Comparable<GoodsCouponView>{
    /**gcpId*/
    private Long gcpId;
    /**商品优惠活动名称*/
    private String gcpName;
    /**商品优惠活动类型(0 优惠现金，1 总价折扣）*/
    private Integer gcpType;
    /**是否限制使用（0 无限制，1限制使用（指定商品种类/ 指定商品）)**/
    private Long gcpIsRestricted;
    /**商品优惠活动触发条件*/
    @JSONField(serialize =false )
    private Long gcpCondition;
    /**
     * 优惠条件计量单位（0 元，1 件）
     */
    private Long gcpUnits;
    /**是否重叠使用*/
    private Integer gcpIsOverlapUse;
    /**商品优惠开始时间*/
    private Date gcpStartTime;
    /**商品优惠结束时间*/
    private Date gcpEndTime;
    /**优惠值*/
    private Long val;
    /**
     * 是否允许使用
     */
    private String allow;
    /**
     * 商品id
     */
    private Long gid;

    public Long getGcpId() {
        return gcpId;
    }

    public void setGcpId(Long gcpId) {
        this.gcpId = gcpId;
    }

    public String getGcpName() {
        return gcpName;
    }

    public void setGcpName(String gcpName) {
        this.gcpName = gcpName;
    }

    public Integer getGcpType() {
        return gcpType;
    }

    public void setGcpType(Integer gcpType) {
        this.gcpType = gcpType;
    }

    public Long getGcpIsRestricted() {
        return gcpIsRestricted;
    }

    public void setGcpIsRestricted(Long gcpIsRestricted) {
        this.gcpIsRestricted = gcpIsRestricted;
    }

    public Long getGcpCondition() {
        return gcpCondition;
    }
    public String getGcpConditions(){
        return  NumberUtils.formaterNumberPower(gcpCondition);
    }


    public void setGcpCondition(Long gcpCondition) {
        this.gcpCondition = gcpCondition;
    }

    public Long getGcpUnits() {
        return gcpUnits;
    }

    public void setGcpUnits(Long gcpUnits) {
        this.gcpUnits = gcpUnits;
    }

    public Integer getGcpIsOverlapUse() {
        return gcpIsOverlapUse;
    }

    public void setGcpIsOverlapUse(Integer gcpIsOverlapUse) {
        this.gcpIsOverlapUse = gcpIsOverlapUse;
    }

    public Date getGcpStartTime() {
        return gcpStartTime;
    }

    public void setGcpStartTime(Date gcpStartTime) {
        this.gcpStartTime = gcpStartTime;
    }

    public Date getGcpEndTime() {
        return gcpEndTime;
    }

    public void setGcpEndTime(Date gcpEndTime) {
        this.gcpEndTime = gcpEndTime;
    }
    public Long getLongVal(){
        return val;
    }
    public String getVal() {
        return NumberUtils.formaterNumberPower(val);
    }

    public void setVal(Long val) {
        this.val = val;
    }

    public String getAllow() {
        return allow;
    }

    public void setAllow(String allow) {
        this.allow = allow;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public int compareTo(GoodsCouponView o) {
        return 0;
    }
}
