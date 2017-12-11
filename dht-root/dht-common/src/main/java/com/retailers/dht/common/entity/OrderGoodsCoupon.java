package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：订单商品优惠关联对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-11 23:32:56
 */
public class OrderGoodsCoupon implements java.io.Serializable {

	/**ocId*/
	@NotEmpty
	private Long ocId;
	/**订单id*/
	@NotEmpty
	private Long ocOrderId;
	/**商品id*/
	@NotEmpty
	private Long ocGoodsId;
	/**商品优惠id*/
	@NotEmpty
	private Long ocGcId;
	//columns END

	public OrderGoodsCoupon(){
	}

	public OrderGoodsCoupon( Long ocId){
		this.ocId = ocId;
	}

	public void setOcId(Long value) {
		this.ocId = value;
	}

	public Long getOcId() {
		return this.ocId;
	}
	public void setOcOrderId(Long value) {
		this.ocOrderId = value;
	}

	public Long getOcOrderId() {
		return this.ocOrderId;
	}
	public void setOcGoodsId(Long value) {
		this.ocGoodsId = value;
	}

	public Long getOcGoodsId() {
		return this.ocGoodsId;
	}
	public void setOcGcId(Long value) {
		this.ocGcId = value;
	}

	public Long getOcGcId() {
		return this.ocGcId;
	}


}
