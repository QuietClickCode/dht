package com.retailers.dht.common.vo;

import com.retailers.tools.utils.NumberUtils;

import java.util.Date;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2018/1/30
 */
public class OrderRefundVo {
    /**
     * 退款id
     */
    private Long rdId;
    /**
     * 退款订单号
     */
    private String rdOrderNo;
    /**
     * 退款状态
     */
    private Long rdStatus;
    /**
     * 退款金额
     */
    private Long rdPrice;
    /**
     * 退款创建时间
     */
    private Date rdCreateDate;
    /**
     * 退款备注
     */
    private String rdRemark;
    /**
     * 退款审核用户
     */
    private Long rdSuid;
    /**
     * 退款审核用户姓名
     */
    private String uName;
    /**
     * 退款审核时间
     */
    private Date rdAuditingDate;
    /**
     * 退款审核备注
     */
    private String rdAuditingRemark;
    /**
     * 退款下划时间（退款时间）
     */
    private Date rdSendDate;
    /**
     * 退款回调code
     */
    private String rdCallbackNo;
    /**
     * 商品购买用户
     */
    private Long orderBuyUid;
    /**
     * 商品购买人姓名
     */
    private String buyName;
    /**
     * 支付方式
     */
    private Integer orderPayWay;
    /**
     *
     */
    private Integer orderPayUseWay;
    /**
     * 收货人姓名
     */
    private String orderUaName;
    /**
     * 收货人电话
     */
    private String orderUaPhone;
    /**
     * 收货人地址
     */
    private String orderUaAddress;

    public Long getRdId() {
        return rdId;
    }

    public void setRdId(Long rdId) {
        this.rdId = rdId;
    }

    public String getRdOrderNo() {
        return rdOrderNo;
    }

    public void setRdOrderNo(String rdOrderNo) {
        this.rdOrderNo = rdOrderNo;
    }

    public Long getRdStatus() {
        return rdStatus;
    }

    public void setRdStatus(Long rdStatus) {
        this.rdStatus = rdStatus;
    }

    public Long getRdPrice() {
        return rdPrice;
    }
    public String getRdPriceStr() {
        return NumberUtils.formaterNumberPower(rdPrice);
    }
    public void setRdPrice(Long rdPrice) {
        this.rdPrice = rdPrice;
    }

    public Date getRdCreateDate() {
        return rdCreateDate;
    }

    public void setRdCreateDate(Date rdCreateDate) {
        this.rdCreateDate = rdCreateDate;
    }

    public String getRdRemark() {
        return rdRemark;
    }

    public void setRdRemark(String rdRemark) {
        this.rdRemark = rdRemark;
    }

    public Long getRdSuid() {
        return rdSuid;
    }

    public void setRdSuid(Long rdSuid) {
        this.rdSuid = rdSuid;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public Date getRdAuditingDate() {
        return rdAuditingDate;
    }

    public void setRdAuditingDate(Date rdAuditingDate) {
        this.rdAuditingDate = rdAuditingDate;
    }

    public String getRdAuditingRemark() {
        return rdAuditingRemark;
    }

    public void setRdAuditingRemark(String rdAuditingRemark) {
        this.rdAuditingRemark = rdAuditingRemark;
    }

    public Date getRdSendDate() {
        return rdSendDate;
    }

    public void setRdSendDate(Date rdSendDate) {
        this.rdSendDate = rdSendDate;
    }

    public String getRdCallbackNo() {
        return rdCallbackNo;
    }

    public void setRdCallbackNo(String rdCallbackNo) {
        this.rdCallbackNo = rdCallbackNo;
    }

    public Long getOrderBuyUid() {
        return orderBuyUid;
    }

    public void setOrderBuyUid(Long orderBuyUid) {
        this.orderBuyUid = orderBuyUid;
    }

    public String getBuyName() {
        return buyName;
    }

    public void setBuyName(String buyName) {
        this.buyName = buyName;
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


}
