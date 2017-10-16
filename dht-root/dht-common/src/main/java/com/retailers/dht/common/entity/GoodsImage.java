package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品图片表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-15 11:48:27
 */
public class GoodsImage implements java.io.Serializable {

	/**商品图片ID*/
	@NotEmpty
	private Long giId;
	/**商品ID*/
	@NotEmpty
	private Long gid;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsImage(){
	}

	public GoodsImage( Long giId){
		this.giId = giId;
	}

	public void setGiId(Long value) {
		this.giId = value;
	}

	public Long getGiId() {
		return this.giId;
	}
	public void setGid(Long value) {
		this.gid = value;
	}

	public Long getGid() {
		return this.gid;
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
