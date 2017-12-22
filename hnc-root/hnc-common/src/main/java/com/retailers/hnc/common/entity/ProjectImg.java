package com.retailers.hnc.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：项目图片表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-22 09:46:01
 */
public class ProjectImg implements java.io.Serializable {

	/**项目图片id*/
	@NotEmpty
	private Long piId;
	/**项目id*/
	@NotEmpty
	private Long pid;
	/**图片id*/
	@NotEmpty
	private Long aid;
	/**是否删除 0不删除 1删除*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public ProjectImg(){
	}

	public ProjectImg( Long piId){
		this.piId = piId;
	}

	public void setPiId(Long value) {
		this.piId = value;
	}

	public Long getPiId() {
		return this.piId;
	}
	public void setPid(Long value) {
		this.pid = value;
	}

	public Long getPid() {
		return this.pid;
	}
	public void setAid(Long value) {
		this.aid = value;
	}

	public Long getAid() {
		return this.aid;
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
