package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：执行队列对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-16 23:43:17
 */
public class ExecuteQueue implements java.io.Serializable {

	/**id*/
	@NotEmpty
	private Long id;
	/**执行队列类型（0 优惠卷，1 秒杀，2 抢购，3订单回调）*/
	@NotEmpty
	private Integer seqType;
	/**请求用户id*/
	@NotEmpty
	private Long seqUid;
	/**执行id*/
	@NotEmpty
	@Length(min = 1, max = 128)
	private String seqExeId;
	/**参数*/
	@NotEmpty
	@Length(min = 1, max = 640)
	private String seqParams;
	/**创建时间*/
	@NotEmpty
	private Date seqCreateTime;
	/**执行时间*/
	@NotEmpty
	private Date seqExeTime;
	/**执行时长*/
	@NotEmpty
	private Long seqTime;
	/**等待时间*/
	@NotEmpty
	private Long seqWaitTime;
	/**执行状态（0 成功，1 失败）*/
	private java.lang.Integer seqStatus;
	/**seqRemark*/
	private java.lang.String seqRemark;
	/**数据版本*/
	@NotEmpty
	private Integer version;
	//columns END

	public ExecuteQueue(){
	}

	public ExecuteQueue( Long id){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}
	public void setSeqType(Integer value) {
		this.seqType = value;
	}

	public Integer getSeqType() {
		return this.seqType;
	}
	public void setSeqUid(Long value) {
		this.seqUid = value;
	}

	public Long getSeqUid() {
		return this.seqUid;
	}
	public void setSeqExeId(String value) {
		this.seqExeId = value;
	}

	public String getSeqExeId() {
		return this.seqExeId;
	}
	public void setSeqParams(String value) {
		this.seqParams = value;
	}

	public String getSeqParams() {
		return this.seqParams;
	}
	public void setSeqCreateTime(Date value) {
		this.seqCreateTime = value;
	}

	public Date getSeqCreateTime() {
		return this.seqCreateTime;
	}
	public void setSeqExeTime(Date value) {
		this.seqExeTime = value;
	}

	public Date getSeqExeTime() {
		return this.seqExeTime;
	}

	public Long getSeqTime() {
		return seqTime;
	}

	public void setSeqTime(Long seqTime) {
		this.seqTime = seqTime;
	}

	public void setSeqWaitTime(Long value) {
		this.seqWaitTime = value;
	}

	public Long getSeqWaitTime() {
		return this.seqWaitTime;
	}
	public void setVersion(Integer value) {
		this.version = value;
	}

	public Integer getVersion() {
		return this.version;
	}

	public Integer getSeqStatus() {
		return seqStatus;
	}

	public void setSeqStatus(Integer seqStatus) {
		this.seqStatus = seqStatus;
	}

	public String getSeqRemark() {
		return seqRemark;
	}

	public void setSeqRemark(String seqRemark) {
		this.seqRemark = seqRemark;
	}
}
