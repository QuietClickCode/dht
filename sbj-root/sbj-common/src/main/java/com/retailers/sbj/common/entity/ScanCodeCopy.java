package com.retailers.sbj.common.entity;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * 描述：扫码副本表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-22 12:00:59
 */
public class ScanCodeCopy implements java.io.Serializable {

	/**sccId*/
	@NotEmpty
	private Long sccId;
	/**扫码员ID*/
	@NotEmpty
	private Long scId;
	/**开盘ID*/
	@NotEmpty
	private Long oid;
	/**员工ID*/
	@NotEmpty
	private Long emId;
	/**是否删除（0代表不删除，1代表删除）默认为0*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	/**操作时间*/
	@NotEmpty
	private Date createTime;
	/**操作人id*/
	@NotEmpty
	private Long uploadperson;
	/**操作类型 0增加 1修改 2删除*/
	@NotEmpty
	private Long uploadtype;
	//columns END

	public ScanCodeCopy(){
	}

	public ScanCodeCopy( Long sccId){
		this.sccId = sccId;
	}

	public void setSccId(Long value) {
		this.sccId = value;
	}

	public Long getSccId() {
		return this.sccId;
	}
	public void setScId(Long value) {
		this.scId = value;
	}

	public Long getScId() {
		return this.scId;
	}
	public void setOid(Long value) {
		this.oid = value;
	}

	public Long getOid() {
		return this.oid;
	}
	public void setEmId(Long value) {
		this.emId = value;
	}

	public Long getEmId() {
		return this.emId;
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
	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}
	public void setUploadperson(Long value) {
		this.uploadperson = value;
	}

	public Long getUploadperson() {
		return this.uploadperson;
	}
	public void setUploadtype(Long value) {
		this.uploadtype = value;
	}

	public Long getUploadtype() {
		return this.uploadtype;
	}


}
