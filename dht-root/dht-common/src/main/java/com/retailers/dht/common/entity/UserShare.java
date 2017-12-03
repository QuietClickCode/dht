package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：分享记录表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-01 11:20:50
 */
public class UserShare implements java.io.Serializable {

	/**用户分享记录id*/
	@NotEmpty
	private Long usId;
	/**用户id*/
	@NotEmpty
	private Long uid;
	/**分享渠道*/
	@NotEmpty
	@Length(min = 1, max = 100)
	private String usWhere;
	/**创建时间*/
	@NotEmpty
	private Date createTime;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public UserShare(){
	}

	public UserShare( Long usId){
		this.usId = usId;
	}

	public void setUsId(Long value) {
		this.usId = value;
	}

	public Long getUsId() {
		return this.usId;
	}
	public void setUid(Long value) {
		this.uid = value;
	}

	public Long getUid() {
		return this.uid;
	}
	public void setUsWhere(String value) {
		this.usWhere = value;
	}

	public String getUsWhere() {
		return this.usWhere;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
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
