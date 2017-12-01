package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：足迹表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-30 09:50:48
 */
public class UserFootprint implements java.io.Serializable {

	/**足迹id*/
	@NotEmpty
	private Long ufId;
	/**gid*/
	@NotEmpty
	private Long gid;
	/**uid*/
	@NotEmpty
	private Long uid;
	/**创建时间*/
	@NotEmpty
	private Date creatTime;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public UserFootprint(){
	}

	public UserFootprint( Long ufId){
		this.ufId = ufId;
	}

	public void setUfId(Long value) {
		this.ufId = value;
	}

	public Long getUfId() {
		return this.ufId;
	}
	public void setGid(Long value) {
		this.gid = value;
	}

	public Long getGid() {
		return this.gid;
	}
	public void setUid(Long value) {
		this.uid = value;
	}

	public Long getUid() {
		return this.uid;
	}
	public void setCreatTime(Date value) {
		this.creatTime = value;
	}

	public Date getCreatTime() {
		return this.creatTime;
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
