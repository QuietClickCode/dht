package com.retailers.dht.common.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;

/**
 * 购买信息
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/11
 */
public class BuyInfoVo {
    /**
     * 购买商品列表
     */
    private List<BuyGoodsDetailVo> buyGoods;
    /**
     * 优惠卷id
     */
    private String cpIds;
    /**
     * 收货人地址id
     */
    private Long address;

    public List<BuyGoodsDetailVo> getBuyGoods() {
        return buyGoods;
    }

    public void setBuyGoods(List<BuyGoodsDetailVo> buyGoods) {
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
    public static void main(String[] args) {
        BuyInfoVo vo =new BuyInfoVo();
        BuyGoodsDetailVo v=new BuyGoodsDetailVo();
        List<BuyGoodsDetailVo> vs=new ArrayList<BuyGoodsDetailVo>();
        vs.add(v);
        vo.setBuyGoods(vs);
        System.out.println(JSON.toJSONString(vo, SerializerFeature.WriteMapNullValue));
    }
}
