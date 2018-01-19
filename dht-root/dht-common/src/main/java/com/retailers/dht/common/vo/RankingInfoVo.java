package com.retailers.dht.common.vo;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2018/1/20
 */
public class RankingInfoVo {
    /**
     * 返现类型
     */
    private Long rtnType;
    /**
     * 累计消费金额
     */
    private Long totalPrice;

    public Long getRtnType() {
        return rtnType;
    }

    public void setRtnType(Long rtnType) {
        this.rtnType = rtnType;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
