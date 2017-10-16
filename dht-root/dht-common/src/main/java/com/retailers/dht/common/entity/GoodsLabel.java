package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品图片表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-15 14:16:22
 */
public class GoodsLabel implements java.io.Serializable {

	/**标签ID*/
	@NotEmpty
	private Long glId;
	/**商品标签名称*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String glName;
	/**开始时间*/
	@NotEmpty
	private Date glStarttime;
	/**结束时间*/
	@NotEmpty
	private Date glEndtime;
	/**是否是商品标签 0 不是 1 是*/
	@NotEmpty
	private Long isGoodslabel;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsLabel(){
	}

	public GoodsLabel( Long glId){
		this.glId = glId;
	}

	public void setGlId(Long value) {
		this.glId = value;
	}

	public Long getGlId() {
		return this.glId;
	}
	public void setGlName(String value) {
		this.glName = value;
	}

	public String getGlName() {
		return this.glName;
	}
	public void setGlStarttime(Date value) {
		this.glStarttime = value;
	}

	public Date getGlStarttime() {
		return this.glStarttime;
	}
	public void setGlEndtime(Date value) {
		this.glEndtime = value;
	}

	public Date getGlEndtime() {
		return this.glEndtime;
	}
	public void setIsGoodslabel(Long value) {
		this.isGoodslabel = value;
	}

	public Long getIsGoodslabel() {
		return this.isGoodslabel;
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
