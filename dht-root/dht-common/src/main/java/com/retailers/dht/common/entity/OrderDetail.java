package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：订单详情对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-13 00:13:42
 */
public class OrderDetail implements java.io.Serializable {

	/**id*/
	@NotEmpty
	private Long id;
	/**订单id*/
	@NotEmpty
	private Long odOrderId;
	/**订单商品id*/
	@NotEmpty
	private Long odGoodsId;
	/**商品价格*/
	@NotEmpty
	private Long odGoodsPrice;
	/**实际支付金额*/
	@NotEmpty
	private Long odActualPrice;
	/**
	 * 会员价（用钱包购买时价格）
	 */
	private Long odMenberPrice;
	/**购买商品数量*/
	@NotEmpty
	private Integer odBuyNumber;


	/**商品详情表（关联了价格和库存，与t_goods_ggsval_detail 关联，能找到对应的商品规格）*/
	@NotEmpty
	private Long odGdId;
	/**
	 * 会员购买是时否打折   0代表会员不折扣 1代表会员折扣
	 */
	private Long odIsDiscount;
	//购买推荐人
	private Long odInviterUid;
	/**是否退款(0 正常，1 申请退款，2 退款驳回，3退款成功）*/
	@NotEmpty
	private Integer odIsRefund;
	/**顾客备注*/
	@NotEmpty
	@Length(min = 1, max = 500)
	private String remark;
	//columns END
	public OrderDetail(){
	}
	public OrderDetail( Long id){
		this.id = id;
	}
	public void setId(Long value) {
		this.id = value;
	}
	public Long getId() {
		return this.id;
	}
	public void setOdOrderId(Long value) {
		this.odOrderId = value;
	}
	public Long getOdOrderId() {
		return this.odOrderId;
	}
	public void setOdGoodsId(Long value) {
		this.odGoodsId = value;
	}
	public Long getOdGoodsId() {
		return this.odGoodsId;
	}
	public void setOdGoodsPrice(Long value) {
		this.odGoodsPrice = value;
	}
	public Long getOdGoodsPrice() {
		return this.odGoodsPrice;
	}
	public Long getOdMenberPrice() {
		return odMenberPrice;
	}
	public void setOdMenberPrice(Long odMenberPrice) {
		this.odMenberPrice = odMenberPrice;
	}
	public void setOdActualPrice(Long value) {
		this.odActualPrice = value;
	}
	public Long getOdActualPrice() {
		return this.odActualPrice;
	}
	public void setOdBuyNumber(Integer value) {
		this.odBuyNumber = value;
	}
	public Integer getOdBuyNumber() {
		return this.odBuyNumber;
	}
	public void setOdGdId(Long value) {
		this.odGdId = value;
	}
	public Long getOdGdId() {
		return this.odGdId;
	}
	public Long getOdInviterUid() {
		return odInviterUid;
	}
	public void setOdInviterUid(Long odInviterUid) {
		this.odInviterUid = odInviterUid;
	}
	public void setOdIsRefund(Integer value) {
		this.odIsRefund = value;
	}
	public Integer getOdIsRefund() {
		return this.odIsRefund;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	public String getRemark() {
		return this.remark;
	}

	public Long getOdIsDiscount() {
		return odIsDiscount;
	}

	public void setOdIsDiscount(Long odIsDiscount) {
		this.odIsDiscount = odIsDiscount;
	}
}
