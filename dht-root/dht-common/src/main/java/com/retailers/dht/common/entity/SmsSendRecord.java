package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：短信发送列表对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-23 23:12:58
 */
public class SmsSendRecord implements java.io.Serializable {

	/**id*/
	@NotEmpty
	private Long id;
	/**发送短信用户*/
	@NotEmpty
	private Long uid;
	/**发送类型（0 绑定手机号码，1 密码找回，2 动态密码登录）*/
	@NotEmpty
	private Integer type;
	/**短信状态（0 未使用，1 己使用）*/
	@NotEmpty
	private Integer status;
	/**手机号码*/
	@NotEmpty
	@Length(min = 1, max = 15)
	private String phone;
	/**随机code*/
	@NotEmpty
	@Length(min = 1, max = 10)
	private String code;
	/**短信内容*/
	@NotEmpty
	@Length(min = 1, max = 500)
	private String context;
	/**创建时间*/
	@NotEmpty
	private Date createDate;
	/**同一类型再次 发送时间*/
	@NotEmpty
	private Date onceSendDate;
	/**验证码失效时间*/
	@NotEmpty
	private Date expireDate;
	/**使用时间*/
	@NotEmpty
	private Date useDate;
	//columns END

	public SmsSendRecord(){
	}

	public SmsSendRecord( Long id){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}
	public void setUid(Long value) {
		this.uid = value;
	}

	public Long getUid() {
		return this.uid;
	}
	public void setType(Integer value) {
		this.type = value;
	}

	public Integer getType() {
		return this.type;
	}
	public void setStatus(Integer value) {
		this.status = value;
	}

	public Integer getStatus() {
		return this.status;
	}
	public void setPhone(String value) {
		this.phone = value;
	}

	public String getPhone() {
		return this.phone;
	}
	public void setCode(String value) {
		this.code = value;
	}

	public String getCode() {
		return this.code;
	}
	public void setContext(String value) {
		this.context = value;
	}

	public String getContext() {
		return this.context;
	}
	public void setCreateDate(Date value) {
		this.createDate = value;
	}

	public Date getCreateDate() {
		return this.createDate;
	}
	public void setOnceSendDate(Date value) {
		this.onceSendDate = value;
	}

	public Date getOnceSendDate() {
		return this.onceSendDate;
	}
	public void setExpireDate(Date value) {
		this.expireDate = value;
	}

	public Date getExpireDate() {
		return this.expireDate;
	}
	public void setUseDate(Date value) {
		this.useDate = value;
	}

	public Date getUseDate() {
		return this.useDate;
	}


}
