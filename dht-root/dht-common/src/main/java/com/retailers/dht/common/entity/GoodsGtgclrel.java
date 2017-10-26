package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：大类与商品评论关系表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-25 10:54:33
 */
public class GoodsGtgclrel implements java.io.Serializable {

	/**商品大类与商品评论关系ID*/
	@NotEmpty
	private Long gtgclId;
	/**商品大类ID*/
	@NotEmpty
	private Long gtId;
	/**商品评论ID*/
	@NotEmpty
	private Long gclId;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsGtgclrel(){
	}

	public GoodsGtgclrel( Long gtgclId){
		this.gtgclId = gtgclId;
	}

	public void setGtgclId(Long value) {
		this.gtgclId = value;
	}

	public Long getGtgclId() {
		return this.gtgclId;
	}
	public void setGtId(Long value) {
		this.gtId = value;
	}

	public Long getGtId() {
		return this.gtId;
	}
	public void setGclId(Long value) {
		this.gclId = value;
	}

	public Long getGclId() {
		return this.gclId;
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
