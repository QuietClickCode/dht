package com.retailers.dht.common.entity;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
/**
 * 描述：商品用户评论关系表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-16 13:54:57
 */
public class GoodsUggclrel implements java.io.Serializable {

	/**用户商品评论关系ID*/
	@NotEmpty
	private Long uggclId;
	/**用户ID*/
	@NotEmpty
	private Long uid;
	/**商品ID*/
	@NotEmpty
	private Long gid;
	/**评论Id*/
	@NotEmpty
	private Long gclId;
	/**创建时间*/
	@NotEmpty
	private Date gclTime;
	/**
	 * 订单号
	 */
	@NotEmpty
	private Long orderNo;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsUggclrel(){
	}

	public GoodsUggclrel( Long uggclId){
		this.uggclId = uggclId;
	}

	public void setUggclId(Long value) {
		this.uggclId = value;
	}

	public Long getUggclId() {
		return this.uggclId;
	}
	public void setUid(Long value) {
		this.uid = value;
	}

	public Long getUid() {
		return this.uid;
	}
	public void setGid(Long value) {
		this.gid = value;
	}

	public Long getGid() {
		return this.gid;
	}
	public void setGclId(Long value) {
		this.gclId = value;
	}

	public Long getGclId() {
		return this.gclId;
	}
	public void setGclTime(Date value) {
		this.gclTime = value;
	}

	public Date getGclTime() {
		return this.gclTime;
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

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
}
