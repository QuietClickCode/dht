package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品图片副本表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-17 14:25:01
 */
public class GoodsImageCopy implements java.io.Serializable {

	/**商品图片备份ID*/
	@NotEmpty
	private Long gicId;
	/**商品图片ID*/
	@NotEmpty
	private Long giId;
	/**操作人*/
	@NotEmpty
	private Long giUploadpersonid;
	/**创建时间*/
	@NotEmpty
	private Date creattime;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsImageCopy(){
	}

	public GoodsImageCopy( Long gicId){
		this.gicId = gicId;
	}

	public void setGicId(Long value) {
		this.gicId = value;
	}

	public Long getGicId() {
		return this.gicId;
	}
	public void setGiId(Long value) {
		this.giId = value;
	}

	public Long getGiId() {
		return this.giId;
	}
	public void setGiUploadpersonid(Long value) {
		this.giUploadpersonid = value;
	}

	public Long getGiUploadpersonid() {
		return this.giUploadpersonid;
	}
	public void setCreattime(Date value) {
		this.creattime = value;
	}

	public Date getCreattime() {
		return this.creattime;
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
