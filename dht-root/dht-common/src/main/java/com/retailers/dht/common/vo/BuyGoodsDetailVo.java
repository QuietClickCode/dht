package com.retailers.dht.common.vo;

import java.util.List;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/11
 */
public class BuyGoodsDetailVo {
    /**
     * 购买商品列表
     */
    private List<BuyGoodsVo> buyGoods;
    /**
     * 优惠卷id
     */
    private String cpIds;
    /**
     * 收货人地址id
     */
    private Long address;

    public List<BuyGoodsVo> getBuyGoods() {
        return buyGoods;
    }

    public void setBuyGoods(List<BuyGoodsVo> buyGoods) {
        this.buyGoods = buyGoods;
    }

    public String getCpIds() {
        return cpIds;
    }

    public void setCpIds(String cpIds) {
        this.cpIds = cpIds;
    }

    public Long getAddress() {
        return address;
    }

    public void setAddress(Long address) {
        this.address = address;
    }
}
