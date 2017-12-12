package com.retailers.dht.common.vo;

/**
 * 购买商品详情
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/11
 */
public class BuyGoodsVo {
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 购买数量
     */
    private Integer num;
    /**
     * 规则id
     */
    private Long spec;
    /**
     * 商品优惠id
     */
    private String gcpIds;
    /**
     * 备注
     */
    private String remark;

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

    public Long getSpec() {
        return spec;
    }

    public void setSpec(Long spec) {
        this.spec = spec;
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
