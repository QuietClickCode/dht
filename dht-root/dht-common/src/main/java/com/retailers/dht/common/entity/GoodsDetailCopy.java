package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品详情副本表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-13 13:28:42
 */
public class GoodsDetailCopy implements java.io.Serializable {

	/**gdcId*/
	@NotEmpty
	private Long gdcId;
	/**商品详情ID*/
	@NotEmpty
	private Long gdId;
	/**商品Id*/
	@NotEmpty
	private Long gid;
	/**该商品该规格的销售价格*/
	@NotEmpty
	private Long gdPrice;
	/**成本价*/
	@NotEmpty
	private Long gdCostprice;
	/**总库存*/
	@NotEmpty
	private Long gdInventory;
	/**剩余库存*/
	@NotEmpty
	private Long gdResidueinventory;
	/**图片ID*/
	@NotEmpty
	private Long gdImgid;
	/**操作人id*/
	@NotEmpty
	private Long gdUploadpersionId;
	/**操作时间*/
	@NotEmpty
	private Date createTime;
	/**是否删除（0代表未删除，1代表已删除）*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsDetailCopy(){
	}

	public GoodsDetailCopy( Long gdcId){
		this.gdcId = gdcId;
	}

	public void setGdcId(Long value) {
		this.gdcId = value;
	}

	public Long getGdcId() {
		return this.gdcId;
	}
	public void setGdId(Long value) {
		this.gdId = value;
	}

	public Long getGdId() {
		return this.gdId;
	}
	public void setGid(Long value) {
		this.gid = value;
	}

	public Long getGid() {
		return this.gid;
	}
	public void setGdPrice(Long value) {
		this.gdPrice = value;
	}

	public Long getGdPrice() {
		return this.gdPrice;
	}
	public void setGdCostprice(Long value) {
		this.gdCostprice = value;
	}

	public Long getGdCostprice() {
		return this.gdCostprice;
	}
	public void setGdInventory(Long value) {
		this.gdInventory = value;
	}

	public Long getGdInventory() {
		return this.gdInventory;
	}
	public void setGdResidueinventory(Long value) {
		this.gdResidueinventory = value;
	}

	public Long getGdResidueinventory() {
		return this.gdResidueinventory;
	}
	public void setGdImgid(Long value) {
		this.gdImgid = value;
	}

	public Long getGdImgid() {
		return this.gdImgid;
	}
	public void setGdUploadpersionId(Long value) {
		this.gdUploadpersionId = value;
	}

	public Long getGdUploadpersionId() {
		return this.gdUploadpersionId;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
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
