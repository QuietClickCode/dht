package com.retailers.razz.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：职位表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-27 16:06:04
 */
public class PositionManage implements java.io.Serializable {

	/**岗位管理（id）*/
	@NotEmpty
	private Long id;
	/**岗位名称*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String positionName;
	/**是否删除（0 未删除，1 删除）*/
	@NotEmpty
	private Long isDelete;
	/**数据版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public PositionManage(){
	}

	public PositionManage( Long id){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}
	public void setPositionName(String value) {
		this.positionName = value;
	}

	public String getPositionName() {
		return this.positionName;
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
