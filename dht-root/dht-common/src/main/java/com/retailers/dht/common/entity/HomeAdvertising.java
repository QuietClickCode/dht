package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 描述：首页广告设置表对象
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-20 17:07:09
 */
public class HomeAdvertising implements java.io.Serializable {

	/**haId*/
	@NotEmpty
	private Long haId;
	/**广告名称*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String haName;
	/**排序*/
	@NotEmpty
	private Long haOrder;
	/**是否显示（1代表显示，0代表隐藏，默认为1）*/
	@NotEmpty
	private Long isShow;
	/**是否删除（0代表未删除，1代表已删除，默认为0）*/
	@NotEmpty
	private Long isDelete;
	/**广告的链接*/
	@NotEmpty
	@Length(min = 1, max = 500)
	private String url;
	/**图片地址*/
	@NotEmpty
	private Long imagePath;
	/**选择推送的客户端（0代表移动端，1代表PC端，2代表小程序，默认为0）*/
	@NotEmpty
	private Long haClient;
	/**推送的对象（0代表乡村，1代表城市）*/
	@NotEmpty
	private Long haCountry;
	/**选择推送广告的类型（0代表向上区域的广告，1代表中间区域的广告，2代表底部区域的广告）*/
	@NotEmpty
	private Long haRegion;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public HomeAdvertising(){
	}

	public HomeAdvertising( Long haId){
		this.haId = haId;
	}

	public void setHaId(Long value) {
		this.haId = value;
	}

	public Long getHaId() {
		return this.haId;
	}
	public void setHaName(String value) {
		this.haName = value;
	}

	public String getHaName() {
		return this.haName;
	}
	public void setHaOrder(Long value) {
		this.haOrder = value;
	}

	public Long getHaOrder() {
		return this.haOrder;
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
	public void setUrl(String value) {
		this.url = value;
	}

	public String getUrl() {
		return this.url;
	}
	public void setImagePath(Long value) {
		this.imagePath = value;
	}

	public Long getImagePath() {
		return this.imagePath;
	}
	public void setHaClient(Long value) {
		this.haClient = value;
	}

	public Long getHaClient() {
		return this.haClient;
	}
	public void setHaCountry(Long value) {
		this.haCountry = value;
	}

	public Long getHaCountry() {
		return this.haCountry;
	}
	public void setHaRegion(Long value) {
		this.haRegion = value;
	}

	public Long getHaRegion() {
		return this.haRegion;
	}
	public void setVersion(Long value) {
		this.version = value;
	}

	public Long getVersion() {
		return this.version;
	}


}
