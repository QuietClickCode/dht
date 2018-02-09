package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：用户卡包操作日志（钱包，积分）对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-22 23:16:42
 */
public class LogUserCardPackage implements java.io.Serializable {

	/**id*/
	@NotEmpty
	private Long id;
	/**用户id*/
	@NotEmpty
	private Long uid;
	/**操作类型（0 钱包入帐，1 钱包出帐，2 积分入帐，3积分出帐,4消费返现,5 提现,6退款,7 退消费累计）*/
	@NotEmpty
	private Integer type;
	/**关联订单id*/
	@NotEmpty
	private Long relationOrderId;
	/**值*/
	@NotEmpty
	private Long val;
	/**当前值*/
	@NotEmpty
	private Long curVal;
	/**备注*/
	@NotEmpty
	@Length(min = 1, max = 500)
	private String remark;
	/**创建时间*/
	@NotEmpty
	private Date createTime;
	//columns END

	public LogUserCardPackage(){
	}

	public LogUserCardPackage( Long id){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}
	public void setUid(Long value) {
		this.uid = value;
	}

	public Long getUid() {
		return this.uid;
	}
	public void setType(Integer value) {
		this.type = value;
	}

	public Integer getType() {
		return this.type;
	}
	public void setRelationOrderId(Long value) {
		this.relationOrderId = value;
	}

	public Long getRelationOrderId() {
		return this.relationOrderId;
	}
	public void setVal(Long value) {
		this.val = value;
	}

	public Long getVal() {
		return this.val;
	}
	public void setCurVal(Long value) {
		this.curVal = value;
	}

	public Long getCurVal() {
		return this.curVal;
	}
	public void setRemark(String value) {
		this.remark = value;
	}

	public String getRemark() {
		return this.remark;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}


}
