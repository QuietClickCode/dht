package com.retailers.hnc.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：客户通过审核记录表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-04 00:14:34
 */
public class CheckUser implements java.io.Serializable {

	/**入场id*/
	@NotEmpty
	private Long cuId;
	/**开盘id*/
	@NotEmpty
	private Long oid;
	/**客户id*/
	@NotEmpty
	private Long cid;
	/**入场验证码*/
	@NotEmpty
	@Length(min = 1, max = 32)
	private String cuValidateCode;
	/**是否使用 0未使用 1已使用*/
	@NotEmpty
	private Long isUse;
	/**使用时间*/
	@NotEmpty
	private Date useTime;
	/**是否删除 0没删除 1已删除*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public CheckUser(){
	}

	public CheckUser( Long cuId){
		this.cuId = cuId;
	}

	public void setCuId(Long value) {
		this.cuId = value;
	}

	public Long getCuId() {
		return this.cuId;
	}
	public void setOid(Long value) {
		this.oid = value;
	}

	public Long getOid() {
		return this.oid;
	}
	public void setCid(Long value) {
		this.cid = value;
	}

	public Long getCid() {
		return this.cid;
	}
	public void setCuValidateCode(String value) {
		this.cuValidateCode = value;
	}

	public String getCuValidateCode() {
		return this.cuValidateCode;
	}
	public void setIsUse(Long value) {
		this.isUse = value;
	}

	public Long getIsUse() {
		return this.isUse;
	}
	public void setUseTime(Date value) {
		this.useTime = value;
	}

	public Date getUseTime() {
		return this.useTime;
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
