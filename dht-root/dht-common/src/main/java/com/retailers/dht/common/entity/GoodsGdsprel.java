package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品详情与特教秒杀关系表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-13 11:09:40
 */
public class GoodsGdsprel implements java.io.Serializable {

	/**商品详情与特价关系Id*/
	@NotEmpty
	private Long gdspId;
	/**商品详情Id*/
	@NotEmpty
	private Long gdId;
	/**特价ID*/
	@NotEmpty
	private Long spId;
	/**特价*/
	@NotEmpty
	private Long spSale;
	/**活动最多库存*/
	@NotEmpty
	private Long spInventory;
	/**折扣率*/
	@NotEmpty
	private Long spDiscountRate;
	/**限购量*/
	@NotEmpty
	private Long spBounds;
	/**是否删除（0代表未删除，1代表已删除）*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsGdsprel(){
	}

	public GoodsGdsprel( Long gdspId){
		this.gdspId = gdspId;
	}

	public void setGdspId(Long value) {
		this.gdspId = value;
	}

	public Long getGdspId() {
		return this.gdspId;
	}
	public void setGdId(Long value) {
		this.gdId = value;
	}

	public Long getGdId() {
		return this.gdId;
	}
	public void setSpId(Long value) {
		this.spId = value;
	}

	public Long getSpId() {
		return this.spId;
	}
	public void setSpSale(Long value) {
		this.spSale = value;
	}

	public Long getSpSale() {
		return this.spSale;
	}
	public void setSpInventory(Long value) {
		this.spInventory = value;
	}

	public Long getSpInventory() {
		return this.spInventory;
	}
	public void setSpDiscountRate(Long value) {
		this.spDiscountRate = value;
	}

	public Long getSpDiscountRate() {
		return this.spDiscountRate;
	}
	public void setSpBounds(Long value) {
		this.spBounds = value;
	}

	public Long getSpBounds() {
		return this.spBounds;
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
