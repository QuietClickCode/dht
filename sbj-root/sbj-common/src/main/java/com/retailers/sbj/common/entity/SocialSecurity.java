package com.retailers.sbj.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：模拟社保卡信息绑定数据表对象
 * @author boylin
 * @version 1.0
 * @since 1.8
 * @date 2018-03-02 15:50:28
 */
public class SocialSecurity implements java.io.Serializable {

	/**id*/
	@NotEmpty
	private java.lang.Long id;
	/**社保卡用户名称*/
	@NotEmpty
	@Length(min = 1, max = 100)
	private java.lang.String name;
	/**身份证号*/
	@NotEmpty
	@Length(min = 1, max = 19)
	private java.lang.String idCard;
	/**社会保障卡号*/
	@NotEmpty
	@Length(min = 1, max = 30)
	private java.lang.String ssCard;
	/**手机号*/
	@NotEmpty
	@Length(min = 1, max = 11)
	private java.lang.String tel;
	//columns END

	public SocialSecurity(){
	}

	public SocialSecurity( java.lang.Long id){
		this.id = id;
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}

	public java.lang.Long getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}

	public java.lang.String getName() {
		return this.name;
	}
	public void setIdCard(java.lang.String value) {
		this.idCard = value;
	}

	public java.lang.String getIdCard() {
		return this.idCard;
	}
	public void setSsCard(java.lang.String value) {
		this.ssCard = value;
	}

	public java.lang.String getSsCard() {
		return this.ssCard;
	}
	public void setTel(java.lang.String value) {
		this.tel = value;
	}

	public java.lang.String getTel() {
		return this.tel;
	}


}
