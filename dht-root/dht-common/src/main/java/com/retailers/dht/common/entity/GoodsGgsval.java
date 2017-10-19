package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品与规格值关系表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-19 18:43:18
 */
public class GoodsGgsval implements java.io.Serializable {

	/**商品规格值ID*/
	@NotEmpty
	private Long ggsId;
	/**商品ID*/
	@NotEmpty
	private Long gid;
	/**规格ID*/
	@NotEmpty
	private Long gsId;
	/**商品规格值*/
	@NotEmpty
	private Long gsvId;
	/**该商品该规格的销售价格*/
	@NotEmpty
	private Float ggsPrice;
	/**成本价*/
	@NotEmpty
	private Float ggsCostprice;
	/**库存*/
	@NotEmpty
	private Long ggsInventory;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsGgsval(){
	}

	public GoodsGgsval( Long ggsId){
		this.ggsId = ggsId;
	}

	public void setGgsId(Long value) {
		this.ggsId = value;
	}

	public Long getGgsId() {
		return this.ggsId;
	}
	public void setGid(Long value) {
		this.gid = value;
	}

	public Long getGid() {
		return this.gid;
	}
	public void setGsId(Long value) {
		this.gsId = value;
	}

	public Long getGsId() {
		return this.gsId;
	}
	public void setGsvId(Long value) {
		this.gsvId = value;
	}

	public Long getGsvId() {
		return this.gsvId;
	}
	public void setGgsPrice(Float value) {
		this.ggsPrice = value;
	}

	public Float getGgsPrice() {
		return this.ggsPrice;
	}
	public void setGgsCostprice(Float value) {
		this.ggsCostprice = value;
	}

	public Float getGgsCostprice() {
		return this.ggsCostprice;
	}
	public void setGgsInventory(Long value) {
		this.ggsInventory = value;
	}

	public Long getGgsInventory() {
		return this.ggsInventory;
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
