package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：第三方消费返现对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-20 01:46:01
 */
public class ConsumeCashBackQueue implements java.io.Serializable {

	/**icbqId*/
	@NotEmpty
	private Long icbqId;
	/**返现用户*/
	@NotEmpty
	private Long icbqUid;
	/**返现金额*/
	@NotEmpty
	private Long icbqMoney;
	/**消耗积分*/
	@NotEmpty
	private Long icbqUseIntegral;
	/**创建时间*/
	@NotEmpty
	private Date icbqCreateTime;
	/**返现时间*/
	@NotEmpty
	private Date icbqCashBackTime;
	/**等待时间*/
	@NotEmpty
	private Long icbqWaitTime;
	/**version*/
	@NotEmpty
	private Integer version;
	//columns END

	public ConsumeCashBackQueue(){
	}

	public ConsumeCashBackQueue( Long icbqId){
		this.icbqId = icbqId;
	}

	public void setIcbqId(Long value) {
		this.icbqId = value;
	}

	public Long getIcbqId() {
		return this.icbqId;
	}
	public void setIcbqUid(Long value) {
		this.icbqUid = value;
	}

	public Long getIcbqUid() {
		return this.icbqUid;
	}
	public void setIcbqMoney(Long value) {
		this.icbqMoney = value;
	}

	public Long getIcbqMoney() {
		return this.icbqMoney;
	}
	public void setIcbqUseIntegral(Long value) {
		this.icbqUseIntegral = value;
	}

	public Long getIcbqUseIntegral() {
		return this.icbqUseIntegral;
	}
	public void setIcbqCreateTime(Date value) {
		this.icbqCreateTime = value;
	}

	public Date getIcbqCreateTime() {
		return this.icbqCreateTime;
	}
	public void setIcbqCashBackTime(Date value) {
		this.icbqCashBackTime = value;
	}

	public Date getIcbqCashBackTime() {
		return this.icbqCashBackTime;
	}
	public void setIcbqWaitTime(Long value) {
		this.icbqWaitTime = value;
	}

	public Long getIcbqWaitTime() {
		return this.icbqWaitTime;
	}
	public void setVersion(Integer value) {
		this.version = value;
	}

	public Integer getVersion() {
		return this.version;
	}


}
