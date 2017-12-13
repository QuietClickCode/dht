package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：砍价日志表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-13 11:51:48
 */
public class CutPriceLog implements java.io.Serializable {

	/**砍价日志id*/
	@NotEmpty
	private Long cplId;
	/**商品详情与砍价关系id*/
	@NotEmpty
	private Long gdcpId;
	/**分享用户id*/
	@NotEmpty
	private Long usId;
	/**被邀请砍价用户id*/
	@NotEmpty
	private Long usdId;
	/**降价*/
	@NotEmpty
	private Long cplCutdownprice;
	/**是否删除 0不删除  1删除*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public CutPriceLog(){
	}

	public CutPriceLog( Long cplId){
		this.cplId = cplId;
	}

	public void setCplId(Long value) {
		this.cplId = value;
	}

	public Long getCplId() {
		return this.cplId;
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
	public void setUsdId(Long value) {
		this.usdId = value;
	}

	public Long getUsdId() {
		return this.usdId;
	}
	public void setCplCutdownprice(Long value) {
		this.cplCutdownprice = value;
	}

	public Long getCplCutdownprice() {
		return this.cplCutdownprice;
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
