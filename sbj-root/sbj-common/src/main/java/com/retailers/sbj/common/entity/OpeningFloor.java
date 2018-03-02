package com.retailers.sbj.common.entity;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 描述：开盘与楼栋关系表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-22 16:49:53
 */
public class OpeningFloor implements java.io.Serializable {

	/**开盘和楼栋关系id*/
	@NotEmpty
	private Long ofId;
	/**开盘id*/
	@NotEmpty
	private Long oid;
	/**楼栋id*/
	@NotEmpty
	private Long fid;
	/**是否删除  0不删除 1删除*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public OpeningFloor(){
	}

	public OpeningFloor( Long ofId){
		this.ofId = ofId;
	}

	public void setOfId(Long value) {
		this.ofId = value;
	}

	public Long getOfId() {
		return this.ofId;
	}
	public void setOid(Long value) {
		this.oid = value;
	}

	public Long getOid() {
		return this.oid;
	}
	public void setFid(Long value) {
		this.fid = value;
	}

	public Long getFid() {
		return this.fid;
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
