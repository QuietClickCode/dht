package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：是否购买活动商品表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-05 15:09:07
 */
public class GoodsIsbuysp implements java.io.Serializable {

	/**是否购买特价/秒杀id*/
	@NotEmpty
	private Long ibsp;
	/**特价/秒杀id*/
	@NotEmpty
	private Long spId;
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

	public GoodsIsbuysp(){
	}

	public GoodsIsbuysp( Long ibsp){
		this.ibsp = ibsp;
	}

	public void setIbsp(Long value) {
		this.ibsp = value;
	}

	public Long getIbsp() {
		return this.ibsp;
	}
	public void setSpId(Long value) {
		this.spId = value;
	}

	public Long getSpId() {
		return this.spId;
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
