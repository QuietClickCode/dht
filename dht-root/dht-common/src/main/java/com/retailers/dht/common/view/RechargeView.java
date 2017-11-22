package com.retailers.dht.common.view;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 充值信息 （展示给用户查看)
 */
public class RechargeView {
    /**rid*/
    private Long rid;
    /**等级名称*/
    private String rname;
    /**
     * 会员卡图片
     */
    private String rlogoUrl;
    /**充值金额*/
    private Long rprice;
    /**享受折扣*/
    private Long rdiscount;
    /**是否返现(0 不返现，1 返现）*/
    private Integer rcashback;

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

    public String getRlogoUrl() {
        return rlogoUrl;
    }

    public void setRlogoUrl(String rlogoUrl) {
        this.rlogoUrl = rlogoUrl;
    }

    public Long getRprice() {
        return rprice;
    }

    public void setRprice(Long rprice) {
        this.rprice = rprice;
    }

    public Long getRdiscount() {
        return rdiscount;
    }

    public void setRdiscount(Long rdiscount) {
        this.rdiscount = rdiscount;
    }

    public Integer getRcashback() {
        return rcashback;
    }

    public void setRcashback(Integer rcashback) {
        this.rcashback = rcashback;
    }
}
