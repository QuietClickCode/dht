package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：订单详情对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-11 23:38:12
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
	/**购买商品数量*/
	@NotEmpty
	private Integer odBuyNumber;
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


}
