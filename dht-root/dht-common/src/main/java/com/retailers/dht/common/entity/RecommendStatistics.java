package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：推荐返现金额对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-25 22:50:20
 */
public class RecommendStatistics implements java.io.Serializable {

	/**rsId*/
	@NotEmpty
	private Long rsId;
	/**推荐类型（0 首单推荐，1 消费推荐）*/
	@NotEmpty
	private Integer rsType;
	/**状态（0，未结算，1 己结算）*/
	@NotEmpty
	private Integer rsStatus;
	/**推荐消费订单id*/
	@NotEmpty
	private Long rsOid;
	/**推荐消费订单详情id*/
	@NotEmpty
	private Long rsOdId;
	/**购买用户*/
	@NotEmpty
	private Long rsUid;
	/**推荐人*/
	@NotEmpty
	private Long rsRecommendUid;
	/**推荐人类型（1 兼职人员，2推广人员）*/
	@NotEmpty
	private Long rsUtype;
	/**消费金额*/
	@NotEmpty
	private Long rsSalesPrice;
	/**提成比例*/
	@NotEmpty
	private Long rsRatio;
	/**rsPrice*/
	@NotEmpty
	private Long rsPrice;
	/**创建时间*/
	@NotEmpty
	private Date rsTime;
	//columns END

	public RecommendStatistics(){
	}

	public RecommendStatistics( Long rsId){
		this.rsId = rsId;
	}

	public void setRsId(Long value) {
		this.rsId = value;
	}

	public Long getRsId() {
		return this.rsId;
	}
	public void setRsType(Integer value) {
		this.rsType = value;
	}

	public Integer getRsType() {
		return this.rsType;
	}
	public void setRsStatus(Integer value) {
		this.rsStatus = value;
	}

	public Integer getRsStatus() {
		return this.rsStatus;
	}
	public void setRsOid(Long value) {
		this.rsOid = value;
	}

	public Long getRsOid() {
		return this.rsOid;
	}
	public void setRsOdId(Long value) {
		this.rsOdId = value;
	}

	public Long getRsOdId() {
		return this.rsOdId;
	}
	public void setRsUid(Long value) {
		this.rsUid = value;
	}

	public Long getRsUid() {
		return this.rsUid;
	}
	public void setRsRecommendUid(Long value) {
		this.rsRecommendUid = value;
	}

	public Long getRsRecommendUid() {
		return this.rsRecommendUid;
	}
	public void setRsUtype(Long value) {
		this.rsUtype = value;
	}

	public Long getRsUtype() {
		return this.rsUtype;
	}
	public void setRsSalesPrice(Long value) {
		this.rsSalesPrice = value;
	}

	public Long getRsSalesPrice() {
		return this.rsSalesPrice;
	}
	public void setRsRatio(Long value) {
		this.rsRatio = value;
	}

	public Long getRsRatio() {
		return this.rsRatio;
	}
	public void setRsPrice(Long value) {
		this.rsPrice = value;
	}

	public Long getRsPrice() {
		return this.rsPrice;
	}

	public Date getRsTime() {
		return rsTime;
	}

	public void setRsTime(Date rsTime) {
		this.rsTime = rsTime;
	}
}
