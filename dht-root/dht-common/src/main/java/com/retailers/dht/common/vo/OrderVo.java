package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.OrderDetail;
import com.retailers.tools.utils.NumberUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单展示
 */
public class OrderVo {
    /**id*/
    private Long id;
    /**交易拆单（多个出售者时，交易进行拆单处理）*/
    private Long parsentId;
    /**订单类型*/
    private String orderType;
    /**订单号*/
    private String orderNo;
    /**订单状态(0 未支付，1 支付中，2 支付失败，3 支付成功/待发货，4 己发货，5 确认收货，6 起发退款 ，9 交易完成（收货后15天关闭交易，不能进行退款申请））*/
    private Integer orderStatus;
    /**支付通道(0 微信，1 支付宝，2 用户钱包）*/
    private Integer orderPayWay;
    /**支付方式(1 公众号支付，2 扫描支付，3 h5 支付）*/
    private Integer orderPayUseWay;
    /**第三方支付订单号*/
    private String orderPayCallbackNo;
    /**
     * 订单回调日志
     */
    private String orderPayCallbackRemark;
    /**订单支付回调时间*/
    private Date orderPayCallbackDate;
    /**购买人id*/
    private Long orderBuyUid;
    /**
     * 购买人姓名
     */
    private String orderBuyNm;
    /**交易金额（总金额）*/
    private Long orderTradePrice;
    /**交易商品总金额*/
    private Long orderGoodsTotalPrice;
    /**商品实际支付金额*/
    private Long orderGoodsActualPayPrice;
    /**
     * 会员价（钱包支付时用户所支付价格)
     */
    private Long orderMenberPrice;
    /**商品优惠金额（某些商品有商品优惠）*/
    private Long orderGoodsCouponPrice;
    /**优惠卷金额（使用优惠卷金额）*/
    private Long orderCouponPrice;
    /**物流费*/
    private Long orderLogisticsPrice;
    /**订单创建时间*/
    private Date orderCreateDate;
    /**支付时间*/
    private Date orderPayDate;
    /**积分或返现（0 消费积分，1 消费返现）*/
    private Integer orderIntegralOrCash;
    /**收货人姓名*/
    private String orderUaName;
    /**收货人电话*/
    private String orderUaPhone;
    /**收货人地址*/
    private String orderUaAddress;
    /**消费折扣(充值会员享受折后折）*/
    private Long orderDiscount;
    /**
     * 物流公司编码
     */
    private String orderLogisticsCompany;
    /**物流单号*/
    private String orderLogisticsCode;
    /**
     * 发货仓库
     */
    private Long orderSendAddressId;
    /**发货时间*/
    private Date orderSendDate;
    /**发货备注*/
    private String orderSendRemark;
    /**确认收货时间*/
    private Date orderConfirmDate;
    /**买家是否删除该订单（0 未删除，1 删除）*/
    private Integer orderBuyDel;
    /**卖家是否删除该订单（0 未删除，1 删除）*/
    private Integer orderSellDel;
    /**
     * 订单数据版本
     */
    private Integer version;
    /**
     * 订单详情
     */
    List<OrderDetailVo> ods=new ArrayList<OrderDetailVo>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParsentId() {
        return parsentId;
    }

    public void setParsentId(Long parsentId) {
        this.parsentId = parsentId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderPayWay() {
        return orderPayWay;
    }

    public void setOrderPayWay(Integer orderPayWay) {
        this.orderPayWay = orderPayWay;
    }

    public Integer getOrderPayUseWay() {
        return orderPayUseWay;
    }

    public void setOrderPayUseWay(Integer orderPayUseWay) {
        this.orderPayUseWay = orderPayUseWay;
    }

    public String getOrderPayCallbackNo() {
        return orderPayCallbackNo;
    }

    public void setOrderPayCallbackNo(String orderPayCallbackNo) {
        this.orderPayCallbackNo = orderPayCallbackNo;
    }

    public Date getOrderPayCallbackDate() {
        return orderPayCallbackDate;
    }

    public void setOrderPayCallbackDate(Date orderPayCallbackDate) {
        this.orderPayCallbackDate = orderPayCallbackDate;
    }

    public Long getOrderBuyUid() {
        return orderBuyUid;
    }

    public void setOrderBuyUid(Long orderBuyUid) {
        this.orderBuyUid = orderBuyUid;
    }

    public String getOrderBuyNm() {
        return orderBuyNm;
    }

    public void setOrderBuyNm(String orderBuyNm) {
        this.orderBuyNm = orderBuyNm;
    }

    public String getOrderTradePrice()
    {
        return NumberUtils.formaterNumberPower(orderTradePrice);
    }

    public void setOrderTradePrice(Long orderTradePrice) {
        this.orderTradePrice = orderTradePrice;
    }

    public String getOrderGoodsTotalPrice() {
        return NumberUtils.formaterNumberPower(orderGoodsTotalPrice);
    }

    public void setOrderGoodsTotalPrice(Long orderGoodsTotalPrice) {
        this.orderGoodsTotalPrice = orderGoodsTotalPrice;
    }

    public String getOrderGoodsActualPayPrice()
    {
        return NumberUtils.formaterNumberPower(orderGoodsActualPayPrice);
    }

    public void setOrderGoodsActualPayPrice(Long orderGoodsActualPayPrice) {
        this.orderGoodsActualPayPrice = orderGoodsActualPayPrice;
    }

    public String getOrderMenberPrice() {
        return NumberUtils.formaterNumberPower(orderMenberPrice);
    }

    public void setOrderMenberPrice(Long orderMenberPrice) {
        this.orderMenberPrice = orderMenberPrice;
    }

    public String getOrderGoodsCouponPrice() {
        return NumberUtils.formaterNumberPower(orderGoodsCouponPrice);
    }

    public void setOrderGoodsCouponPrice(Long orderGoodsCouponPrice) {
        this.orderGoodsCouponPrice = orderGoodsCouponPrice;
    }

    public String getOrderCouponPrice() {
        return NumberUtils.formaterNumberPower(orderCouponPrice);
    }

    public void setOrderCouponPrice(Long orderCouponPrice) {
        this.orderCouponPrice = orderCouponPrice;
    }

    public String getOrderLogisticsPrice() {
        return NumberUtils.formaterNumberPower(orderLogisticsPrice);
    }

    public void setOrderLogisticsPrice(Long orderLogisticsPrice) {
        this.orderLogisticsPrice = orderLogisticsPrice;
    }

    public Date getOrderCreateDate() {
        return orderCreateDate;
    }

    public void setOrderCreateDate(Date orderCreateDate) {
        this.orderCreateDate = orderCreateDate;
    }

    public Date getOrderPayDate() {
        return orderPayDate;
    }

    public void setOrderPayDate(Date orderPayDate) {
        this.orderPayDate = orderPayDate;
    }

    public Integer getOrderIntegralOrCash() {
        return orderIntegralOrCash;
    }

    public void setOrderIntegralOrCash(Integer orderIntegralOrCash) {
        this.orderIntegralOrCash = orderIntegralOrCash;
    }

    public String getOrderUaName() {
        return orderUaName;
    }

    public void setOrderUaName(String orderUaName) {
        this.orderUaName = orderUaName;
    }

    public String getOrderUaPhone() {
        return orderUaPhone;
    }

    public void setOrderUaPhone(String orderUaPhone) {
        this.orderUaPhone = orderUaPhone;
    }

    public String getOrderUaAddress() {
        return orderUaAddress;
    }

    public void setOrderUaAddress(String orderUaAddress) {
        this.orderUaAddress = orderUaAddress;
    }

    public Long getOrderDiscount() {
        return orderDiscount;
    }

    public void setOrderDiscount(Long orderDiscount) {
        this.orderDiscount = orderDiscount;
    }

    public String getOrderLogisticsCompany() {
        return orderLogisticsCompany;
    }

    public void setOrderLogisticsCompany(String orderLogisticsCompany) {
        this.orderLogisticsCompany = orderLogisticsCompany;
    }

    public String getOrderLogisticsCode() {
        return orderLogisticsCode;
    }

    public void setOrderLogisticsCode(String orderLogisticsCode) {
        this.orderLogisticsCode = orderLogisticsCode;
    }

    public Date getOrderSendDate() {
        return orderSendDate;
    }

    public void setOrderSendDate(Date orderSendDate) {
        this.orderSendDate = orderSendDate;
    }

    public String getOrderSendRemark() {
        return orderSendRemark;
    }

    public void setOrderSendRemark(String orderSendRemark) {
        this.orderSendRemark = orderSendRemark;
    }

    public Date getOrderConfirmDate() {
        return orderConfirmDate;
    }

    public void setOrderConfirmDate(Date orderConfirmDate) {
        this.orderConfirmDate = orderConfirmDate;
    }

    public Integer getOrderBuyDel() {
        return orderBuyDel;
    }

    public void setOrderBuyDel(Integer orderBuyDel) {
        this.orderBuyDel = orderBuyDel;
    }

    public Integer getOrderSellDel() {
        return orderSellDel;
    }

    public void setOrderSellDel(Integer orderSellDel) {
        this.orderSellDel = orderSellDel;
    }

    public List<OrderDetailVo> getOds() {
        return ods;
    }

    public void setOds(List<OrderDetailVo> ods) {
        this.ods = ods;
    }

    public String getOrderPayCallbackRemark() {
        return orderPayCallbackRemark;
    }

    public void setOrderPayCallbackRemark(String orderPayCallbackRemark) {
        this.orderPayCallbackRemark = orderPayCallbackRemark;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getOrderSendAddressId() {
        return orderSendAddressId;
    }

    public void setOrderSendAddressId(Long orderSendAddressId) {
        this.orderSendAddressId = orderSendAddressId;
    }
}
