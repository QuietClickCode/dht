package com.retailers.dht.common.entity;
import com.alibaba.fastjson.annotation.JSONField;
import com.retailers.tools.utils.NumberUtils;
import com.retailers.tools.utils.ObjectUtils;
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
	/**是否限制使用（0 无限制，1限制使用（指定商品种类/ 指定商品）)**/
	private Long gcpIsRestricted;
	/**商品优惠活动触发条件*/
	@JSONField(serialize = false)
	private Long gcpCondition;
	/**
	 * 优惠条件计量单位（0 元，1 件）
	 */
	private Long gcpUnits;
	/**是否重叠使用*/
	private Integer gcpIsOverlapUse;
	/**商品优惠开始时间*/
	private Date gcpStartTime;
	/**商品优惠结束时间*/
	private Date gcpEndTime;
	/**优惠金额*/
	@JSONField(serialize = false)
	private Long gcpMoney;
	/**优惠折扣*/
	@JSONField(serialize = false)
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

	public String getGcpConditions() {
		return NumberUtils.formaterNumberPower(gcpCondition);
	}

	public Long getGcpUnits() {
		return gcpUnits;
	}

	public void setGcpUnits(Long gcpUnits) {
		this.gcpUnits = gcpUnits;
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
	public String getGcpMoneys() {
		if(ObjectUtils.isNotEmpty(gcpMoney)){
			return NumberUtils.formaterNumberPower(gcpMoney);
		}
		return null;
	}
	public void setGcpDiscount(Long value) {
		this.gcpDiscount = value;
	}

	public Long getGcpDiscount() {
		return this.gcpDiscount;
	}
	public String getGcpDiscounts() {
		if(ObjectUtils.isNotEmpty(gcpDiscount)){
			return NumberUtils.formaterNumberPower(gcpDiscount);
		}
		return null;
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

	public Long getGcpIsRestricted() {
		return gcpIsRestricted;
	}

	public void setGcpIsRestricted(Long gcpIsRestricted) {
		this.gcpIsRestricted = gcpIsRestricted;
	}
}
