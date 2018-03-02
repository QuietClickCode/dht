package com.retailers.sbj.common.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * 描述：开盘副本表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-22 12:01:16
 */
public class OpeningCopy implements java.io.Serializable {

	/**副本id*/
	@NotEmpty
	private Long ocId;
	/**开盘id*/
	@NotEmpty
	private Long oid;
	/**开盘名称*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String oname;
	/**开盘起始日期*/
	@NotEmpty
	private Date ostartTime;
	/**开盘结束日期*/
	@NotEmpty
	private Date oendTime;
	/**开盘户数*/
	@NotEmpty
	private Long onum;
	/**预约客户总数*/
	@NotEmpty
	private Long omenberNum;
	/**备注*/
	@NotEmpty
	@Length(min = 1, max = 2000)
	private String oremark;
	/**是否删除 0不删除 1删除*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	/**操作时间*/
	@NotEmpty
	private Date createTime;
	/**上传者id*/
	@NotEmpty
	private Long uploadperson;
	/**操作类型 0增加 1修改 2删除*/
	@NotEmpty
	private Long uploadtype;
	//columns END

	public OpeningCopy(){
	}

	public OpeningCopy( Long ocId){
		this.ocId = ocId;
	}

	public void setOcId(Long value) {
		this.ocId = value;
	}

	public Long getOcId() {
		return this.ocId;
	}
	public void setOid(Long value) {
		this.oid = value;
	}

	public Long getOid() {
		return this.oid;
	}
	public void setOname(String value) {
		this.oname = value;
	}

	public String getOname() {
		return this.oname;
	}
	public void setOstartTime(Date value) {
		this.ostartTime = value;
	}

	public Date getOstartTime() {
		return this.ostartTime;
	}
	public void setOendTime(Date value) {
		this.oendTime = value;
	}

	public Date getOendTime() {
		return this.oendTime;
	}
	public void setOnum(Long value) {
		this.onum = value;
	}

	public Long getOnum() {
		return this.onum;
	}
	public void setOmenberNum(Long value) {
		this.omenberNum = value;
	}

	public Long getOmenberNum() {
		return this.omenberNum;
	}
	public void setOremark(String value) {
		this.oremark = value;
	}

	public String getOremark() {
		return this.oremark;
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
