package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：是否购买砍价商品表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-09 10:10:14
 */
public class GoodsIsbuycp implements java.io.Serializable {

	/**是否购买砍价id*/
	@NotEmpty
	private Long ibcp;
	/**砍价id*/
	@NotEmpty
	private Long cpId;
	/**用户ID*/
	@NotEmpty
	private Long uid;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsIsbuycp(){
	}

	public GoodsIsbuycp( Long ibcp){
		this.ibcp = ibcp;
	}

	public void setIbcp(Long value) {
		this.ibcp = value;
	}

	public Long getIbcp() {
		return this.ibcp;
	}
	public void setCpId(Long value) {
		this.cpId = value;
	}

	public Long getCpId() {
		return this.cpId;
	}
	public void setUid(Long value) {
		this.uid = value;
	}

	public Long getUid() {
		return this.uid;
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
