package com.retailers.hnc.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：开盘表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-22 16:36:52
 */
public class Opening implements java.io.Serializable {

	/**开盘id*/
	@NotEmpty
	private Long oid;
	/**开盘名称*/
	@NotEmpty
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
	private String oremark;
	/**是否删除 0不删除 1删除*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public Opening(){
	}

	public Opening( Long oid){
		this.oid = oid;
	}

	public String getOremark() {
		return oremark;
	}

	public void setOremark(String oremark) {
		this.oremark = oremark;
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
