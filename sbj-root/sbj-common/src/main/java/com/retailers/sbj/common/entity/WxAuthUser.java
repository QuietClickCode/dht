package com.retailers.sbj.common.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * 描述：微信用户表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-25 16:08:40
 */
public class WxAuthUser implements java.io.Serializable {

	/**wauId*/
	@NotEmpty
	private Long wauId;
	/**获取用户信息的微信id*/
	@NotEmpty
	private Long wauWxId;
	/**关联用户id*/
	@NotEmpty
	private Long wauUid;
	/**微信用户id（同一个公众号下同一用户唯一）*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String wauOpenid;
	/**微信昵称*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String wauNickname;
	/**性别*/
	@NotEmpty
	private Integer wauSex;
	/**拉取语颜*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String wauLanguage;
	/**所在城市*/
	@NotEmpty
	@Length(min = 1, max = 128)
	private String wauCity;
	/**所在省份*/
	@NotEmpty
	@Length(min = 1, max = 128)
	private String wauProvince;
	/**所在国家*/
	@NotEmpty
	@Length(min = 1, max = 128)
	private String wauCountry;
	/**用户头像*/
	@NotEmpty
	@Length(min = 1, max = 500)
	private String wauHeadimgurl;
	/**微信用户的uuid（同一服务号下的微信用户uuid 一至）*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String wauUnionid;
	/**创建时间*/
	@NotEmpty
	private Date wauCreateDate;
	/**推荐人id*/
	@NotEmpty
	private Long wauRefereeId;
	/**数据乐观锁*/
	@NotEmpty
	private Integer version;
	//columns END

	public WxAuthUser(){
	}

	public WxAuthUser( Long wauId){
		this.wauId = wauId;
	}

	public void setWauId(Long value) {
		this.wauId = value;
	}

	public Long getWauId() {
		return this.wauId;
	}
	public void setWauWxId(Long value) {
		this.wauWxId = value;
	}

	public Long getWauWxId() {
		return this.wauWxId;
	}
	public void setWauUid(Long value) {
		this.wauUid = value;
	}

	public Long getWauUid() {
		return this.wauUid;
	}
	public void setWauOpenid(String value) {
		this.wauOpenid = value;
	}

	public String getWauOpenid() {
		return this.wauOpenid;
	}
	public void setWauNickname(String value) {
		this.wauNickname = value;
	}

	public String getWauNickname() {
		return this.wauNickname;
	}
	public void setWauSex(Integer value) {
		this.wauSex = value;
	}

	public Integer getWauSex() {
		return this.wauSex;
	}
	public void setWauLanguage(String value) {
		this.wauLanguage = value;
	}

	public String getWauLanguage() {
		return this.wauLanguage;
	}
	public void setWauCity(String value) {
		this.wauCity = value;
	}

	public String getWauCity() {
		return this.wauCity;
	}
	public void setWauProvince(String value) {
		this.wauProvince = value;
	}

	public String getWauProvince() {
		return this.wauProvince;
	}
	public void setWauCountry(String value) {
		this.wauCountry = value;
	}

	public String getWauCountry() {
		return this.wauCountry;
	}
	public void setWauHeadimgurl(String value) {
		this.wauHeadimgurl = value;
	}

	public String getWauHeadimgurl() {
		return this.wauHeadimgurl;
	}
	public void setWauUnionid(String value) {
		this.wauUnionid = value;
	}

	public String getWauUnionid() {
		return this.wauUnionid;
	}
	public void setWauCreateDate(Date value) {
		this.wauCreateDate = value;
	}

	public Date getWauCreateDate() {
		return this.wauCreateDate;
	}
	public void setWauRefereeId(Long value) {
		this.wauRefereeId = value;
	}

	public Long getWauRefereeId() {
		return this.wauRefereeId;
	}
	public void setVersion(Integer value) {
		this.version = value;
	}

	public Integer getVersion() {
		return this.version;
	}


}
