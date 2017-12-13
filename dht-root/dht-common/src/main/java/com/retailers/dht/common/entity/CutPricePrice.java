package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：砍价价格初始化表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-13 11:46:52
 */
public class CutPricePrice implements java.io.Serializable {

	/**砍价价格初始化id*/
	@NotEmpty
	private Long cppId;
	/**商品详情与砍价关系id*/
	@NotEmpty
	private Long gdcpId;
	/**分享用户id*/
	@NotEmpty
	private Long usId;
	/**砍掉的价格*/
	@NotEmpty
	private Long cppPrice;
	/**商品件数*/
	@NotEmpty
	private Long gcount;
	/**是否删除 0不删除 1删除*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public CutPricePrice(){
	}

	public CutPricePrice( Long cppId){
		this.cppId = cppId;
	}

	public void setCppId(Long value) {
		this.cppId = value;
	}

	public Long getCppId() {
		return this.cppId;
	}
	public void setGdcpId(Long value) {
		this.gdcpId = value;
	}

	public Long getGdcpId() {
		return this.gdcpId;
	}
	public void setUsId(Long value) {
		this.usId = value;
	}

	public Long getUsId() {
		return this.usId;
	}
	public void setCppPrice(Long value) {
		this.cppPrice = value;
	}

	public Long getCppPrice() {
		return this.cppPrice;
	}
	public void setGcount(Long value) {
		this.gcount = value;
	}

	public Long getGcount() {
		return this.gcount;
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
