package com.retailers.hnc.common.entity;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 描述：楼栋关系表对象
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-18 11:07:35
 */
public class FloorRelationship implements java.io.Serializable {

	/**楼栋关联表ID*/
	@NotEmpty
	private Long flId;
	/**楼栋ID*/
	@NotEmpty
	private Long fmId;
	/**与楼栋所关联的户型ID*/
	@NotEmpty
	private Long frId;
	/**户型ID*/
	@NotEmpty
	private Long htId;
	/**与户型所关联的楼栋ID*/
	@NotEmpty
	private Long hrId;
	/**是否删除（0代表不删除，1代表删除）默认为0*/
	@NotEmpty
	private Integer isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public FloorRelationship(){
	}

	public FloorRelationship( Long flId){
		this.flId = flId;
	}

	public void setFlId(Long value) {
		this.flId = value;
	}

	public Long getFlId() {
		return this.flId;
	}
	public void setFmId(Long value) {
		this.fmId = value;
	}

	public Long getFmId() {
		return this.fmId;
	}
	public void setFrId(Long value) {
		this.frId = value;
	}

	public Long getFrId() {
		return this.frId;
	}
	public void setHtId(Long value) {
		this.htId = value;
	}

	public Long getHtId() {
		return this.htId;
	}
	public void setHrId(Long value) {
		this.hrId = value;
	}

	public Long getHrId() {
		return this.hrId;
	}
	public void setIsDelete(Integer value) {
		this.isDelete = value;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}
	public void setVersion(Long value) {
		this.version = value;
	}

	public Long getVersion() {
		return this.version;
	}


}
