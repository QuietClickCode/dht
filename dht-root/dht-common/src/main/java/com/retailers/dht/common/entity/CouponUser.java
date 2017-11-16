package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：用户优惠卷领取记录对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-16 23:31:01
 */
public class CouponUser implements java.io.Serializable {

	/**cpuId*/
	@NotEmpty
	private Long cpuId;
	/**优惠卷id*/
	@NotEmpty
	private Long cpId;
	/**周期优惠卷id*/
	@NotEmpty
	private Integer cpuCycleId;
	/**领取优惠卷用户id*/
	@NotEmpty
	private Long cpuUid;
	/**优惠卷所处状态（0 未使用，1 己使用，2 己过期）*/
	@NotEmpty
	private Long cpuStatus;
	/**领取时间*/
	@NotEmpty
	private Date cpuCreateTime;
	/**使用时间*/
	@NotEmpty
	private Date cpuUseTime;
	/**关联订单id*/
	@NotEmpty
	private Long cpuUseOid;
	/**数据版本*/
	@NotEmpty
	private Integer version;
	//columns END

	public CouponUser(){
	}

	public CouponUser( Long cpuId){
		this.cpuId = cpuId;
	}

	public void setCpuId(Long value) {
		this.cpuId = value;
	}

	public Long getCpuId() {
		return this.cpuId;
	}
	public void setCpId(Long value) {
		this.cpId = value;
	}

	public Long getCpId() {
		return this.cpId;
	}
	public void setCpuCycleId(Integer value) {
		this.cpuCycleId = value;
	}

	public Integer getCpuCycleId() {
		return this.cpuCycleId;
	}
	public void setCpuUid(Long value) {
		this.cpuUid = value;
	}

	public Long getCpuUid() {
		return this.cpuUid;
	}
	public void setCpuStatus(Long value) {
		this.cpuStatus = value;
	}

	public Long getCpuStatus() {
		return this.cpuStatus;
	}
	public void setCpuCreateTime(Date value) {
		this.cpuCreateTime = value;
	}

	public Date getCpuCreateTime() {
		return this.cpuCreateTime;
	}
	public void setCpuUseTime(Date value) {
		this.cpuUseTime = value;
	}

	public Date getCpuUseTime() {
		return this.cpuUseTime;
	}
	public void setCpuUseOid(Long value) {
		this.cpuUseOid = value;
	}

	public Long getCpuUseOid() {
		return this.cpuUseOid;
	}
	public void setVersion(Integer value) {
		this.version = value;
	}

	public Integer getVersion() {
		return this.version;
	}


}
