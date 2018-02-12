package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：农夫用户关系表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-02-07 17:16:31
 */
public class FamerUser implements java.io.Serializable {

	/**农户用户关系id*/
	@NotEmpty
	private Long fuId;
	/**农户id*/
	@NotEmpty
	private Long fid;
	/**用户id*/
	@NotEmpty
	private Long uid;
	/**结亲时间*/
	@NotEmpty
	private Date fuTime;
	/**是否删除 0不删除 1珊瑚*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public FamerUser(){
	}

	public Date getFuTime() {
		return fuTime;
	}

	public void setFuTime(Date fuTime) {
		this.fuTime = fuTime;
	}

	public FamerUser(Long fuId){
		this.fuId = fuId;
	}

	public void setFuId(Long value) {
		this.fuId = value;
	}

	public Long getFuId() {
		return this.fuId;
	}
	public void setFid(Long value) {
		this.fid = value;
	}

	public Long getFid() {
		return this.fid;
	}
	public void setUid(Long value) {
		this.uid = value;
	}

	public Long getUid() {
		return this.uid;
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
