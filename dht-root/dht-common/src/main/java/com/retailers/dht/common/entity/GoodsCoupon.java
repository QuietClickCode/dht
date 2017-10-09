package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品优惠活动对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-09 20:54:20
 */
public class GoodsCoupon implements java.io.Serializable {

	/**gcpId*/
	@NotEmpty
	private Long gcpId;
	/**商品优惠活动名称*/
	@NotEmpty
	@Length(min = 1, max = 50)
	private String gcpName;
	/**商品优惠活动类型(0 优惠现金，1 总价折扣）*/
	@NotEmpty(message = "商品优惠活动类型不能为空")
	private Integer gcpType;
	/**商品优惠活动触发条件*/
	@NotEmpty
	private Long gcpCondition;
	/**是否重叠使用*/
	@NotEmpty
	private Integer gcpIsOverlapUse;
	/**商品优惠开始时间*/
	@NotEmpty
	private Date gcpStartTime;
	/**创建时间*/
	@NotEmpty
	private Date gcpCreateTime;
	/**商品优惠结束时间*/
	@NotEmpty
	private Date gcpEndTime;
	/**优惠金额*/
	@NotEmpty
	private Long gcpMoney;
	/**优惠折扣*/
	@NotEmpty
	private Long cpDiscount;
	/**是否有效*/
	@NotEmpty
	private Integer isValid;
	/**是否删除*/
	@NotEmpty
	private Integer isDelete;
	/**数据版本*/
	@NotEmpty
	private Integer version;
	//columns END

	public GoodsCoupon(){
	}

	public GoodsCoupon( Long gcpId){
		this.gcpId = gcpId;
	}

	public void setGcpId(Long value) {
		this.gcpId = value;
	}

	public Long getGcpId() {
		return this.gcpId;
	}
	public void setGcpName(String value) {
		this.gcpName = value;
	}

	public String getGcpName() {
		return this.gcpName;
	}
	public void setGcpType(Integer value) {
		this.gcpType = value;
	}

	public Integer getGcpType() {
		return this.gcpType;
	}
	public void setGcpCondition(Long value) {
		this.gcpCondition = value;
	}

	public Long getGcpCondition() {
		return this.gcpCondition;
	}
	public void setGcpIsOverlapUse(Integer value) {
		this.gcpIsOverlapUse = value;
	}

	public Integer getGcpIsOverlapUse() {
		return this.gcpIsOverlapUse;
	}
	public void setGcpStartTime(Date value) {
		this.gcpStartTime = value;
	}

	public Date getGcpStartTime() {
		return this.gcpStartTime;
	}
	public void setGcpCreateTime(Date value) {
		this.gcpCreateTime = value;
	}

	public Date getGcpCreateTime() {
		return this.gcpCreateTime;
	}
	public void setGcpEndTime(Date value) {
		this.gcpEndTime = value;
	}

	public Date getGcpEndTime() {
		return this.gcpEndTime;
	}
	public void setGcpMoney(Long value) {
		this.gcpMoney = value;
	}

	public Long getGcpMoney() {
		return this.gcpMoney;
	}
	public void setCpDiscount(Long value) {
		this.cpDiscount = value;
	}

	public Long getCpDiscount() {
		return this.cpDiscount;
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
