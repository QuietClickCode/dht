package com.retailers.wx.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：微信支付设置对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-28 00:26:12
 */
public class WxPay implements java.io.Serializable {

	/**wxId*/
	private Long wxId;
	/**微信商户号*/
	@NotEmpty(message="商户号不能为空")
	@Length(min = 1, max = 256,message = "商户号长度只能在{min}-{max}之间")
	private String wxMchId;
	/**微信appid*/
	@Length(min = 1, max = 50)
	private String wxAppId;
	/**微信商户秘钥*/
	@NotEmpty(message="商户号商户秘钥")
	@Length(min = 1, max = 64,message = "商户号商户秘钥长度只能在{min}-{max}之间")
	private String wxApiKey;
	/**证书编码*/
	private Long wxCertificateCode;
	/**本地缓存证书地址*/
	private String wxLocalCertificateCodeAddr;
	/**创建时间*/
	private Date createTime;
	/**创建人*/
	private Long createUid;
	/**是否删除（0 未删，1 己删）*/
	private Integer isDelete;
	/**version*/
	private Integer version;
	//columns END

	public WxPay(){
	}

	public WxPay( Long wxId){
		this.wxId = wxId;
	}

	public void setWxId(Long value) {
		this.wxId = value;
	}

	public Long getWxId() {
		return this.wxId;
	}
	public void setWxMchId(String value) {
		this.wxMchId = value;
	}

	public String getWxMchId() {
		return this.wxMchId;
	}
	public void setWxAppId(String value) {
		this.wxAppId = value;
	}

	public String getWxAppId() {
		return this.wxAppId;
	}
	public void setWxApiKey(String value) {
		this.wxApiKey = value;
	}

	public String getWxApiKey() {
		return this.wxApiKey;
	}
	public void setWxCertificateCode(Long value) {
		this.wxCertificateCode = value;
	}

	public Long getWxCertificateCode() {
		return this.wxCertificateCode;
	}
	public void setWxLocalCertificateCodeAddr(String value) {
		this.wxLocalCertificateCodeAddr = value;
	}

	public String getWxLocalCertificateCodeAddr() {
		return this.wxLocalCertificateCodeAddr;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateUid(Long value) {
		this.createUid = value;
	}

	public Long getCreateUid() {
		return this.createUid;
	}
	public void setIsDelete(Integer value) {
		this.isDelete = value;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}
	public void setVersion(Integer value) {
		this.version = value;
	}

	public Integer getVersion() {
		return this.version;
	}


}
