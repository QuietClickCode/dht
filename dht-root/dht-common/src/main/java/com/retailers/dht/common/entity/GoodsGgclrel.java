package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品与商品评论关系表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-25 13:55:59
 */
public class GoodsGgclrel implements java.io.Serializable {

	/**商品与评论关系ID*/
	@NotEmpty
	private Long ggclId;
	/**商品ID*/
	@NotEmpty
	private Long gid;
	/**评论标签ID*/
	@NotEmpty
	private Long gclId;
	/**评论等级*/
	@NotEmpty
	private Long gclLevel;
	/**评论数量*/
	@NotEmpty
	private Long gclCount;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsGgclrel(){
	}

	public GoodsGgclrel( Long ggclId){
		this.ggclId = ggclId;
	}

	public void setGgclId(Long value) {
		this.ggclId = value;
	}

	public Long getGgclId() {
		return this.ggclId;
	}
	public void setGid(Long value) {
		this.gid = value;
	}

	public Long getGid() {
		return this.gid;
	}
	public void setGclId(Long value) {
		this.gclId = value;
	}

	public Long getGclId() {
		return this.gclId;
	}
	public void setGclLevel(Long value) {
		this.gclLevel = value;
	}

	public Long getGclLevel() {
		return this.gclLevel;
	}
	public void setGclCount(Long value) {
		this.gclCount = value;
	}

	public Long getGclCount() {
		return this.gclCount;
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
