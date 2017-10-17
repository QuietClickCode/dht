package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 描述：主页导航表对象
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-17 11:56:31
 */
public class FloorManage implements java.io.Serializable {

	/**楼层ID*/
	@NotEmpty
	private Long flId;
	/**楼层名称*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String flName;
	/**父类ID*/
	@NotEmpty
	private Long parentId;
	/**排序*/
	@NotEmpty
	private Long flOrder;
	/**是否显示（0代表不显示，1代表显示）默认为1*/
	@NotEmpty
	private Long isShow;
	/**是否删除（0代表不删除，1代表删除）默认为0*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
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
