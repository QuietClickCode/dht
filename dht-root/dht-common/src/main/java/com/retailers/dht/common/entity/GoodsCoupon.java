package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品优惠活动对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-10 00:15:11
 */
public class GoodsCoupon implements java.io.Serializable {

	/**gcpId*/
	private Long gcpId;
	/**商品优惠活动名称*/
	@Length(min = 1, max = 50)
	private String gcpName;
	/**商品优惠活动类型(0 优惠现金，1 总价折扣）*/
	private Integer gcpType;
	/**商品优惠活动触发条件*/
	private Long gcpCondition;
	/**是否重叠使用*/
	private Integer gcpIsOverlapUse;
	/**商品优惠开始时间*/
	private Date gcpStartTime;
	/**商品优惠结束时间*/
	private Date gcpEndTime;
	/**优惠金额*/
	private Long gcpMoney;
	/**优惠折扣*/
	private Long gcpDiscount;
	/**创建时间*/
	private Date gcpCreateTime;
	/**是否有效*/
	private Integer isValid;
	/**是否删除*/
	private Integer isDelete;
	/**数据版本*/
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
	public void setGcpDiscount(Long value) {
		this.gcpDiscount = value;
	}

	public Long getGcpDiscount() {
		return this.gcpDiscount;
	}
	public void setGcpCreateTime(Date value) {
		this.gcpCreateTime = value;
	}

	public Date getGcpCreateTime() {
		return this.gcpCreateTime;
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
