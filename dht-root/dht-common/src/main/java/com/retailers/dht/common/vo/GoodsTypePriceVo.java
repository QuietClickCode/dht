package com.retailers.dht.common.vo;

/**
 * 购买商品对应的商品id 商品类型，商品总价
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/16
 */
public class GoodsTypePriceVo {
    /**
     * 商品id
     */
    private Long gId;
    /**
     *商品规格
     */
    private Long gdId;
    /**
     * 商品数量
     */
    private Long gType;
    /**
     * 商品总价
     */
    private Long gPrice;
    /**
     * 商品数量
     */
    private Long num;
    public GoodsTypePriceVo(){

    }
    public GoodsTypePriceVo(Long gid,Long gdId,Long gType,Long gPrice,Long num){
        this.gId=gid;
        this.gType=gType;
        this.gPrice=gPrice;
        this.num=num;
        this.gdId=gdId;
    }
    public Long getgId() {
        return gId;
    }

    public void setgId(Long gId) {
        this.gId = gId;
    }

    public Long getgType() {
        return gType;
    }

    public void setgType(Long gType) {
        this.gType = gType;
    }

    public Long getgPrice() {
        return gPrice;
    }

    public void setgPrice(Long gPrice) {
        this.gPrice = gPrice;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getGdId() {
        return gdId;
    }

    public void setGdId(Long gdId) {
        this.gdId = gdId;
    }
}
