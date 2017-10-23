package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 描述：楼层广告设置对象
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-21 15:15:44
 */
public class FloorAdvertising implements java.io.Serializable {

	/**faId*/
	@NotEmpty
	private Long faId;
	/**父类ID*/
	@NotEmpty
	private Long parentId;
	/**上传图片返回的ID*/
	@NotEmpty
	private Long imageId;
	/**图片的链接*/
	@NotEmpty
	@Length(min = 1, max = 500)
	private String url;
	/**楼层广告名称*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String faName;
	/**排序*/
	@NotEmpty
	private Long faOrder;
	/**推送对象*/
	@NotEmpty
	private Long faCountry;
	/**推送客户端*/
	@NotEmpty
	private Long faClient;
	/**是否显示*/
	@NotEmpty
	private Long isShow;
	/**是否删除*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public FloorAdvertising(){
	}

	public FloorAdvertising( Long faId){
		this.faId = faId;
	}

	public void setFaId(Long value) {
		this.faId = value;
	}

	public Long getFaId() {
		return this.faId;
	}
	public void setParentId(Long value) {
		this.parentId = value;
	}

	public Long getParentId() {
		return this.parentId;
	}
	public void setImageId(Long value) {
		this.imageId = value;
	}

	public Long getImageId() {
		return this.imageId;
	}
	public void setUrl(String value) {
		this.url = value;
	}

	public String getUrl() {
		return this.url;
	}
	public void setFaName(String value) {
		this.faName = value;
	}

	public String getFaName() {
		return this.faName;
	}
	public void setFaOrder(Long value) {
		this.faOrder = value;
	}

	public Long getFaOrder() {
		return this.faOrder;
	}
	public void setFaCountry(Long value) {
		this.faCountry = value;
	}

	public Long getFaCountry() {
		return this.faCountry;
	}
	public void setFaClient(Long value) {
		this.faClient = value;
	}

	public Long getFaClient() {
		return this.faClient;
	}
	public void setIsShow(Long value) {
		this.isShow = value;
	}

	public Long getIsShow() {
		return this.isShow;
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
