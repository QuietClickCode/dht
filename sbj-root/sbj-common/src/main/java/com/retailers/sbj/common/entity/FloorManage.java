package com.retailers.sbj.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 描述：楼栋管理对象
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-15 14:23:58
 */
public class FloorManage implements java.io.Serializable {

	/**fmId*/
	@NotEmpty
	private Long fmId;
	/**楼栋名称*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String fmName;
	/**物业性质*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String fmType;
	/**房屋套数*/
	@NotEmpty
	private Long fmQuantity;
	/**备注*/
	@NotEmpty
	@Length(min = 1, max = 20000)
	private String fmInfo;
	/**是否显示（0代表不显示，1代表显示）默认为1*/
	@NotEmpty
	private Integer isShow;
	/**是否删除（0代表不删除，1代表删除）默认为0*/
	@NotEmpty
	private Integer isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public FloorManage(){
	}

	public FloorManage( Long fmId){
		this.fmId = fmId;
	}

	public void setFmId(Long value) {
		this.fmId = value;
	}

	public Long getFmId() {
		return this.fmId;
	}
	public void setFmName(String value) {
		this.fmName = value;
	}

	public String getFmName() {
		return this.fmName;
	}
	public void setFmType(String value) {
		this.fmType = value;
	}

	public String getFmType() {
		return this.fmType;
	}
	public void setFmQuantity(Long value) {
		this.fmQuantity = value;
	}

	public Long getFmQuantity() {
		return this.fmQuantity;
	}
	public void setFmInfo(String value) {
		this.fmInfo = value;
	}

	public String getFmInfo() {
		return this.fmInfo;
	}
	public void setIsShow(Integer value) {
		this.isShow = value;
	}

	public Integer getIsShow() {
		return this.isShow;
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
