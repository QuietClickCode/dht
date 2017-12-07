package com.retailers.dht.common.vo;

import com.retailers.tools.utils.DateUtil;
import com.retailers.tools.utils.NumberUtils;
import com.retailers.tools.utils.ObjectUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import java.util.Date;

/**
 *
 */
public class GoodsCouponVo {
    /**gcpId*/
    private Long gcpId;
    /**商品优惠活动名称*/
    @Length(min = 2, max = 50)
    private String gcpName;
    /**商品优惠活动类型(0 优惠现金，1 总价折扣）*/
    private Integer gcpType;
    /**是否限制使用（0 无限制，1限制使用（指定商品种类/ 指定商品）)**/
    private Long gcpIsRestricted;
    /**商品优惠活动触发条件*/
    @Digits(integer=10,fraction = 2,message = "优惠条件只允许在10位整数和2位小数范围内")
    private Double gcpCondition;

    /**
     * 优惠条件计量单位（0 元，1 件）
     */
    private Long gcpUnits;
    /**是否重叠使用*/
    private Integer gcpIsOverlapUse;
    /**商品优惠开始时间*/
    private String gcpStartTime;
    /**商品优惠结束时间*/
    private String gcpEndTime;
    /**优惠金额*/
    @Digits(integer=10,fraction = 2,message = "优惠金额只允许在10位整数和2位小数范围内")
    private Double gcpMoney;
    /**优惠折扣*/
    @Digits(integer=1,fraction = 2,message = "优惠折扣只允许在2位整数和2位小数范围内")
    private Double gcpDiscount;
    /**创建时间*/
    private Date gcpCreateTime;
    /**是否有效*/
    private Integer isValid;
    /**是否删除*/
    private Integer isDelete;
    /**数据版本*/
    private Integer version;
    /**
     * 商品子类id
     */
    private String spzlId;
    /**
     * 商品子类名称
     */
    private String spzlNm;
    /**
     * 商品id
     */
    private String spId;
    /**
     * 商品名称
     */
    private String spNm;

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

    public Long getGcpCondition() {
        if(ObjectUtils.isNotEmpty(gcpCondition)){
            return NumberUtils.priceChangeFen(NumberUtils.formaterNumber(gcpCondition,2));
        }
        return null;
    }

    public void setGcpCondition(Double gcpCondition) {
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
        if(ObjectUtils.isNotEmpty(gcpStartTime)){
            return DateUtil.stringToDate(gcpEndTime,DateUtil.DATE_WITHSECOND_FORMAT);
        }
        return null;
    }

    public void setGcpStartTime(String gcpStartTime) {
        this.gcpStartTime = gcpStartTime;
    }

    public Date getGcpEndTime() {
        if(ObjectUtils.isNotEmpty(gcpEndTime)){
            return DateUtil.stringToDate(gcpEndTime,DateUtil.DATE_WITHSECOND_FORMAT);
        }
        return null;
    }

    public void setGcpEndTime(String gcpEndTime) {
        this.gcpEndTime = gcpEndTime;
    }

    public Long getGcpMoney() {
        if(ObjectUtils.isNotEmpty(gcpMoney)){
            return NumberUtils.priceChangeFen(NumberUtils.formaterNumber(gcpMoney,2));
        }
        return null;
    }

    public void setGcpMoney(Double gcpMoney) {
        this.gcpMoney = gcpMoney;
    }

    public Long getGcpDiscount() {
        if(ObjectUtils.isNotEmpty(gcpDiscount)){
            return NumberUtils.priceChangeFen(NumberUtils.formaterNumber(gcpDiscount,2));
        }
        return null;
    }

    public void setGcpDiscount(Double gcpDiscount) {
        this.gcpDiscount = gcpDiscount;
    }

    public Date getGcpCreateTime() {
        return gcpCreateTime;
    }

    public void setGcpCreateTime(Date gcpCreateTime) {
        this.gcpCreateTime = gcpCreateTime;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getGcpIsRestricted() {
        return gcpIsRestricted;
    }

    public void setGcpIsRestricted(Long gcpIsRestricted) {
        this.gcpIsRestricted = gcpIsRestricted;
    }

    public String getSpzlId() {
        return spzlId;
    }

    public void setSpzlId(String spzlId) {
        this.spzlId = spzlId;
    }

    public String getSpzlNm() {
        return spzlNm;
    }

    public void setSpzlNm(String spzlNm) {
        this.spzlNm = spzlNm;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getSpNm() {
        return spNm;
    }

    public void setSpNm(String spNm) {
        this.spNm = spNm;
    }
}
