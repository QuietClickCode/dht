package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：钱包消费返现(包括指定可返现商品类型）对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-20 01:43:22
 */
public class WalletCashBackQueue implements java.io.Serializable {

	/**ccbqD*/
	@NotEmpty
	private Long ccbqD;
	/**商品类型（第一级）*/
	@NotEmpty
	private Long ccbqGoodsType;
	/**订单id*/
	@NotEmpty
	private Long ccbqOrderId;
	/**用户id*/
	@NotEmpty
	private Long ccbqUid;
	/**返现金额（分）*/
	@NotEmpty
	private Long ccbqMoney;
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

	public WalletCashBackQueue( Long ccbqD){
		this.ccbqD = ccbqD;
	}

	public void setCcbqD(Long value) {
		this.ccbqD = value;
	}

	public Long getCcbqD() {
		return this.ccbqD;
	}
	public void setCcbqGoodsType(Long value) {
		this.ccbqGoodsType = value;
	}

	public Long getCcbqGoodsType() {
		return this.ccbqGoodsType;
	}
	public void setCcbqOrderId(Long value) {
		this.ccbqOrderId = value;
	}

	public Long getCcbqOrderId() {
		return this.ccbqOrderId;
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
