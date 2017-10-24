package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品与规格值关系表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-20 09:54:38
 */
public class GoodsGgsval implements java.io.Serializable {

	/**商品规格值ID*/
	@NotEmpty
	private Long ggsId;
	/**商品ID*/
	@NotEmpty
	private Long gid;
	/**规格ID*/
	@NotEmpty
	private Long gsId;
	/**商品规格值*/
	@NotEmpty
	private Long gsvId;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsGgsval(){
	}

	public GoodsGgsval( Long ggsId){
		this.ggsId = ggsId;
	}

	public void setGgsId(Long value) {
		this.ggsId = value;
	}

	public Long getGgsId() {
		return this.ggsId;
	}
	public void setGid(Long value) {
		this.gid = value;
	}

	public Long getGid() {
		return this.gid;
	}
	public void setGsId(Long value) {
		this.gsId = value;
	}

	public Long getGsId() {
		return this.gsId;
	}
	public void setGsvId(Long value) {
		this.gsvId = value;
	}

	public Long getGsvId() {
		return this.gsvId;
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
