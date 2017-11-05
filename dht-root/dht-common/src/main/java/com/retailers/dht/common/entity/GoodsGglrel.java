package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品与标签关系表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-24 14:28:44
 */
public class GoodsGglrel implements java.io.Serializable {

	/**商品与品牌关系ID*/
	@NotEmpty
	private Long gglId;
	/**商品ID*/
	@NotEmpty
	private Long gid;
	/*子类ID*/
	@NotEmpty
	private Long gclassId;
	/**标签ID*/
	@NotEmpty
	private Long glId;
	/**是否使用 0不使用 1使用*/
	@NotEmpty
	private Long isUse;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsGglrel(){
	}

	public GoodsGglrel( Long gglId){
		this.gglId = gglId;
	}

	public void setGglId(Long value) {
		this.gglId = value;
	}

	public Long getGglId() {
		return this.gglId;
	}
	public void setGid(Long value) {
		this.gid = value;
	}

	public Long getGid() {
		return this.gid;
	}
	public void setGlId(Long value) {
		this.glId = value;
	}

	public Long getGlId() {
		return this.glId;
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
