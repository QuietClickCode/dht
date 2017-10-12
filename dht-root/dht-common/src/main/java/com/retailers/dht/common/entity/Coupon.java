package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：优惠卷对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-11 21:36:45
 */
public class Coupon implements java.io.Serializable {

	/**cpId*/
	@NotEmpty
	private Long cpId;
	/**优惠卷名称*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String cpName;
	/**优惠卷金额类型（0 固定金额，1 随机金额)*/
	@NotEmpty
	private Integer cpCoinType;
	/**优惠类型（0 代金卷，1 折扣卷）*/
	@NotEmpty
	private Integer cpType;
	/**卡券图片（关联附件id）*/
	@NotEmpty
	private Long cpLogo;
	/**是否限制使用（0 无限制，1 指定商品种类，2 指定商品）*/
	@NotEmpty
	private Long cpIsRestricted;
	/**领取条件*/
	@NotEmpty
	@Length(min = 1, max = 300)
	private String cpReceiveCondition;
	/**使用条件（直减/直折的时候可填写-1或者不填）*/
	@NotEmpty
	private Long cpUseCondition;
	/**优惠卷 有效时间开始*/
	@NotEmpty
	private Date cpStartDate;
	/**优惠卷 有效时间结束*/
	@NotEmpty
	private Date cpEndDate;
	/**能否叠加使用（0 可以叠加使用，1 不可叠加使用）*/
	@NotEmpty
	private Integer cpIsOverlapUse;
	/**是否优先使用(0 优先使用，1 不是优先使用）*/
	@NotEmpty
	private Integer cpIsFirst;
	/**发放方式（0 及时发送，1 定时发送，2 周期发送）*/
	@NotEmpty
	private Integer cpSendWay;
	/**发放开始时间*/
	@NotEmpty
	private Date cpSendStartDate;
	/**发放结束时间*/
	@NotEmpty
	private Date cpSendEndDate;
	/**周期发送次数次数*/
	@NotEmpty
	private Long cpSendNum;
	/**总张数*/
	@NotEmpty
	private Long cpNum;
	/**金额*/
	@NotEmpty
	private Long cpMoney;
	/**折扣*/
	@NotEmpty
	private Long cpDiscount;
	/**卡券领取与使用规则*/
	@NotEmpty
	@Length(min = 1, max = 1000)
	private String cpContext;
	/**是否删除（0 未删作，1 删除）*/
	@NotEmpty
	private Integer isDelete;
	/**是否有效（0 有效，1 无效）*/
	@NotEmpty
	private Integer isValid;
	/**数据版本*/
	@NotEmpty
	private Integer version;
	//columns END

	public Coupon(){
	}

	public Coupon( Long cpId){
		this.cpId = cpId;
	}

	public void setCpId(Long value) {
		this.cpId = value;
	}

	public Long getCpId() {
		return this.cpId;
	}
	public void setCpName(String value) {
		this.cpName = value;
	}

	public String getCpName() {
		return this.cpName;
	}
	public void setCpCoinType(Integer value) {
		this.cpCoinType = value;
	}

	public Integer getCpCoinType() {
		return this.cpCoinType;
	}
	public void setCpType(Integer value) {
		this.cpType = value;
	}

	public Integer getCpType() {
		return this.cpType;
	}
	public void setCpLogo(Long value) {
		this.cpLogo = value;
	}

	public Long getCpLogo() {
		return this.cpLogo;
	}
	public void setCpIsRestricted(Long value) {
		this.cpIsRestricted = value;
	}

	public Long getCpIsRestricted() {
		return this.cpIsRestricted;
	}
	public void setCpReceiveCondition(String value) {
		this.cpReceiveCondition = value;
	}

	public String getCpReceiveCondition() {
		return this.cpReceiveCondition;
	}
	public void setCpUseCondition(Long value) {
		this.cpUseCondition = value;
	}

	public Long getCpUseCondition() {
		return this.cpUseCondition;
	}
	public void setCpStartDate(Date value) {
		this.cpStartDate = value;
	}

	public Date getCpStartDate() {
		return this.cpStartDate;
	}
	public void setCpEndDate(Date value) {
		this.cpEndDate = value;
	}

	public Date getCpEndDate() {
		return this.cpEndDate;
	}
	public void setCpIsOverlapUse(Integer value) {
		this.cpIsOverlapUse = value;
	}

	public Integer getCpIsOverlapUse() {
		return this.cpIsOverlapUse;
	}
	public void setCpIsFirst(Integer value) {
		this.cpIsFirst = value;
	}

	public Integer getCpIsFirst() {
		return this.cpIsFirst;
	}
	public void setCpSendWay(Integer value) {
		this.cpSendWay = value;
	}

	public Integer getCpSendWay() {
		return this.cpSendWay;
	}
	public void setCpSendStartDate(Date value) {
		this.cpSendStartDate = value;
	}

	public Date getCpSendStartDate() {
		return this.cpSendStartDate;
	}
	public void setCpSendEndDate(Date value) {
		this.cpSendEndDate = value;
	}

	public Date getCpSendEndDate() {
		return this.cpSendEndDate;
	}
	public void setCpSendNum(Long value) {
		this.cpSendNum = value;
	}

	public Long getCpSendNum() {
		return this.cpSendNum;
	}
	public void setCpNum(Long value) {
		this.cpNum = value;
	}

	public Long getCpNum() {
		return this.cpNum;
	}
	public void setCpMoney(Long value) {
		this.cpMoney = value;
	}

	public Long getCpMoney() {
		return this.cpMoney;
	}
	public void setCpDiscount(Long value) {
		this.cpDiscount = value;
	}

	public Long getCpDiscount() {
		return this.cpDiscount;
	}
	public void setCpContext(String value) {
		this.cpContext = value;
	}

	public String getCpContext() {
		return this.cpContext;
	}
	public void setIsDelete(Integer value) {
		this.isDelete = value;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}
	public void setIsValid(Integer value) {
		this.isValid = value;
	}

	public Integer getIsValid() {
		return this.isValid;
	}
	public void setVersion(Integer value) {
		this.version = value;
	}

	public Integer getVersion() {
		return this.version;
	}


}
