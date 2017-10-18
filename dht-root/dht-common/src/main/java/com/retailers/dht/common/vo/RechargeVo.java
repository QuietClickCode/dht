package com.retailers.dht.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.tools.utils.NumberUtils;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/16
 */
public class RechargeVo {

    /**rid*/
    private Long rid;
    /**等级名称*/
    @Length(min = 0, max = 24)
    private String rname;
    /**会员卡图片*/
    private Long rlogo;
    /**
     * 会员卡图片
     */
    private String rlogoUrl;
    /**充值金额*/
    @Digits(integer=10,fraction = 2,message = "充值金额只允许在10位整数和2位小数范围内")
    @NotNull(message = "充值金额不能为空")
    private Double rprice;
    /**享受折扣*/
    @Digits(integer=1,fraction = 2,message = "享受折扣只允许在1位整数和2位小数范围内")
    private Double rdiscount;

    /**是否返现(0 不返现，1 返现）*/
    private Integer rcashback;
    /**创建时间*/
    private Date rcreateDate;
    /**创建用户*/
    @JSONField(serialize = false)
    private Long rcreateSid;
    /**快照id*/
    private String rsnapshot;
    /**是否有效（0 有效，1 无效）*/
    private Integer isValid;
    /**是否删除（0 未删作，1 删除）*/
    @JSONField(serialize = false)
    private Integer isDelete;
    /**数据版本*/
    private Integer version;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Long getRlogo() {
        return rlogo;
    }

    public void setRlogo(Long rlogo) {
        this.rlogo = rlogo;
    }

    public Long getRprice() {
        return NumberUtils.priceChangeFen(rprice);
    }

    public void setRprice(Double rprice) {
        this.rprice = rprice;
    }

    public Long getRdiscount() {
        return NumberUtils.priceChangeFen(rdiscount);
    }

    public void setRdiscount(Double rdiscount) {
        this.rdiscount = rdiscount;
    }

    public Integer getRcashback() {
        return rcashback;
    }

    public void setRcashback(Integer rcashback) {
        this.rcashback = rcashback;
    }

    public Date getRcreateDate() {
        return rcreateDate;
    }

    public void setRcreateDate(Date rcreateDate) {
        this.rcreateDate = rcreateDate;
    }

    public Long getRcreateSid() {
        return rcreateSid;
    }

    public void setRcreateSid(Long rcreateSid) {
        this.rcreateSid = rcreateSid;
    }

    public String getRsnapshot() {
        return rsnapshot;
    }

    public void setRsnapshot(String rsnapshot) {
        this.rsnapshot = rsnapshot;
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

    public String getRpriceFormater() {
        return NumberUtils.formaterNumberrDoulbe(NumberUtils.priceChangeYuan(rprice),2);
    }
    public String getRdiscountFormater() {
        return NumberUtils.formaterNumberrDoulbe(NumberUtils.priceChangeYuan(rdiscount),2);
    }
    public void setRlogoUrl(String rlogoUrl) {
        this.rlogoUrl = rlogoUrl;
    }
    public String getRlogoUrl() {
        if(ObjectUtils.isNotEmpty(rlogoUrl)){
            if(rlogoUrl.indexOf(AttachmentConstant.IMAGE_SHOW_URL)==-1&&rlogoUrl.indexOf("http://")==-1){
                return StringUtils.concat(AttachmentConstant.IMAGE_SHOW_URL,rlogoUrl);
            }
        }
        return rlogoUrl;

    }
}
