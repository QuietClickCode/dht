package com.retailers.hnc.common.entity;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 描述：开盘和置业顾问关系表对象
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-28 14:55:17
 */
public class EmRelationship implements java.io.Serializable {

	/**erId*/
	@NotEmpty
	private Long erId;
	/**开盘期数ID*/
	@NotEmpty
	private Long pid;
	/**置业顾问ID*/
	@NotEmpty
	private Long emId;
	/**团队ID*/
	@NotEmpty
	private Long tid;
	/**预约人数*/
	@NotEmpty
	private Long emReservation;
	/**是否删除（0代表不删除，1代表删除）默认为0*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public EmRelationship(){
	}

	public EmRelationship( Long erId){
		this.erId = erId;
	}

	public void setErId(Long value) {
		this.erId = value;
	}

	public Long getErId() {
		return this.erId;
	}
	public void setPid(Long value) {
		this.pid = value;
	}

	public Long getPid() {
		return this.pid;
	}
	public void setEmId(Long value) {
		this.emId = value;
	}

	public Long getEmId() {
		return this.emId;
	}
	public void setTid(Long value) {
		this.tid = value;
	}

	public Long getTid() {
		return this.tid;
	}
	public void setEmReservation(Long value) {
		this.emReservation = value;
	}

	public Long getEmReservation() {
		return this.emReservation;
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
