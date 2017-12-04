package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：受邀记录表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-02 14:10:15
 */
public class UserShared implements java.io.Serializable {

	/**受邀记录id*/
	@NotEmpty
	private Long usdId;
	/**分享id*/
	@NotEmpty
	private Long usUid;
	/**受邀人*/
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

	public UserShared(){
	}

	public UserShared( Long usdId){
		this.usdId = usdId;
	}

	public void setUsdId(Long value) {
		this.usdId = value;
	}

	public Long getUsdId() {
		return this.usdId;
	}
	public void setUsUid(Long value) {
		this.usUid = value;
	}

	public Long getUsUid() {
		return this.usUid;
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
