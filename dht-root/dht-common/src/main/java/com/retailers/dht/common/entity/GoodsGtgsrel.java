package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：大类与规格关系表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-19 11:57:43
 */
public class GoodsGtgsrel implements java.io.Serializable {

	/**商品大类与商品规格关系ID*/
	@NotEmpty
	private Long gtgsId;
	/**商品大类ID*/
	@NotEmpty
	private Long gtId;
	/**商品规格ID*/
	@NotEmpty
	private Long gsId;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsGtgsrel(){
	}

	public GoodsGtgsrel( Long gtgsId){
		this.gtgsId = gtgsId;
	}

	public void setGtgsId(Long value) {
		this.gtgsId = value;
	}

	public Long getGtgsId() {
		return this.gtgsId;
	}
	public void setGtId(Long value) {
		this.gtId = value;
	}

	public Long getGtId() {
		return this.gtId;
	}
	public void setGsId(Long value) {
		this.gsId = value;
	}

	public Long getGsId() {
		return this.gsId;
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
