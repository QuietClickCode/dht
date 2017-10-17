package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 描述：主页导航表对象
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-17 14:58:24
 */
public class HomeNavigation implements java.io.Serializable {

	/**主键*/
	@NotEmpty
	private Long hnId;
	/**hnName*/
	@NotEmpty
	@Length(min = 1, max = 20)
	private String hnName;
	/**主标题*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String hnMianTitle;
	/**副标题*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String hnSubTitle;
	/**选择样式（0代表无副标题的样式，1代表有副标题的样式）*/
	@NotEmpty
	private Long hnStyle;
	/**不同的客户端（0代表移动端，1代表PC端，2代表小程序，默认为0）*/
	@NotEmpty
	private Long hnClient;
	/**图片外链接*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String hnUrl;
	/**图片路径*/
	@NotEmpty
	private Long hnImgpath;
	/**是否删除（0代表不删除，1代表删除，默认为0）*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	/**是否显示（1表示显示，0表示不显示，默认为1）*/
	@NotEmpty
	private Long isShow;
	/**排序*/
	@NotEmpty
	private Long hnOrder;
	/**选择推送对象（农村还是城市）0代表农村，1代表城市*/
	@NotEmpty
	private Long hnCountry;
	//columns END

	public HomeNavigation(){
	}

	public HomeNavigation( Long hnId){
		this.hnId = hnId;
	}

	public void setHnId(Long value) {
		this.hnId = value;
	}

	public Long getHnId() {
		return this.hnId;
	}
	public void setHnName(String value) {
		this.hnName = value;
	}

	public String getHnName() {
		return this.hnName;
	}
	public void setHnMianTitle(String value) {
		this.hnMianTitle = value;
	}

	public String getHnMianTitle() {
		return this.hnMianTitle;
	}
	public void setHnSubTitle(String value) {
		this.hnSubTitle = value;
	}

	public String getHnSubTitle() {
		return this.hnSubTitle;
	}
	public void setHnStyle(Long value) {
		this.hnStyle = value;
	}

	public Long getHnStyle() {
		return this.hnStyle;
	}
	public void setHnClient(Long value) {
		this.hnClient = value;
	}

	public Long getHnClient() {
		return this.hnClient;
	}
	public void setHnUrl(String value) {
		this.hnUrl = value;
	}

	public String getHnUrl() {
		return this.hnUrl;
	}
	public void setHnImgpath(Long value) {
		this.hnImgpath = value;
	}

	public Long getHnImgpath() {
		return this.hnImgpath;
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
	public void setIsShow(Long value) {
		this.isShow = value;
	}

	public Long getIsShow() {
		return this.isShow;
	}
	public void setHnOrder(Long value) {
		this.hnOrder = value;
	}

	public Long getHnOrder() {
		return this.hnOrder;
	}
	public void setHnCountry(Long value) {
		this.hnCountry = value;
	}

	public Long getHnCountry() {
		return this.hnCountry;
	}


}
