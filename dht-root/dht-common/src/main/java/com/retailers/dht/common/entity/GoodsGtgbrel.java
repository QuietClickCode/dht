package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：大类与规格关系表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-18 17:46:58
 */
public class GoodsGtgbrel implements java.io.Serializable {

	/**大类与品牌关系ID*/
	@NotEmpty
	private Long gtgbId;
	/**大类ID*/
	@NotEmpty
	private Long gtId;
	/**品牌ID*/
	@NotEmpty
	private Long gbId;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsGtgbrel(){
	}

	public GoodsGtgbrel( Long gtgbId){
		this.gtgbId = gtgbId;
	}

	public void setGtgbId(Long value) {
		this.gtgbId = value;
	}

	public Long getGtgbId() {
		return this.gtgbId;
	}
	public void setGtId(Long value) {
		this.gtId = value;
	}

	public Long getGtId() {
		return this.gtId;
	}
	public void setGbId(Long value) {
		this.gbId = value;
	}

	public Long getGbId() {
		return this.gbId;
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
