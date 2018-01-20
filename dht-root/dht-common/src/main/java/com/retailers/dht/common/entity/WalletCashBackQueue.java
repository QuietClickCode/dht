package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：钱包消费返现(包括指定可返现商品类型）对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2018-01-17 21:05:20
 */
public class WalletCashBackQueue implements java.io.Serializable {

	/**ccbqId*/
	@NotEmpty
	private Long ccbqId;
	/**返现类别（0 钱包消费返现，1 自然消费累计20000返现）*/
	@NotEmpty
	private Integer ccbqType;
	/**订单id*/
	@NotEmpty
	private Long ccbqOrderId;
	/**返现类型*/
	@NotEmpty
	private Long ccbqRtnType;
	/**用户id*/
	@NotEmpty
	private Long ccbqUid;
	/**返现金额（分）*/
	@NotEmpty
	private Long ccbqMoney;
	/**
	 * 累计金额
	 */
	private Long ccbqTotalPrice;
	/**当前状态（0 排队中，1 正在筹款，2 己返现）*/
	@NotEmpty
	private Long ccbqStatus;
	/**创建时间*/
	@NotEmpty
	private Date ccbqCreateTime;
	/**返现时间*/
	@NotEmpty
	private Date ccbqCashBackTime;
	/**等待时间*/
	@NotEmpty
	private Long ccbqWaitTime;
	/**数据版本*/
	@NotEmpty
	private Integer version;
	//columns END

	public WalletCashBackQueue(){
	}

	public WalletCashBackQueue( Long ccbqId){
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

	public Long getCcbqTotalPrice() {
		return ccbqTotalPrice;
	}

	public void setCcbqTotalPrice(Long ccbqTotalPrice) {
		this.ccbqTotalPrice = ccbqTotalPrice;
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
	public void setCcbqRtnType(Long value) {
		this.ccbqRtnType = value;
	}

	public Long getCcbqRtnType() {
		return this.ccbqRtnType;
	}
	public void setCcbqUid(Long value) {
		this.ccbqUid = value;
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
	public void setCcbqStatus(Long value) {
		this.ccbqStatus = value;
	}

	public Long getCcbqStatus() {
		return this.ccbqStatus;
	}
	public void setCcbqCreateTime(Date value) {
		this.ccbqCreateTime = value;
	}

	public Date getCcbqCreateTime() {
		return this.ccbqCreateTime;
	}
	public void setCcbqCashBackTime(Date value) {
		this.ccbqCashBackTime = value;
	}

	public Date getCcbqCashBackTime() {
		return this.ccbqCashBackTime;
	}
	public void setCcbqWaitTime(Long value) {
		this.ccbqWaitTime = value;
	}

	public Long getCcbqWaitTime() {
		return this.ccbqWaitTime;
	}
	public void setVersion(Integer value) {
		this.version = value;
	}

	public Integer getVersion() {
		return this.version;
	}


}
