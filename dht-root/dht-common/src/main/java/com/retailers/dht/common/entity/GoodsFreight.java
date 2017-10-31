package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品运费表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-29 10:39:17
 */
public class GoodsFreight implements java.io.Serializable {

	/**gfId*/
	@NotEmpty
	private Long gfId;
	/**运费名称*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String gfName;
	/**运费价格*/
	@NotEmpty
	private Float gfPrice;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsFreight(){
	}

	public GoodsFreight( Long gfId){
		this.gfId = gfId;
	}

	public void setGfId(Long value) {
		this.gfId = value;
	}

	public Long getGfId() {
		return this.gfId;
	}
	public void setGfName(String value) {
		this.gfName = value;
	}

	public String getGfName() {
		return this.gfName;
	}
	public void setGfPrice(Float value) {
		this.gfPrice = value;
	}

	public Float getGfPrice() {
		return this.gfPrice;
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
