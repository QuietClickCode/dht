package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：用户提现订单对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2018-02-27 23:04:40
 */
public class CashOrder implements java.io.Serializable {

	/**coId*/
	@NotEmpty
	private Long coId;
	/**提现订单号*/
	@NotEmpty
	@Length(min = 1, max = 32)
	private String coNo;
	/**提现状态（0 待审核，1 拒绝，2 待下划，3 提现成功，4 提现失败）*/
	@NotEmpty
	private Long coStatus;
	/**提现用户id*/
	@NotEmpty
	private Long coUid;
	/**提现金额*/
	@NotEmpty
	protected Long coMoney;
	/**创建时间*/
	@NotEmpty
	private Date coCreateTime;
	/**审核人id（后面人员id）*/
	@NotEmpty
	private Long coAuditingSid;
	/**审核时间*/
	@NotEmpty
	private Date coAuditingTime;
	/**审核备注（拒绝时添加理由）*/
	@NotEmpty
	@Length(min = 1, max = 500)
	private String coAuditingRemark;
	/**提现备注*/
	@NotEmpty
	@Length(min = 1, max = 500)
	private String coRemark;
	/**下划用户id*/
	@NotEmpty
	private Long coCashSid;
	/**提现下划微信返回单号*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String coReturnNo;
	/**提现下划第三方返回数据*/
	@NotEmpty
	@Length(min = 1, max = 128)
	private String coReturnData;

	/**提现下划时间*/
	@NotEmpty
	private Date coReturnTime;
	/**提现实际到帐金额*/
	@NotEmpty
	protected java.lang.Long coActualMoney;
	/**用户提现费率*/
	@NotEmpty
	protected Double coRate;
	//columns END

	public CashOrder(){
	}

	public CashOrder( Long coId){
		this.coId = coId;
	}

	public void setCoId(Long value) {
		this.coId = value;
	}

	public Long getCoId() {
		return this.coId;
	}
	public void setCoNo(String value) {
		this.coNo = value;
	}

	public String getCoNo() {
		return this.coNo;
	}
	public void setCoStatus(Long value) {
		this.coStatus = value;
	}

	public Long getCoStatus() {
		return this.coStatus;
	}
	public void setCoUid(Long value) {
		this.coUid = value;
	}

	public Long getCoUid() {
		return this.coUid;
	}
	public void setCoMoney(Long value) {
		this.coMoney = value;
	}

	public Long getCoMoney() {
		return this.coMoney;
	}
	public void setCoCreateTime(Date value) {
		this.coCreateTime = value;
	}

	public Date getCoCreateTime() {
		return this.coCreateTime;
	}
	public void setCoAuditingSid(Long value) {
		this.coAuditingSid = value;
	}

	public Long getCoAuditingSid() {
		return this.coAuditingSid;
	}
	public void setCoAuditingTime(Date value) {
		this.coAuditingTime = value;
	}

	public Date getCoAuditingTime() {
		return this.coAuditingTime;
	}
	public void setCoAuditingRemark(String value) {
		this.coAuditingRemark = value;
	}

	public String getCoAuditingRemark() {
		return this.coAuditingRemark;
	}
	public void setCoRemark(String value) {
		this.coRemark = value;
	}

	public String getCoRemark() {
		return this.coRemark;
	}
	public void setCoCashSid(Long value) {
		this.coCashSid = value;
	}

	public Long getCoCashSid() {
		return this.coCashSid;
	}
	public void setCoReturnNo(String value) {
		this.coReturnNo = value;
	}

	public String getCoReturnNo() {
		return this.coReturnNo;
	}
	public void setCoReturnData(String value) {
		this.coReturnData = value;
	}

	public String getCoReturnData() {
		return this.coReturnData;
	}

	public Date getCoReturnTime() {
		return coReturnTime;
	}

	public void setCoReturnTime(Date coReturnTime) {
		this.coReturnTime = coReturnTime;
	}

	public Long getCoActualMoney() {
		return coActualMoney;
	}

	public void setCoActualMoney(Long coActualMoney) {
		this.coActualMoney = coActualMoney;
	}

	public Double getCoRate() {
		return coRate;
	}

	public void setCoRate(Double coRate) {
		this.coRate = coRate;
	}
}
