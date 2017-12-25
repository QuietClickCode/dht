package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：订单支付成功队例对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-24 11:30:58
 */
public class OrderSuccessQueue implements java.io.Serializable {

	/**id*/
	@NotEmpty
	private Long id;
	/**订单号*/
	@NotEmpty
	private Long orderId;
	/**创建时间*/
	@NotEmpty
	private Date createTime;
	/**执行次数*/
	@NotEmpty
	private Integer executeNum;
	/**状态*/
	@NotEmpty
	private Integer status;
	/**备注*/
	@NotEmpty
	@Length(min = 1, max = 1000)
	private String remark;
	//columns END

	public OrderSuccessQueue(){
	}

	public OrderSuccessQueue( Long id){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}
	public void setOrderId(Long value) {
		this.orderId = value;
	}

	public Long getOrderId() {
		return this.orderId;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}
	public void setExecuteNum(Integer value) {
		this.executeNum = value;
	}

	public Integer getExecuteNum() {
		return this.executeNum;
	}
	public void setStatus(Integer value) {
		this.status = value;
	}

	public Integer getStatus() {
		return this.status;
	}
	public void setRemark(String value) {
		this.remark = value;
	}

	public String getRemark() {
		return this.remark;
	}


}
