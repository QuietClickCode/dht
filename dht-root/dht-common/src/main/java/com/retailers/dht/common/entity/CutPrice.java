package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：砍价表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-05 16:14:43
 */
public class CutPrice implements java.io.Serializable {

	/**砍价活动id*/
	@NotEmpty
	private Long cpId;
	/**砍价活动名称*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String cpName;
	/**商品ID*/
	@NotEmpty
	private Long gid;
	/**父类ID*/
	@NotEmpty
	private Long parentId;
	/**排序*/
	@NotEmpty
	private Long spOrder;
	/**最少砍价人数*/
	@NotEmpty
	private Long cpLestpseson;
	/**砍价最多人数*/
	@NotEmpty
	private Long cpMostperson;
	/**是否使用优惠券（1代表允许使用优惠券，0代表不允许使用优惠券）*/
	@NotEmpty
	private Long isCoupon;
	/**推送地区（0代表乡村，1代表城市）*/
	@NotEmpty
	private Long cpRegion;
	/**开始时间*/
	@NotEmpty
	private Date cpStartTime;
	/**结束时间*/
	@NotEmpty
	private Date cpEndTime;
	/**是否删除（1删除，0不删除）*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public CutPrice(){
	}

	public CutPrice( Long cpId){
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
	public void setGid(Long value) {
		this.gid = value;
	}

	public Long getGid() {
		return this.gid;
	}
	public void setParentId(Long value) {
		this.parentId = value;
	}

	public Long getParentId() {
		return this.parentId;
	}
	public void setSpOrder(Long value) {
		this.spOrder = value;
	}

	public Long getSpOrder() {
		return this.spOrder;
	}
	public void setCpLestpseson(Long value) {
		this.cpLestpseson = value;
	}

	public Long getCpLestpseson() {
		return this.cpLestpseson;
	}
	public void setCpMostperson(Long value) {
		this.cpMostperson = value;
	}

	public Long getCpMostperson() {
		return this.cpMostperson;
	}
	public void setIsCoupon(Long value) {
		this.isCoupon = value;
	}

	public Long getIsCoupon() {
		return this.isCoupon;
	}
	public void setCpRegion(Long value) {
		this.cpRegion = value;
	}

	public Long getCpRegion() {
		return this.cpRegion;
	}
	public void setCpStartTime(Date value) {
		this.cpStartTime = value;
	}

	public Date getCpStartTime() {
		return this.cpStartTime;
	}
	public void setCpEndTime(Date value) {
		this.cpEndTime = value;
	}

	public Date getCpEndTime() {
		return this.cpEndTime;
	}
	public void setIsDelete(Long value) {
		this.isDelete = value;
	}

	public Long getIsDelete() {
		return this.isDelete;
	}
	public void setVersion(Long value) {
		this.version = value;
	}

	public Long getVersion() {
		return this.version;
	}


}
