package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：订单处理队列（订单支付回调，订单状态变更）对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-14 01:32:58
 */
public class OrderProcessingQueue implements java.io.Serializable {

	/**id*/
	@NotEmpty
	private Long id;
	/**订单处理类型（0 修改订单状态，1 支付回调，2 退款状态）*/
	@NotEmpty
	private Long type;
	/**执行状态（0 未执行，1 执行失败，2执行成功）*/
	@NotEmpty
	private Long status;
	/**订单号*/
	@NotEmpty
	@Length(min = 1, max = 32)
	private String orderNo;
	/**参数json串*/
	@NotEmpty
	@Length(min = 1, max = 500)
	private String params;
	/**创建时间*/
	@NotEmpty
	private Date createTime;
	/**开始执行时间*/
	@NotEmpty
	private Date executeTime;
	/**执行等待时间*/
	@NotEmpty
	private Long waitTime;
	/**remark*/
	@NotEmpty
	@Length(min = 1, max = 500)
	private String remark;
	//columns END

	public OrderProcessingQueue(){
	}

	public OrderProcessingQueue( Long id){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}
	public void setType(Long value) {
		this.type = value;
	}

	public Long getType() {
		return this.type;
	}
	public void setStatus(Long value) {
		this.status = value;
	}

	public Long getStatus() {
		return this.status;
	}
	public void setOrderNo(String value) {
		this.orderNo = value;
	}

	public String getOrderNo() {
		return this.orderNo;
	}
	public void setParams(String value) {
		this.params = value;
	}

	public String getParams() {
		return this.params;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}
	public void setExecuteTime(Date value) {
		this.executeTime = value;
	}

	public Date getExecuteTime() {
		return this.executeTime;
	}
	public void setWaitTime(Long value) {
		this.waitTime = value;
	}

	public Long getWaitTime() {
		return this.waitTime;
	}
	public void setRemark(String value) {
		this.remark = value;
	}

	public String getRemark() {
		return this.remark;
	}


}
