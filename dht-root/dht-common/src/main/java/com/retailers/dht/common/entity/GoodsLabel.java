package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品标签表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-07 12:03:22
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
	public void setVersion(Long value) {
		this.version = value;
	}

	public Long getVersion() {
		return this.version;
	}


}
