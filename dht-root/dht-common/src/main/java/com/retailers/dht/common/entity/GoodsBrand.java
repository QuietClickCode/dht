package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品品牌表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-07 09:42:29
 */
public class GoodsBrand implements java.io.Serializable {

	/**品牌ID*/
	@NotEmpty
	private Long gbId;
	/**品牌名称*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String gbName;
	/**gbImgpath*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String gbImgpath;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsBrand(){
	}

	public GoodsBrand( Long gbId){
		this.gbId = gbId;
	}

	public void setGbId(Long value) {
		this.gbId = value;
	}

	public Long getGbId() {
		return this.gbId;
	}
	public void setGbName(String value) {
		this.gbName = value;
	}

	public String getGbName() {
		return this.gbName;
	}
	public void setGbImgpath(String value) {
		this.gbImgpath = value;
	}

	public String getGbImgpath() {
		return this.gbImgpath;
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
