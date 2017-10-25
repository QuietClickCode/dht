package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品与品牌关系表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-24 10:09:42
 */
public class GoodsGgbrel implements java.io.Serializable {

	/**商品与品牌关系ID*/
	@NotEmpty
	private Long ggbId;
	/**商品ID*/
	@NotEmpty
	private Long gid;
	/**品牌ID*/
	@NotEmpty
	private Long gbId;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsGgbrel(){
	}

	public GoodsGgbrel( Long ggbId){
		this.ggbId = ggbId;
	}

	public void setGgbId(Long value) {
		this.ggbId = value;
	}

	public Long getGgbId() {
		return this.ggbId;
	}
	public void setGid(Long value) {
		this.gid = value;
	}

	public Long getGid() {
		return this.gid;
	}
	public void setGbId(Long value) {
		this.gbId = value;
	}

	public Long getGbId() {
		return this.gbId;
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
