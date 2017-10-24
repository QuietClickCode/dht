package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品与规格值详情表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-20 09:52:11
 */
public class GoodsGgsvalDetail implements java.io.Serializable {

	/**规格值与商品关系细节ID*/
	@NotEmpty
	private Long ggdId;
	/**商品ID*/
	@NotEmpty
	private Long gid;
	/**规格ID*/
	@NotEmpty
	private Long gsId;
	/**商品规格值*/
	@NotEmpty
	private Long gsvId;
	/**商品详情ID*/
	@NotEmpty
	private Long gdId;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsGgsvalDetail(){
	}

	public GoodsGgsvalDetail( Long ggdId){
		this.ggdId = ggdId;
	}

	public void setGgdId(Long value) {
		this.ggdId = value;
	}

	public Long getGgdId() {
		return this.ggdId;
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
	public void setGdId(Long value) {
		this.gdId = value;
	}

	public Long getGdId() {
		return this.gdId;
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
