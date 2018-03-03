package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.CashOrder;
import com.retailers.tools.utils.NumberUtils;

/**
 * 提现订单 后台页面展示效果
 * @author zhongp
 * @version 1.0.1
 * @data 2018/3/1
 */
public class CashOrderVo extends CashOrder {
    /**
     * 提现用户
     */
    private String userName;
    /**
     * 审核 用户
     */
    private String auditingNm;
    /**
     * 下划用户（最后下划金额）
     */
    private String cashNm;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuditingNm() {
        return auditingNm;
    }

    public void setAuditingNm(String auditingNm) {
        this.auditingNm = auditingNm;
    }

    public String getCashNm() {
        return cashNm;
    }

    public void setCashNm(String cashNm) {
        this.cashNm = cashNm;
    }

    public String getCashMoney() {
        return NumberUtils.formaterNumberPower(coMoney);
    }

    public String getCashActualMoney(){return NumberUtils.formaterNumberPower(coActualMoney);}

    public String getCashRate(){return NumberUtils.formaterNumber(coRate*100,2)+"%";}
}
