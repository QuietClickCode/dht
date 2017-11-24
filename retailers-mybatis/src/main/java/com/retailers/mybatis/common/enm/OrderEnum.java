package com.retailers.mybatis.common.enm;

/**
 * 系统订单类型（包涵t_order,t_game_order)
 * @author  zhongp
 * @version 1.0
 * @date 2017-11-21
 */
public enum OrderEnum {
    RECHARGE("RECHARGE", "充值", "CZ"),
    SHOPPING("SHOPPING", "购买商品", "GM"),
    SECKILL("SECKILL", "秒杀", "MS"),
    GROUP_BUYING("GROUP_BUYING", "团购", "tg"),
    PRE_ORDER("PRE_ORDER", "预购", "YG");
    //SHOPPING("SHOPPING","购买商品","GM"), recharge
    //SHOPPING("SHOPPING","购买商品","GM");
    String key, value, shorNm;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getShorNm() {
        return shorNm;
    }

    public void setShorNm(String shorNm) {
        this.shorNm = shorNm;
    }

    OrderEnum(String key, String value, String shorNm) {
        this.key = key;
        this.value = value;
        this.shorNm = shorNm;
    }
    public static OrderEnum getOrderType(String key) {
        OrderEnum[] typeEnumArray = OrderEnum.values();
        for (OrderEnum typeEnum : typeEnumArray) {
            if (key.equals(typeEnum.getKey())) {
                return typeEnum;
            }
        }
        return null;
    }
}
