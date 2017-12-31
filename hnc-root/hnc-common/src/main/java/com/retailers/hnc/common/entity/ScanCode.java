package com.retailers.hnc.common.entity;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 描述：扫码人员关系表对象
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-31 12:47:06
 */
public class ScanCode implements java.io.Serializable {

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
	//columns END

	public ScanCode(){
	}

	public ScanCode( Long scId){
		this.scId = scId;
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


}
