package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品详情与砍价关系表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-05 16:15:28
 */
public class GoodsGdcprel implements java.io.Serializable {

	/**商品详情与砍价关系Id*/
	@NotEmpty
	private Long gdcpId;
	/**商品详情Id*/
	@NotEmpty
	private Long gdId;
	/**特价ID*/
	@NotEmpty
	private Long cpId;
	/**底价*/
	@NotEmpty
	private Float cpSale;
	/**活动最多库存*/
	@NotEmpty
	private Long cpInventory;
	/**限购量*/
	@NotEmpty
	private Long cpBounds;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsGdcprel(){
	}

	public GoodsGdcprel( Long gdcpId){
		this.gdcpId = gdcpId;
	}

	public void setGdcpId(Long value) {
		this.gdcpId = value;
	}

	public Long getGdcpId() {
		return this.gdcpId;
	}
	public void setGdId(Long value) {
		this.gdId = value;
	}

	public Long getGdId() {
		return this.gdId;
	}
	public void setCpId(Long value) {
		this.cpId = value;
	}

	public Long getCpId() {
		return this.cpId;
	}
	public void setCpSale(Float value) {
		this.cpSale = value;
	}

	public Float getCpSale() {
		return this.cpSale;
	}
	public void setCpInventory(Long value) {
		this.cpInventory = value;
	}

	public Long getCpInventory() {
		return this.cpInventory;
	}
	public void setCpBounds(Long value) {
		this.cpBounds = value;
	}

	public Long getCpBounds() {
		return this.cpBounds;
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
