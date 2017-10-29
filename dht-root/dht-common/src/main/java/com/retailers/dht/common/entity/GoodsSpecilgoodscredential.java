package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：特殊商品证件表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-26 17:15:12
 */
public class GoodsSpecilgoodscredential implements java.io.Serializable {

	/**特殊商品证件Id*/
	@NotEmpty
	private Long gsgId;
	/**商品ID*/
	@NotEmpty
	private Long gid;
	/**证件类型 0编号 1图片*/
	@NotEmpty
	private Long gsgType;
	/**商品编号*/
	@NotEmpty
	@Length(min = 1, max = 100)
	private String gsgNumber;
	/**图片ID*/
	@NotEmpty
	private Long gsgImgid;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsSpecilgoodscredential(){
	}

	public GoodsSpecilgoodscredential( Long gsgId){
		this.gsgId = gsgId;
	}

	public void setGsgId(Long value) {
		this.gsgId = value;
	}

	public Long getGsgId() {
		return this.gsgId;
	}
	public void setGid(Long value) {
		this.gid = value;
	}

	public Long getGid() {
		return this.gid;
	}
	public void setGsgType(Long value) {
		this.gsgType = value;
	}

	public Long getGsgType() {
		return this.gsgType;
	}
	public void setGsgNumber(String value) {
		this.gsgNumber = value;
	}

	public String getGsgNumber() {
		return this.gsgNumber;
	}
	public void setGsgImgid(Long value) {
		this.gsgImgid = value;
	}

	public Long getGsgImgid() {
		return this.gsgImgid;
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
