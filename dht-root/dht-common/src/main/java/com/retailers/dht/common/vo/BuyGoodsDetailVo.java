package com.retailers.dht.common.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 购买商品详情
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/11
 */
public class BuyGoodsDetailVo {
    /**
     * 购物车id
     */
    private Long buyCartId;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 购买数量
     */
    private Integer num;
    /**
     * 商品详情表（关联了价格和库存，与t_goods_ggsval_detail 关联，能找到对应的商品规格）
     */
    private Long gdId;
    /**
     * 商品优惠id
     */
    private String gcpIds;
    /**
     * 备注
     */
    private String remark;

    public Long getBuyCartId() {
        return buyCartId;
    }

    public void setBuyCartId(Long buyCartId) {
        this.buyCartId = buyCartId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getGdId() {
        return gdId;
    }

    public void setGdId(Long gdId) {
        this.gdId = gdId;
    }

    public String getGcpIds() {
        return gcpIds;
    }

    public void setGcpIds(String gcpIds) {
        this.gcpIds = gcpIds;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
