package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：订单对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-14 00:20:05
 */
public class Order implements java.io.Serializable {

	/**id*/
	@NotEmpty
	private Long id;
	/**交易拆单（多个出售者时，交易进行拆单处理）*/
	@NotEmpty
	private Long parsentId;
	/**订单类型*/
	@NotEmpty
	@Length(min = 1, max = 24)
	private String orderType;
	/**订单号*/
	@NotEmpty
	@Length(min = 1, max = 20)
	private String orderNo;
	/**订单状态(0 未支付，1 支付中，2 支付失败，3 支付成功/待发货，4 己发货，5 确认收货，6 起发退款 ，7订单超时，9 交易完成（收货后15天关闭交易，不能进行退款申请））*/
	@NotEmpty
	private Integer orderStatus;
	/**支付通道(0 微信，1 支付宝，2 用户钱包）*/
	@NotEmpty
	private Integer orderPayWay;
	/**支付方式(1 公众号支付，2 扫描支付，3 h5 支付）*/
	@NotEmpty
	private Integer orderPayUseWay;
	/**第三方支付订单号*/
	@NotEmpty
	@Length(min = 1, max = 128)
	private String orderPayCallbackNo;
	/**订单回调备注*/
	@NotEmpty
	@Length(min = 1, max = 640)
	private String orderPayCallbackRemark;
	/**订单支付回调时间*/
	@NotEmpty
	private Date orderPayCallbackDate;
	/**购买人id*/
	@NotEmpty
	private Long orderBuyUid;
	/**售卖人id*/
	@NotEmpty
	private Long orderSellUid;
	/**交易金额（总金额）*/
	@NotEmpty
	private Long orderTradePrice;
	/**交易商品总金额*/
	@NotEmpty
	private Long orderGoodsTotalPrice;
	/**商品实际支付金额*/
	@NotEmpty
	private Long orderGoodsActualPayPrice;
	/**
	 * 会员价（钱包支付时用户所支付价格)
	 */
	private Long orderMenberPrice;

	/**商品优惠金额（某些商品有商品优惠）*/
	@NotEmpty
	private Long orderGoodsCouponPrice;
	/**优惠卷金额（使用优惠卷金额）*/
	@NotEmpty
	private Long orderCouponPrice;
	/**物流费*/
	@NotEmpty
	private Long orderLogisticsPrice;
	/**订单创建时间*/
	@NotEmpty
	private Date orderCreateDate;
	/**支付时间*/
	@NotEmpty
	private Date orderPayDate;
	/**订单备注*/
	@NotEmpty
	@Length(min = 1, max = 640)
	private String orderRemark;
	/**订单说明（用于内容）*/
	@NotEmpty
	@Length(min = 1, max = 640)
	private String orderIllustrate;
	/**订单分销用户*/
	@NotEmpty
	private Long orderShareUid;
	/**积分或返现（0 消费积分，1 消费返现）*/
	@NotEmpty
	private Integer orderIntegralOrCash;
	/**收货人地址id*/
	@NotEmpty
	private Long orderUaId;
	/**收货人姓名*/
	@NotEmpty
	@Length(min = 1, max = 50)
	private String orderUaName;
	/**收货人电话*/
	@NotEmpty
	@Length(min = 1, max = 20)
	private String orderUaPhone;
	/**收货人地址*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String orderUaAddress;
	/**消费折扣(充值会员享受折后折）*/
	@NotEmpty
	private Long orderDiscount;
	/**
	 * 快递公司编码
	 */
	private String orderLogisticsCompany;
	/**物流单号*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String orderLogisticsCode;
	/**发货时间*/
	@NotEmpty
	private Date orderSendDate;
	/**
	 * 发货人
	 */
	private Long orderSendUid;
	/**发货备注*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String orderSendRemark;
	/**确认收货时间*/
	@NotEmpty
	private Date orderConfirmDate;
	/**发货仓库id*/
	@NotEmpty
	private Long orderSendAddressId;
	/**买家是否删除该订单（0 未删除，1 删除）*/
	@NotEmpty
	private Integer orderBuyDel;
	/**卖家是否删除该订单（0 未删除，1 删除）*/
	@NotEmpty
	private Integer orderSellDel;
	/**是否真实交易订单(0 真实交易订单，1 虚拟交易订单）*/
	@NotEmpty
	private Integer isReal;
	/**数据版本*/
	@NotEmpty
	private Integer version;
	//columns END

	public Order(){
	}

	public Order( Long id){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}
	public void setParsentId(Long value) {
		this.parsentId = value;
	}

	public Long getParsentId() {
		return this.parsentId;
	}
	public void setOrderType(String value) {
		this.orderType = value;
	}

	public String getOrderType() {
		return this.orderType;
	}
	public void setOrderNo(String value) {
		this.orderNo = value;
	}

	public String getOrderNo() {
		return this.orderNo;
	}
	public void setOrderStatus(Integer value) {
		this.orderStatus = value;
	}

	public Integer getOrderStatus() {
		return this.orderStatus;
	}
	public void setOrderPayWay(Integer value) {
		this.orderPayWay = value;
	}

	public Integer getOrderPayWay() {
		return this.orderPayWay;
	}
	public void setOrderPayUseWay(Integer value) {
		this.orderPayUseWay = value;
	}

	public Integer getOrderPayUseWay() {
		return this.orderPayUseWay;
	}
	public void setOrderPayCallbackNo(String value) {
		this.orderPayCallbackNo = value;
	}

	public String getOrderPayCallbackNo() {
		return this.orderPayCallbackNo;
	}
	public void setOrderPayCallbackRemark(String value) {
		this.orderPayCallbackRemark = value;
	}

	public String getOrderPayCallbackRemark() {
		return this.orderPayCallbackRemark;
	}
	public void setOrderPayCallbackDate(Date value) {
		this.orderPayCallbackDate = value;
	}

	public Date getOrderPayCallbackDate() {
		return this.orderPayCallbackDate;
	}
	public void setOrderBuyUid(Long value) {
		this.orderBuyUid = value;
	}

	public Long getOrderBuyUid() {
		return this.orderBuyUid;
	}
	public void setOrderSellUid(Long value) {
		this.orderSellUid = value;
	}

	public Long getOrderSellUid() {
		return this.orderSellUid;
	}
	public void setOrderTradePrice(Long value) {
		this.orderTradePrice = value;
	}

	public Long getOrderTradePrice() {
		return this.orderTradePrice;
	}
	public void setOrderGoodsTotalPrice(Long value) {
		this.orderGoodsTotalPrice = value;
	}

	public Long getOrderGoodsTotalPrice() {
		return this.orderGoodsTotalPrice;
	}
	public Long getOrderMenberPrice() {
		return orderMenberPrice;
	}
	public void setOrderMenberPrice(Long orderMenberPrice) {
		this.orderMenberPrice = orderMenberPrice;
	}
	public void setOrderGoodsActualPayPrice(Long value) {
		this.orderGoodsActualPayPrice = value;
	}

	public Long getOrderGoodsActualPayPrice() {
		return this.orderGoodsActualPayPrice;
	}
	public void setOrderGoodsCouponPrice(Long value) {
		this.orderGoodsCouponPrice = value;
	}

	public Long getOrderGoodsCouponPrice() {
		return this.orderGoodsCouponPrice;
	}
	public void setOrderCouponPrice(Long value) {
		this.orderCouponPrice = value;
	}

	public Long getOrderCouponPrice() {
		return this.orderCouponPrice;
	}
	public void setOrderLogisticsPrice(Long value) {
		this.orderLogisticsPrice = value;
	}

	public Long getOrderLogisticsPrice() {
		return this.orderLogisticsPrice;
	}
	public void setOrderCreateDate(Date value) {
		this.orderCreateDate = value;
	}

	public Date getOrderCreateDate() {
		return this.orderCreateDate;
	}
	public void setOrderPayDate(Date value) {
		this.orderPayDate = value;
	}

	public Date getOrderPayDate() {
		return this.orderPayDate;
	}
	public void setOrderRemark(String value) {
		this.orderRemark = value;
	}

	public String getOrderRemark() {
		return this.orderRemark;
	}
	public void setOrderIllustrate(String value) {
		this.orderIllustrate = value;
	}

	public String getOrderIllustrate() {
		return this.orderIllustrate;
	}
	public void setOrderShareUid(Long value) {
		this.orderShareUid = value;
	}

	public Long getOrderShareUid() {
		return this.orderShareUid;
	}
	public void setOrderIntegralOrCash(Integer value) {
		this.orderIntegralOrCash = value;
	}

	public Integer getOrderIntegralOrCash() {
		return this.orderIntegralOrCash;
	}
	public void setOrderUaId(Long value) {
		this.orderUaId = value;
	}

	public Long getOrderUaId() {
		return this.orderUaId;
	}
	public void setOrderUaName(String value) {
		this.orderUaName = value;
	}

	public String getOrderUaName() {
		return this.orderUaName;
	}
	public void setOrderUaPhone(String value) {
		this.orderUaPhone = value;
	}

	public String getOrderUaPhone() {
		return this.orderUaPhone;
	}
	public void setOrderUaAddress(String value) {
		this.orderUaAddress = value;
	}

	public String getOrderUaAddress() {
		return this.orderUaAddress;
	}
	public void setOrderDiscount(Long value) {
		this.orderDiscount = value;
	}

	public Long getOrderDiscount() {
		return this.orderDiscount;
	}
	public void setOrderLogisticsCode(String value) {
		this.orderLogisticsCode = value;
	}

	public String getOrderLogisticsCode() {
		return this.orderLogisticsCode;
	}
	public void setOrderSendDate(Date value) {
		this.orderSendDate = value;
	}

	public Date getOrderSendDate() {
		return this.orderSendDate;
	}
	public void setOrderSendRemark(String value) {
		this.orderSendRemark = value;
	}

	public String getOrderSendRemark() {
		return this.orderSendRemark;
	}
	public void setOrderConfirmDate(Date value) {
		this.orderConfirmDate = value;
	}

	public Date getOrderConfirmDate() {
		return this.orderConfirmDate;
	}
	public void setOrderSendAddressId(Long value) {
		this.orderSendAddressId = value;
	}

	public Long getOrderSendAddressId() {
		return this.orderSendAddressId;
	}
	public void setOrderBuyDel(Integer value) {
		this.orderBuyDel = value;
	}

	public Integer getOrderBuyDel() {
		return this.orderBuyDel;
	}
	public void setOrderSellDel(Integer value) {
		this.orderSellDel = value;
	}

	public Integer getOrderSellDel() {
		return this.orderSellDel;
	}
	public void setIsReal(Integer value) {
		this.isReal = value;
	}

	public Integer getIsReal() {
		return this.isReal;
	}
	public void setVersion(Integer value) {
		this.version = value;
	}

	public Integer getVersion() {
		return this.version;
	}

	public String getOrderLogisticsCompany() {
		return orderLogisticsCompany;
	}

	public void setOrderLogisticsCompany(String orderLogisticsCompany) {
		this.orderLogisticsCompany = orderLogisticsCompany;
	}

	public Long getOrderSendUid() {
		return orderSendUid;
	}

	public void setOrderSendUid(Long orderSendUid) {
		this.orderSendUid = orderSendUid;
	}
}
