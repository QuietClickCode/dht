package com.retailers.wx.common.entity;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * 描述：微信认证token对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-31 22:05:24
 */
public class WxAccessToken implements java.io.Serializable {

	/**watId*/
	@NotEmpty
	private Long watId;
	/**关联微信id*/
	@NotEmpty
	private String watWxAppId;
	/**access_token是公众号的全局唯一接口调用凭据*/
	@NotEmpty
	private String watToken;
	/**
	 * js-sdk token取得
	 */
	private String watTicket;
	/**申请时间*/
	@NotEmpty
	private Date watTokenCreateTime;
	/**失效时间*/
	@NotEmpty
	private Date watTokenExpiresTime;
	/**token 有效时间（一般是7200秒，失效时间会提前五分钟）*/
	@NotEmpty
	private Integer watTokenExpires;
	//columns END

	public WxAccessToken(){
	}

	public WxAccessToken( Long watId){
		this.watId = watId;
	}

	public void setWatId(Long value) {
		this.watId = value;
	}

	public Long getWatId() {
		return this.watId;
	}

	public String getWatWxAppId() {
		return watWxAppId;
	}

	public void setWatWxAppId(String watWxAppId) {
		this.watWxAppId = watWxAppId;
	}

	public String getWatToken() {
		return watToken;
	}

	public void setWatToken(String watToken) {
		this.watToken = watToken;
	}

	public void setWatTokenCreateTime(Date value) {
		this.watTokenCreateTime = value;
	}

	public Date getWatTokenCreateTime() {
		return this.watTokenCreateTime;
	}
	public void setWatTokenExpiresTime(Date value) {
		this.watTokenExpiresTime = value;
	}

	public Date getWatTokenExpiresTime() {
		return this.watTokenExpiresTime;
	}
	public void setWatTokenExpires(Integer value) {
		this.watTokenExpires = value;
	}

	public Integer getWatTokenExpires() {
		return this.watTokenExpires;
	}

	public String getWatTicket() {
		return watTicket;
	}

	public void setWatTicket(String watTicket) {
		this.watTicket = watTicket;
	}
}
