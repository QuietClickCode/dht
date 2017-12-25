package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：目前平台销售情况对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-25 01:07:51
 */
public class CurrentPlatformSales implements java.io.Serializable {

	/**cpsId*/
	@NotEmpty
	private Long cpsId;
	/**支付类型（0 直接返现，1消费累计返现）*/
	@NotEmpty
	private Long cpsPayType;
	/**购买商品类型（顶层类型）*/
	@NotEmpty
	private Long cpsGoodsMainType;
	/**当前 销售总金额(该 商品类型）*/
	@NotEmpty
	private Long cpsTotalPrice;
	/**己返现金额*/
	@NotEmpty
	private Long cpsCashbackPrice;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public CurrentPlatformSales(){
	}

	public CurrentPlatformSales( Long cpsId){
		this.cpsId = cpsId;
	}

	public void setCpsId(Long value) {
		this.cpsId = value;
	}

	public Long getCpsId() {
		return this.cpsId;
	}
	public void setCpsPayType(Long value) {
		this.cpsPayType = value;
	}

	public Long getCpsPayType() {
		return this.cpsPayType;
	}
	public void setCpsGoodsMainType(Long value) {
		this.cpsGoodsMainType = value;
	}

	public Long getCpsGoodsMainType() {
		return this.cpsGoodsMainType;
	}
	public void setCpsTotalPrice(Long value) {
		this.cpsTotalPrice = value;
	}

	public Long getCpsTotalPrice() {
		return this.cpsTotalPrice;
	}
	public void setCpsCashbackPrice(Long value) {
		this.cpsCashbackPrice = value;
	}

	public Long getCpsCashbackPrice() {
		return this.cpsCashbackPrice;
	}
	public void setVersion(Long value) {
		this.version = value;
	}

	public Long getVersion() {
		return this.version;
	}


}
