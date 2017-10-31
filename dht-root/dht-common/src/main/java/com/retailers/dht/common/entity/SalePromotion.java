package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品促销表对象
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-30 15:28:44
 */
public class SalePromotion implements java.io.Serializable {

	/**spId*/
	@NotEmpty
	private Long spId;
	/**商品ID*/
	@NotEmpty
	private Long goodsId;
	/**商品名称*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String goodsName;
	/**原价*/
	@NotEmpty
	private Long spPrice;
	/**特价*/
	@NotEmpty
	private Long spSale;
	/**促销类型（0代表特价，1代表秒杀）*/
	@NotEmpty
	private Long spType;
	/**排序*/
	@NotEmpty
	private Long spOrder;
	/**是否使用优惠券（1代表允许使用优惠券，0代表不允许使用优惠券）*/
	@NotEmpty
	private Long isCoupon;
	/**推送地区（0代表乡村，1代表城市）*/
	@NotEmpty
	private Long spRegion;
	/**库存量*/
	@NotEmpty
	private Long spInventory;
	/**折扣率*/
	@NotEmpty
	private Double spDiscountRate;
	/**已售数量*/
	@NotEmpty
	private Long spSoldOut;
	/**特价数量*/
	@NotEmpty
	private Long spSaleSize;
	/**限购量*/
	@NotEmpty
	private Long spBounds;
	/**开始时间*/
	@NotEmpty
	private Date spStartTime;
	/**结束时间*/
	@NotEmpty
	private Date spEndTime;
	/**是否删除（1删除，0不删除）*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public SalePromotion(){
	}

	public SalePromotion( Long spId){
		this.spId = spId;
	}

	public void setSpId(Long value) {
		this.spId = value;
	}

	public Long getSpId() {
		return this.spId;
	}
	public void setGoodsId(Long value) {
		this.goodsId = value;
	}

	public Long getGoodsId() {
		return this.goodsId;
	}
	public void setGoodsName(String value) {
		this.goodsName = value;
	}

	public String getGoodsName() {
		return this.goodsName;
	}
	public void setSpPrice(Long value) {
		this.spPrice = value;
	}

	public Long getSpPrice() {
		return this.spPrice;
	}
	public void setSpSale(Long value) {
		this.spSale = value;
	}

	public Long getSpSale() {
		return this.spSale;
	}
	public void setSpType(Long value) {
		this.spType = value;
	}

	public Long getSpType() {
		return this.spType;
	}
	public void setSpOrder(Long value) {
		this.spOrder = value;
	}

	public Long getSpOrder() {
		return this.spOrder;
	}
	public void setIsCoupon(Long value) {
		this.isCoupon = value;
	}

	public Long getIsCoupon() {
		return this.isCoupon;
	}
	public void setSpRegion(Long value) {
		this.spRegion = value;
	}

	public Long getSpRegion() {
		return this.spRegion;
	}
	public void setSpInventory(Long value) {
		this.spInventory = value;
	}

	public Long getSpInventory() {
		return this.spInventory;
	}
	public void setSpDiscountRate(Double value) {
		this.spDiscountRate = value;
	}

	public Double getSpDiscountRate() {
		return this.spDiscountRate;
	}
	public void setSpSoldOut(Long value) {
		this.spSoldOut = value;
	}

	public Long getSpSoldOut() {
		return this.spSoldOut;
	}
	public void setSpSaleSize(Long value) {
		this.spSaleSize = value;
	}

	public Long getSpSaleSize() {
		return this.spSaleSize;
	}
	public void setSpBounds(Long value) {
		this.spBounds = value;
	}

	public Long getSpBounds() {
		return this.spBounds;
	}
	public void setSpStartTime(Date value) {
		this.spStartTime = value;
	}

	public Date getSpStartTime() {
		return this.spStartTime;
	}
	public void setSpEndTime(Date value) {
		this.spEndTime = value;
	}

	public Date getSpEndTime() {
		return this.spEndTime;
	}
	public void setIsDelete(Long value) {
		this.isDelete = value;
	}

	public Long getIsDelete() {
		return this.isDelete;
	}
	public void setVersion(Long value) {
		this.version = value;
	}

	public Long getVersion() {
		return this.version;
	}


}
