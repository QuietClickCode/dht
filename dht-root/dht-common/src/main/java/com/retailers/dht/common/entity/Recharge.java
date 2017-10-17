package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：充值管理对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-15 23:56:18
 */
public class Recharge implements java.io.Serializable {

	/**rid*/
	@NotEmpty
	private Long rid;
	/**等级名称*/
	@NotEmpty
	@Length(min = 0, max = 24)
	private String rname;
	/**会员卡图片*/
	@NotEmpty
	private Long rlogo;
	/**充值金额*/
	@NotEmpty
	private Long rprice;
	/**享受折扣*/
	@NotEmpty
	private Long rdiscount;
	/**是否返现(0 不返现，1 返现）*/
	@NotEmpty
	private Integer rcashback;
	/**创建时间*/
	@NotEmpty
	private Date rcreateDate;
	/**创建用户*/
	@NotEmpty
	private Long rcreateSid;
	/**快照id*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String rsnapshot;
	/**是否有效（0 有效，1 无效）*/
	@NotEmpty
	private Integer isValid;
	/**是否删除（0 未删作，1 删除）*/
	@NotEmpty
	private Integer isDelete;
	/**数据版本*/
	@NotEmpty
	private Integer version;
	//columns END

	public Recharge(){
	}

	public Recharge( Long rid){
		this.rid = rid;
	}

	public void setRid(Long value) {
		this.rid = value;
	}

	public Long getRid() {
		return this.rid;
	}
	public void setRname(String value) {
		this.rname = value;
	}

	public String getRname() {
		return this.rname;
	}
	public void setRlogo(Long value) {
		this.rlogo = value;
	}

	public Long getRlogo() {
		return this.rlogo;
	}
	public void setRprice(Long value) {
		this.rprice = value;
	}

	public Long getRprice() {
		return this.rprice;
	}
	public void setRdiscount(Long value) {
		this.rdiscount = value;
	}

	public Long getRdiscount() {
		return this.rdiscount;
	}
	public void setRcashback(Integer value) {
		this.rcashback = value;
	}

	public Integer getRcashback() {
		return this.rcashback;
	}
	public void setRcreateDate(Date value) {
		this.rcreateDate = value;
	}

	public Date getRcreateDate() {
		return this.rcreateDate;
	}
	public void setRcreateSid(Long value) {
		this.rcreateSid = value;
	}

	public Long getRcreateSid() {
		return this.rcreateSid;
	}
	public void setRsnapshot(String value) {
		this.rsnapshot = value;
	}

	public String getRsnapshot() {
		return this.rsnapshot;
	}
	public void setIsValid(Integer value) {
		this.isValid = value;
	}

	public Integer getIsValid() {
		return this.isValid;
	}
	public void setIsDelete(Integer value) {
		this.isDelete = value;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}
	public void setVersion(Integer value) {
		this.version = value;
	}

	public Integer getVersion() {
		return this.version;
	}


}
