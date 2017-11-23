package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：特价广告表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-23 10:19:14
 */
public class SecspepAdvertising implements java.io.Serializable {

	/**广告id*/
	@NotEmpty
	private Long saId;
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
	private String saName;
	/**排序*/
	@NotEmpty
	private Long saOrder;
	/**推送客户端 0移动 1PC 2小程序*/
	@NotEmpty
	private Long saClient;
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

	public SecspepAdvertising(){
	}

	public SecspepAdvertising( Long saId){
		this.saId = saId;
	}

	public void setSaId(Long value) {
		this.saId = value;
	}

	public Long getSaId() {
		return this.saId;
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
	public void setSaName(String value) {
		this.saName = value;
	}

	public String getSaName() {
		return this.saName;
	}
	public void setSaOrder(Long value) {
		this.saOrder = value;
	}

	public Long getSaOrder() {
		return this.saOrder;
	}
	public void setSaClient(Long value) {
		this.saClient = value;
	}

	public Long getSaClient() {
		return this.saClient;
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
