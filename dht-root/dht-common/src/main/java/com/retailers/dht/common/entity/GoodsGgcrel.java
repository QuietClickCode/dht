package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：赠品与商品关系表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-27 17:14:07
 */
public class GoodsGgcrel implements java.io.Serializable {

	/**赠品与商品关系ID*/
	@NotEmpty
	private Long ggcId;
	/**商品ID*/
	@NotEmpty
	private Long gid;
	/**赠品ID*/
	@NotEmpty
	private Long gcId;
	/**子类ID*/
	@NotEmpty
	private Long gclassId;
	/**是否使用*/
	@NotEmpty
	private Long isUse;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsGgcrel(){
	}

	public GoodsGgcrel( Long ggcId){
		this.ggcId = ggcId;
	}

	public void setGgcId(Long value) {
		this.ggcId = value;
	}

	public Long getGgcId() {
		return this.ggcId;
	}
	public void setGid(Long value) {
		this.gid = value;
	}

	public Long getGid() {
		return this.gid;
	}
	public void setGcId(Long value) {
		this.gcId = value;
	}

	public Long getGcId() {
		return this.gcId;
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

	public Long getGclassId() {
		return gclassId;
	}

	public void setGclassId(Long gclassId) {
		this.gclassId = gclassId;
	}

	public Long getIsUse() {
		return isUse;
	}

	public void setIsUse(Long isUse) {
		this.isUse = isUse;
	}
}
