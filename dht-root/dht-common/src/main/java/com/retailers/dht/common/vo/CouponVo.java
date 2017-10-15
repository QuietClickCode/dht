package com.retailers.dht.common.vo;

import com.retailers.tools.utils.DateUtil;
import com.retailers.tools.utils.NumberUtils;
import com.retailers.tools.utils.ObjectUtils;

import javax.validation.constraints.Digits;
import java.util.Date;

/**
 * 优惠卷 页面编辑
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/12
 */
public class CouponVo {
    /**cpId*/
    private Long cpId;
    /**优惠卷名称*/
    private String cpName;
    /**优惠卷金额类型（0 固定金额，1 随机金额)*/
    private Integer cpCoinType;
    /**优惠类型（0 代金卷，1 折扣卷）*/
    private Integer cpType;
    /**卡券图片（关联附件id）*/
    private Long cpLogo;
    /**
     * 卡卷图片地址
     */
    private String cpLogoUrl;
    /**是否限制使用（0 无限制，1 指定商品种类，2 指定商品）*/
    private Long cpIsRestricted;
    /**领取条件*/
    private String cpReceiveCondition;
    /**使用条件（直减/直折的时候可填写-1或者不填）*/
    @Digits(integer=10,fraction = 2,message = "优惠卷使用条件只允许在10位整数和2位小数范围内")
    private Double cpUseCondition;
    /**优惠卷 有效时间开始*/
    private String cpStartDate;
    /**优惠卷 有效时间结束*/
    private String cpEndDate;
    /**能否叠加使用（0 可以叠加使用，1 不可叠加使用）*/
    private Integer cpIsOverlapUse;
    /**是否优先使用(0 优先使用，1 不是优先使用）*/
    private Integer cpIsFirst;
    /**发放方式（0 及时发送，1 定时发送，2 周期发送）*/
    private Integer cpSendWay;
    /**发放开始时间*/
    private String cpSendStartDate;
    /**发放结束时间*/
    private String cpSendEndDate;
    /**周期发送类型（0 按年，1 按季，2 按月，3按周，4 按天）*/
    private Integer cpCycleSendType;
    /**周期发送次数次数*/
    private Long cpSendNum;
    /**发送优惠卷张数*/
    private Long cpNum;
    /**金额*/
    @Digits(integer=10,fraction = 2,message = "优惠卷金额只允许在10位整数和2位小数范围内")
    private Double cpMoney;
    /**折扣*/
    @Digits(integer=10,fraction = 2,message = "优惠卷折扣只允许在1位整数和2位小数范围内")
    private Double cpDiscount;
    /**发送优惠卷总金额（拼手气优惠卷 代金卷）*/
    @Digits(integer=10,fraction = 2,message = "优惠卷发送优惠卷总金额只允许在10位整数和2位小数范围内")
    private Double cpTotalMoney;
    /**拼手气折扣浮动最小值*/
    @Digits(integer=10,fraction = 2,message = "优惠卷拼手气折扣浮动最小值只允许在10位整数和2位小数范围内")
    private Double cpMinDiscount;
    /**拼手气折扣浮动最大值*/
    @Digits(integer=10,fraction = 2,message = "优惠卷拼手气折扣浮动最大值只允许在10位整数和2位小数范围内")
    private Double cpMaxDiscount;
    /**卡券领取与使用规则*/
    private String cpContext;
    /**优惠卷创建时间*/
    private Date cpCreate;
    /**是否删除（0 未删作，1 删除）*/
    private Integer isDelete;
    /**是否有效（0 有效，1 无效）*/
    private Integer isValid;
    /**数据版本*/
    private Integer version;

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

    public Long getCpLogo() {
        return cpLogo;
    }

    public void setCpLogo(Long cpLogo) {
        this.cpLogo = cpLogo;
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
        return formateNumber(cpUseCondition);
    }

    public void setCpUseCondition(Double cpUseCondition) {
        this.cpUseCondition = cpUseCondition;
    }

    public Date getCpStartDate() {
        return formateDate(cpStartDate);
    }

    public void setCpStartDate(String cpStartDate) {
        this.cpStartDate = cpStartDate;
    }

    public Date getCpEndDate() {
        return formateDate(cpEndDate);
    }

    public void setCpEndDate(String cpEndDate) {
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

    public Integer getCpSendWay() {
        return cpSendWay;
    }

    public void setCpSendWay(Integer cpSendWay) {
        this.cpSendWay = cpSendWay;
    }

    public Date getCpSendStartDate() {
        return formateDate(cpSendStartDate);
    }

    public void setCpSendStartDate(String cpSendStartDate) {
        this.cpSendStartDate = cpSendStartDate;
    }

    public Date getCpSendEndDate() {
        return formateDate(cpSendEndDate);
    }

    public void setCpSendEndDate(String cpSendEndDate) {
        this.cpSendEndDate = cpSendEndDate;
    }

    public Integer getCpCycleSendType() {
        return cpCycleSendType;
    }

    public void setCpCycleSendType(Integer cpCycleSendType) {
        this.cpCycleSendType = cpCycleSendType;
    }

    public Long getCpSendNum() {
        return cpSendNum;
    }

    public void setCpSendNum(Long cpSendNum) {
        this.cpSendNum = cpSendNum;
    }

    public Long getCpNum() {
        return cpNum;
    }

    public void setCpNum(Long cpNum) {
        this.cpNum = cpNum;
    }

    public Long getCpMoney() {
        return formateNumber(cpMoney);
    }

    public void setCpMoney(Double cpMoney) {
        this.cpMoney = cpMoney;
    }

    public Long getCpDiscount() {
        return formateNumber(cpDiscount);
    }

    public void setCpDiscount(Double cpDiscount) {
        this.cpDiscount = cpDiscount;
    }

    public Long getCpTotalMoney() {
        return formateNumber(cpTotalMoney);
    }

    public void setCpTotalMoney(Double cpTotalMoney) {
        this.cpTotalMoney = cpTotalMoney;
    }

    public Long getCpMinDiscount() {
        return formateNumber(cpMinDiscount);
    }

    public void setCpMinDiscount(Double cpMinDiscount) {
        this.cpMinDiscount = cpMinDiscount;
    }

    public Long getCpMaxDiscount() {
        return formateNumber(cpMaxDiscount);
    }

    public void setCpMaxDiscount(Double cpMaxDiscount) {
        this.cpMaxDiscount = cpMaxDiscount;
    }

    public String getCpContext() {
        return cpContext;
    }

    public void setCpContext(String cpContext) {
        this.cpContext = cpContext;
    }

    public Date getCpCreate() {
        return cpCreate;
    }

    public void setCpCreate(Date cpCreate) {
        this.cpCreate = cpCreate;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    private  Long formateNumber(Double num) {
        if(ObjectUtils.isNotEmpty(num)){
            return NumberUtils.priceChangeFen(NumberUtils.formaterNumber(num,2));
        }
        return null;
    }
    private Date formateDate(String date){
        if(ObjectUtils.isNotEmpty(date)){
            return DateUtil.stringToDate(date,DateUtil.DATE_WITHSECOND_FORMAT);
        }
        return null;
    }
}
