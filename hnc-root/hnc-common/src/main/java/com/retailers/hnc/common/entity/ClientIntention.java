package com.retailers.hnc.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：客户意向表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-29 17:32:34
 */
public class ClientIntention implements java.io.Serializable {

	/**用户意向id*/
	@NotEmpty
	private Long iid;
	/**客户id*/
	@NotEmpty
	private Long cmId;
	/**楼栋id 多个用逗号隔开*/
	@NotEmpty
	@Length(min = 1, max = 999)
	private String fids;
	/**户型id 多个用逗号隔开*/
	@NotEmpty
	@Length(min = 1, max = 999)
	private String hids;
	/**个性需求*/
	@NotEmpty
	@Length(min = 1, max = 999)
	private String iremark;
	/**是否删除 0不删除 1删除*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public ClientIntention(){
	}

	public ClientIntention( Long iid){
		this.iid = iid;
	}

	public void setIid(Long value) {
		this.iid = value;
	}

	public Long getIid() {
		return this.iid;
	}
	public void setCmId(Long value) {
		this.cmId = value;
	}

	public Long getCmId() {
		return this.cmId;
	}
	public void setFids(String value) {
		this.fids = value;
	}

	public String getFids() {
		return this.fids;
	}
	public void setHids(String value) {
		this.hids = value;
	}

	public String getHids() {
		return this.hids;
	}
	public void setIremark(String value) {
		this.iremark = value;
	}

	public String getIremark() {
		return this.iremark;
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
