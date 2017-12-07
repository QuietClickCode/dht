package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：优惠卷对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-12 23:08:44
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
	/**是否限制使用（0 无限制，1限制使用（指定商品种类/ 指定商品）)**/
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
	/**周期发送类型（0 按年，1 按季，2 按月，3按周，4 按天）*/
	@NotEmpty
	private Integer cpCycleSendType;
	/**周期发送次数次数*/
	@NotEmpty
	private Long cpSendNum;
	/**发送优惠卷张数*/
	@NotEmpty
	private Long cpNum;
	/**
	 * 优惠卷剩余张数
	 */
	private Long cpSurplusNum;
	/**金额*/
	@NotEmpty
	private Long cpMoney;
	/**折扣*/
	@NotEmpty
	private Long cpDiscount;
	/**发送优惠卷总金额（拼手机优惠卷 代金卷）*/
	@NotEmpty
	private Long cpTotalMoney;
	/**拼手机折扣浮动最小值*/
	@NotEmpty
	private Long cpMinDiscount;
	/**拼手机折扣浮动最大值*/
	@NotEmpty
	private Long cpMaxDiscount;
	/**卡券领取与使用规则*/
	@NotEmpty
	@Length(min = 1, max = 1000)
	private String cpContext;
	/**优惠卷创建用户*/
	@NotEmpty
	private Long cpCreateSid;
	/**优惠卷创建时间*/
	@NotEmpty
	private Date cpCreate;
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
	public void setCpCycleSendType(Integer value) {
		this.cpCycleSendType = value;
	}

	public Integer getCpCycleSendType() {
		return this.cpCycleSendType;
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
	public void setCpTotalMoney(Long value) {
		this.cpTotalMoney = value;
	}

	public Long getCpTotalMoney() {
		return this.cpTotalMoney;
	}
	public void setCpMinDiscount(Long value) {
		this.cpMinDiscount = value;
	}

	public Long getCpMinDiscount() {
		return this.cpMinDiscount;
	}
	public void setCpMaxDiscount(Long value) {
		this.cpMaxDiscount = value;
	}

	public Long getCpMaxDiscount() {
		return this.cpMaxDiscount;
	}
	public void setCpContext(String value) {
		this.cpContext = value;
	}

	public String getCpContext() {
		return this.cpContext;
	}
	public void setCpCreateSid(Long value) {
		this.cpCreateSid = value;
	}

	public Long getCpCreateSid() {
		return this.cpCreateSid;
	}
	public void setCpCreate(Date value) {
		this.cpCreate = value;
	}

	public Date getCpCreate() {
		return this.cpCreate;
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

	public Long getCpSurplusNum() {
		return cpSurplusNum;
	}

	public void setCpSurplusNum(Long cpSurplusNum) {
		this.cpSurplusNum = cpSurplusNum;
	}
}
