package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 描述：主页楼层表对象
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-12 14:20:57
 */
public class FloorManage implements java.io.Serializable {

	/**flId*/
	@NotEmpty
	private Long flId;
	/**flName*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String flName;
	/**parentId*/
	@NotEmpty
	private Long parentId;
	/**flOrder*/
	@NotEmpty
	private Long flOrder;
	/**isShow*/
	@NotEmpty
	private Long isShow;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public FloorManage(){
	}

	public FloorManage( Long flId){
		this.flId = flId;
	}

	public void setFlId(Long value) {
		this.flId = value;
	}

	public Long getFlId() {
		return this.flId;
	}
	public void setFlName(String value) {
		this.flName = value;
	}

	public String getFlName() {
		return this.flName;
	}
	public void setParentId(Long value) {
		this.parentId = value;
	}

	public Long getParentId() {
		return this.parentId;
	}
	public void setFlOrder(Long value) {
		this.flOrder = value;
	}

	public Long getFlOrder() {
		return this.flOrder;
	}
	public void setIsShow(Long value) {
		this.isShow = value;
	}

	public Long getIsShow() {
		return this.isShow;
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
