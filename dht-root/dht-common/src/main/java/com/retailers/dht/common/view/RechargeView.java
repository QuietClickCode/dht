package com.retailers.dht.common.view;

import com.alibaba.fastjson.annotation.JSONField;
import com.retailers.tools.encrypt.DESUtils;
import com.retailers.tools.encrypt.DesKey;
import com.retailers.tools.utils.NumberUtils;
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

    public String getRid() {
        try{
            return DESUtils.encryptDES(rid+"", DesKey.WEB_KEY);
        }catch(Exception e){
        }
        return "";
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

    public String getRprice() {
//        return rprice;
        return NumberUtils.formaterNumberPower(rprice);
    }

    public void setRprice(Long rprice) {
        this.rprice = rprice;
    }

    public String getRdiscount() {
        return NumberUtils.formaterNumberPower(rdiscount);
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
