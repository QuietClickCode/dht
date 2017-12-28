package com.retailers.wx.common.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * 描述：公众号管理对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-30 23:18:07
 */
public class WxManager implements java.io.Serializable {

	/**微信管理id*/
	private Long wxId;
	/**
	 * 微信公众号图片地址
	 */
	private Long wxQrCode;
	/**微信号*/
	@Length(min = 1, max = 64)
	private String wxName;
	/**微信原始id*/
	@NotEmpty(message = "微信原始id不能为空")
	@Length(min = 1, max = 128,message = "微信原始id长度在{min}-{max}之间")
	private String wxOriginalId;
	/**公众号类型*/
	private String wxType;
	/**公众号id（与app_secret 使用取得公众号token)*/
	@NotEmpty(message = "公众号APPID不能为空")
	@Length(min = 1, max = 64)
	private String appId;
	/**公众号秘钥（与app_id 使用取得公众号token)*/
	@NotEmpty(message = "公众号秘钥不能为空")
	@Length(min = 1, max = 64,message = "公众号秘钥长度在{min}-{max}之间")
	private String appSecret;
	/**公众号权限域名*/
	private String wxDomainName;
	/**回调地址*/
	private String wxDomainUrl;
	/**微信token*/
	private String wxToken;
	/**创建人*/
	private Long createUid;
	/**创建时间*/
	private Date createTime;
	/**是否删除（0 未删除，1 己删除）*/
	private Integer isDelete;
	/**是否有效（0 有效，1 失效）*/
	private Integer isValid;
	/**数据乐观锁*/
	private Integer version;
	//columns END

	public WxManager(){
	}

	public WxManager( Long wxId){
		this.wxId = wxId;
	}

	public void setWxId(Long value) {
		this.wxId = value;
	}

	public Long getWxId() {
		return this.wxId;
	}
	public void setWxName(String value) {
		this.wxName = value;
	}

	public String getWxName() {
		return this.wxName;
	}
	public void setWxOriginalId(String value) {
		this.wxOriginalId = value;
	}

	public String getWxOriginalId() {
		return this.wxOriginalId;
	}

	public String getWxType() {
		return wxType;
	}

	public void setWxType(String wxType) {
		this.wxType = wxType;
	}

	public void setAppId(String value) {
		this.appId = value;
	}

	public String getAppId() {
		return this.appId;
	}
	public void setAppSecret(String value) {
		this.appSecret = value;
	}

	public String getAppSecret() {
		return this.appSecret;
	}
	public void setWxDomainName(String value) {
		this.wxDomainName = value;
	}

	public String getWxDomainName() {
		return this.wxDomainName;
	}
	public void setWxDomainUrl(String value) {
		this.wxDomainUrl = value;
	}

	public String getWxDomainUrl() {
		return this.wxDomainUrl;
	}
	public void setWxToken(String value) {
		this.wxToken = value;
	}

	public String getWxToken() {
		return this.wxToken;
	}
	public void setCreateUid(Long value) {
		this.createUid = value;
	}

	public Long getCreateUid() {
		return this.createUid;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}
	public void setIsDelete(Integer value) {
		this.isDelete = value;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}
	public void setIsValid(Integer value) {
		this.isValid = value;
	}

	public Integer getIsValid() {
		return this.isValid;
	}
	public void setVersion(Integer value) {
		this.version = value;
	}

	public Integer getVersion() {
		return this.version;
	}

	public Long getWxQrCode() {
		return wxQrCode;
	}

	public void setWxQrCode(Long wxQrCode) {
		this.wxQrCode = wxQrCode;
	}
}
