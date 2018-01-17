package com.retailers.dht.common.view;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2018/1/17
 */
public class UserCashBackDetailView {
    /**
     * 类型
     */
    private String type;
    /**
     * 金额
     */
    private Long price;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
