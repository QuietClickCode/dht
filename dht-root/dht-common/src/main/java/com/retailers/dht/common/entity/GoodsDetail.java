package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品详情表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-21 10:51:10
 */
public class GoodsDetail implements java.io.Serializable {

	/**商品详情ID*/
	@NotEmpty
	private Long gdId;
	/**商品Id*/
	@NotEmpty
	private Long gid;
	/**该商品该规格的销售价格*/
	@NotEmpty
	private Float gdPrice;
	/**成本价*/
	@NotEmpty
	private Float gdCostprice;
	/**总库存*/
	@NotEmpty
	private Long gdInventory;
	/**剩余库存*/
	@NotEmpty
	private Long gdResidueinventory;
	/**图片ID*/
	@NotEmpty
	private Long gdImgid;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsDetail(){
	}

	public GoodsDetail( Long gdId){
		this.gdId = gdId;
	}

	public void setGdId(Long value) {
		this.gdId = value;
	}

	public Long getGdId() {
		return this.gdId;
	}
	public void setGid(Long value) {
		this.gid = value;
	}

	public Long getGid() {
		return this.gid;
	}
	public void setGdPrice(Float value) {
		this.gdPrice = value;
	}

	public Float getGdPrice() {
		return this.gdPrice;
	}
	public void setGdCostprice(Float value) {
		this.gdCostprice = value;
	}

	public Float getGdCostprice() {
		return this.gdCostprice;
	}
	public void setGdInventory(Long value) {
		this.gdInventory = value;
	}

	public Long getGdInventory() {
		return this.gdInventory;
	}
	public void setGdResidueinventory(Long value) {
		this.gdResidueinventory = value;
	}

	public Long getGdResidueinventory() {
		return this.gdResidueinventory;
	}
	public void setGdImgid(Long value) {
		this.gdImgid = value;
	}

	public Long getGdImgid() {
		return this.gdImgid;
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
