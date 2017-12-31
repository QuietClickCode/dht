package com.retailers.hnc.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：开盘与雇员和客户的关系表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-29 09:01:40
 */
public class OpeningEmpClient implements java.io.Serializable {

	/**oecId*/
	@NotEmpty
	private Long oecId;
	/**开盘id*/
	@NotEmpty
	private Long oid;
	/**员工id*/
	@NotEmpty
	private Long eid;
	/**客户id*/
	@NotEmpty
	private Long cid;
	/**状态 0代分配 1审核中 2已分配 3未通过分配*/
	@NotEmpty
	private Long oecStatus;
	/**审核意见*/
	@NotEmpty
	private String oecMsg;
	/**是否删除 0不删除 1删除*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public OpeningEmpClient(){
	}

	public String getOecMsg() {
		return oecMsg;
	}

	public void setOecMsg(String oecMsg) {
		this.oecMsg = oecMsg;
	}

	public OpeningEmpClient(Long oecId){
		this.oecId = oecId;
	}

	public void setOecId(Long value) {
		this.oecId = value;
	}

	public Long getOecId() {
		return this.oecId;
	}
	public void setOid(Long value) {
		this.oid = value;
	}

	public Long getOid() {
		return this.oid;
	}
	public void setEid(Long value) {
		this.eid = value;
	}

	public Long getEid() {
		return this.eid;
	}
	public void setCid(Long value) {
		this.cid = value;
	}

	public Long getCid() {
		return this.cid;
	}
	public void setOecStatus(Long value) {
		this.oecStatus = value;
	}

	public Long getOecStatus() {
		return this.oecStatus;
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
