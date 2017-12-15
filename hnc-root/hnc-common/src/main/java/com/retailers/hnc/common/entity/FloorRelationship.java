package com.retailers.hnc.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 描述：楼栋关系表对象
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-15 14:22:08
 */
public class FloorRelationship implements java.io.Serializable {

	/**楼栋关联表ID*/
	@NotEmpty
	private Long frId;
	/**楼栋id*/
	@NotEmpty
	private Long fmId;
	/**户型id*/
	@NotEmpty
	private Long ftId;
	/**户型名称*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String ftName;
	/**是否删除（0代表不删除，1代表删除）默认为0*/
	@NotEmpty
	private Integer isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public FloorRelationship(){
	}

	public FloorRelationship( Long frId){
		this.frId = frId;
	}

	public void setFrId(Long value) {
		this.frId = value;
	}

	public Long getFrId() {
		return this.frId;
	}
	public void setFmId(Long value) {
		this.fmId = value;
	}

	public Long getFmId() {
		return this.fmId;
	}
	public void setFtId(Long value) {
		this.ftId = value;
	}

	public Long getFtId() {
		return this.ftId;
	}
	public void setFtName(String value) {
		this.ftName = value;
	}

	public String getFtName() {
		return this.ftName;
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
