package com.retailers.dht.common.vo;

import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.dao.AttachmentMapper;
import com.retailers.tools.utils.DateUtil;
import com.retailers.tools.utils.NumberUtils;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;

import javax.validation.constraints.Digits;
import java.util.Date;

/**
 * 优惠卷 页面展示
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/12
 */
public class CouponShowVo {
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
    /**卡卷图片地址*/
    private String cpLogoUrl;
    /**是否限制使用（0 无限制，1 指定商品种类，2 指定商品）*/
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
    /**发放方式（0 及时发送，1 定时发送，2 周期发送）*/
    private Integer cpSendWay;
    /**发放开始时间*/
    private Date cpSendStartDate;
    /**发放结束时间*/
    private Date cpSendEndDate;
    /**周期发送类型（0 按年，1 按季，2 按月，3按周，4 按天）*/
    private Integer cpCycleSendType;
    /**周期发送次数次数*/
    private Long cpSendNum;
    /**发送优惠卷张数*/
    private Long cpNum;
    /**
     * 优惠卷剩余张数
     */
    private Long cpSurplusNum;
    /**金额*/
    private Long cpMoney;
    /**折扣*/
    private Long cpDiscount;
    /**发送优惠卷总金额（拼手气优惠卷 代金卷）*/
    private Long cpTotalMoney;
    /**拼手气折扣浮动最小值*/
    private Long cpMinDiscount;
    /**拼手气折扣浮动最大值*/
    private Long cpMaxDiscount;
    /**卡券领取与使用规则*/
    private String cpContext;
    /**优惠卷创建时间*/
    private Date cpCreate;
    //创建人
    private Long cpCreateSid;
    /**是否删除（0 未删作，1 删除）*/
    private Integer isDelete;
    /**是否有效（0 有效，1 无效）*/
    private Integer isValid;
    /**数据版本*/
    private Integer version;
    //优惠卷使用范围id
    private String relevanceId;
    //优惠卷使用范围名称
    private String relevanceNm;

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
        if(ObjectUtils.isNotEmpty(cpLogoUrl)){
            if(cpLogoUrl.indexOf(AttachmentConstant.IMAGE_SHOW_URL)==-1&&cpLogoUrl.indexOf("http://")==-1){
                return StringUtils.concat(AttachmentConstant.IMAGE_SHOW_URL,cpLogoUrl);
            }
        }
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

    public String getCpUseCondition() {
        return NumberUtils.formaterNumberLong(cpUseCondition,2);
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

    public Integer getCpSendWay() {
        return cpSendWay;
    }

    public void setCpSendWay(Integer cpSendWay) {
        this.cpSendWay = cpSendWay;
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
    public String getCpMoney() {
        return NumberUtils.formaterNumberLong(cpMoney,2,true);
    }
    public void setCpMoney(Long cpMoney) {
        this.cpMoney = cpMoney;
    }

    public String getCpDiscount() {
        return NumberUtils.formaterNumberLong(cpDiscount,2,true);
    }

    public void setCpDiscount(Long cpDiscount) {
        this.cpDiscount = cpDiscount;
    }

    public String getCpTotalMoney() {
        return NumberUtils.formaterNumberLong(cpTotalMoney,2,true);
    }

    public void setCpTotalMoney(Long cpTotalMoney) {
        this.cpTotalMoney = cpTotalMoney;
    }

    public String getCpMinDiscount() {
        return NumberUtils.formaterNumberLong(cpMinDiscount,2,true);
    }

    public void setCpMinDiscount(Long cpMinDiscount) {
        this.cpMinDiscount = cpMinDiscount;
    }

    public String getCpMaxDiscount() {
        return NumberUtils.formaterNumberLong(cpMaxDiscount,2,true);
    }

    public void setCpMaxDiscount(Long cpMaxDiscount) {
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

    public Long getCpCreateSid() {
        return cpCreateSid;
    }

    public void setCpCreateSid(Long cpCreateSid) {
        this.cpCreateSid = cpCreateSid;
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

    public Long getCpSurplusNum() {
        return cpSurplusNum;
    }

    public void setCpSurplusNum(Long cpSurplusNum) {
        this.cpSurplusNum = cpSurplusNum;
    }

    public String getRelevanceId() {
        return relevanceId;
    }

    public void setRelevanceId(String relevanceId) {
        this.relevanceId = relevanceId;
    }

    public String getRelevanceNm() {
        return relevanceNm;
    }

    public void setRelevanceNm(String relevanceNm) {
        this.relevanceNm = relevanceNm;
    }
}
