package com.retailers.sbj.common.entity;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 描述：客户户型关系表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-31 22:12:55
 */
public class UserLikeHourse implements java.io.Serializable {

	/**用户是否喜欢户型id*/
	@NotEmpty
	private Long ulhId;
	/**客户id*/
	@NotEmpty
	private Long uid;
	/**户型id*/
	@NotEmpty
	private Long hid;
	/**是否喜欢 0不喜欢 1喜欢*/
	@NotEmpty
	private Long isLike;
	/**是否删除 0不删除 1删除*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public UserLikeHourse(){
	}

	public UserLikeHourse( Long ulhId){
		this.ulhId = ulhId;
	}

	public void setUlhId(Long value) {
		this.ulhId = value;
	}

	public Long getUlhId() {
		return this.ulhId;
	}
	public void setUid(Long value) {
		this.uid = value;
	}

	public Long getUid() {
		return this.uid;
	}
	public void setHid(Long value) {
		this.hid = value;
	}

	public Long getHid() {
		return this.hid;
	}
	public void setIsLike(Long value) {
		this.isLike = value;
	}

	public Long getIsLike() {
		return this.isLike;
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
