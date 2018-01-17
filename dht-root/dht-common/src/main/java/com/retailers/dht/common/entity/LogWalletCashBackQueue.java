package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：用户返现队列日志对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2018-01-17 20:48:05
 */
public class LogWalletCashBackQueue implements java.io.Serializable {

	/**ccbqId*/
	@NotEmpty
	private Long ccbqId;
	/**日志类型（0 返现日志，1 自然消费日志）*/
	@NotEmpty
	private Integer ccbqType;
	/**订单id*/
	@NotEmpty
	private Long ccbqOrderId;
	/**返现类型id*/
	@NotEmpty
	private Long ccbqOdId;
	/**返现类型*/
	@NotEmpty
	private Long ccbqRtnType;
	/**用户id*/
	@NotEmpty
	private Long ccbqUid;
	/**返现金额（分）*/
	@NotEmpty
	private Long ccbqMoney;
	/**创建时间*/
	@NotEmpty
	private Date ccbqCreateTime;
	//columns END

	public LogWalletCashBackQueue(){
	}

	public LogWalletCashBackQueue( Long ccbqId){
		this.ccbqId = ccbqId;
	}

	public void setCcbqId(Long value) {
		this.ccbqId = value;
	}

	public Long getCcbqId() {
		return this.ccbqId;
	}
	public void setCcbqType(Integer value) {
		this.ccbqType = value;
	}

	public Integer getCcbqType() {
		return this.ccbqType;
	}
	public void setCcbqOrderId(Long value) {
		this.ccbqOrderId = value;
	}

	public Long getCcbqOrderId() {
		return this.ccbqOrderId;
	}
	public void setCcbqOdId(Long value) {
		this.ccbqOdId = value;
	}

	public Long getCcbqOdId() {
		return this.ccbqOdId;
	}

	public void setCcbqUid(Long value) {
		this.ccbqUid = value;
	}

	public Long getCcbqRtnType() {
		return ccbqRtnType;
	}

	public void setCcbqRtnType(Long ccbqRtnType) {
		this.ccbqRtnType = ccbqRtnType;
	}

	public Long getCcbqUid() {
		return this.ccbqUid;
	}
	public void setCcbqMoney(Long value) {
		this.ccbqMoney = value;
	}

	public Long getCcbqMoney() {
		return this.ccbqMoney;
	}
	public void setCcbqCreateTime(Date value) {
		this.ccbqCreateTime = value;
	}

	public Date getCcbqCreateTime() {
		return this.ccbqCreateTime;
	}


}
