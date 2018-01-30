package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：退款订单列表对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2018-01-29 23:49:26
 */
public class OrderRefund implements java.io.Serializable {

	/**rdId*/
	@NotEmpty
	private Long rdId;
	/**退款单号（用户发起退款申请，生成单号）*/
	@NotEmpty
	@Length(min = 1, max = 20)
	private String rdOrderNo;
	/**退款订单id*/
	@NotEmpty
	private Long rdOrder;
	/**退款金额*/
	@NotEmpty
	private Long rdPrice;
	/**退还类型（0 消费累计，1 消费排名，2 混合）*/
	@NotEmpty
	private Long rdIntegral;
	/**退款回执单号（如果是钱包支付的为订单号）*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String rdCallbackNo;
	/**退款发起时间*/
	@NotEmpty
	private Date rdSendDate;
	/**退款时间(退款成功时间)*/
	@NotEmpty
	private Date rdDate;
	/**退款备注（用户填写）*/
	@NotEmpty
	@Length(min = 1, max = 600)
	private String rdRemark;
	/**退款发起时间*/
	@NotEmpty
	private Date rdCreateDate;
	/**退款状态（0 待审核，1  审核失败，2 审核成功，3 发起退款，4 退款成功，5 退款失败）*/
	@NotEmpty
	private Long rdStatus;
	/**审核用户id*/
	@NotEmpty
	private Long rdSuid;
	/**审核备注*/
	@NotEmpty
	@Length(min = 1, max = 500)
	private String rdAuditingRemark;
	/**审核时间*/
	@NotEmpty
	private Date rdAuditingDate;
	//columns END

	public OrderRefund(){
	}

	public OrderRefund( Long rdId){
		this.rdId = rdId;
	}

	public void setRdId(Long value) {
		this.rdId = value;
	}

	public Long getRdId() {
		return this.rdId;
	}
	public void setRdOrderNo(String value) {
		this.rdOrderNo = value;
	}

	public String getRdOrderNo() {
		return this.rdOrderNo;
	}
	public void setRdOrder(Long value) {
		this.rdOrder = value;
	}

	public Long getRdOrder() {
		return this.rdOrder;
	}
	public void setRdPrice(Long value) {
		this.rdPrice = value;
	}

	public Long getRdPrice() {
		return this.rdPrice;
	}
	public void setRdIntegral(Long value) {
		this.rdIntegral = value;
	}

	public Long getRdIntegral() {
		return this.rdIntegral;
	}
	public void setRdCallbackNo(String value) {
		this.rdCallbackNo = value;
	}

	public String getRdCallbackNo() {
		return this.rdCallbackNo;
	}
	public void setRdSendDate(Date value) {
		this.rdSendDate = value;
	}

	public Date getRdSendDate() {
		return this.rdSendDate;
	}
	public void setRdDate(Date value) {
		this.rdDate = value;
	}

	public Date getRdDate() {
		return this.rdDate;
	}
	public void setRdRemark(String value) {
		this.rdRemark = value;
	}

	public String getRdRemark() {
		return this.rdRemark;
	}
	public void setRdCreateDate(Date value) {
		this.rdCreateDate = value;
	}

	public Date getRdCreateDate() {
		return this.rdCreateDate;
	}
	public void setRdStatus(Long value) {
		this.rdStatus = value;
	}

	public Long getRdStatus() {
		return this.rdStatus;
	}
	public void setRdSuid(Long value) {
		this.rdSuid = value;
	}

	public Long getRdSuid() {
		return this.rdSuid;
	}
	public void setRdAuditingRemark(String value) {
		this.rdAuditingRemark = value;
	}

	public String getRdAuditingRemark() {
		return this.rdAuditingRemark;
	}
	public void setRdAuditingDate(Date value) {
		this.rdAuditingDate = value;
	}

	public Date getRdAuditingDate() {
		return this.rdAuditingDate;
	}


}
