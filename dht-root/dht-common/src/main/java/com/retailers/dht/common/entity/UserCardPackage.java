package com.retailers.dht.common.entity;
import com.alibaba.fastjson.annotation.JSONField;
import com.retailers.tools.utils.NumberUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：用户钱包，积分对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-22 23:13:08
 */
public class UserCardPackage implements java.io.Serializable {

	/**id*/
	@NotEmpty
	private Long id;
	/**充值总金额*/
	@NotEmpty
	@JSONField(serialize = false)
	private Long utotalWallet;
	/**当前可用金额*/
	@NotEmpty
	@JSONField(serialize = false)
	private Long ucurWallet;
	/**总积分*/
	@NotEmpty
	@JSONField(serialize = false)
	private Long utotalIntegral;
	/**当前可用积分*/
	@NotEmpty
	private Long ucurIntegral;
	/**version*/
	@NotEmpty
	private Integer version;
	//columns END

	public UserCardPackage(){
	}

	public UserCardPackage( Long id){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}
	public void setUtotalWallet(Long value) {
		this.utotalWallet = value;
	}

	public Long getUtotalWallet() {
		return this.utotalWallet;
	}
	public void setUcurWallet(Long value) {
		this.ucurWallet = value;
	}

	public Long getUcurWallet() {
		return this.ucurWallet;
	}
	public void setUtotalIntegral(Long value) {
		this.utotalIntegral = value;
	}

	public Long getUtotalIntegral() {
		return this.utotalIntegral;
	}
	public void setUcurIntegral(Long value) {
		this.ucurIntegral = value;
	}

	public Long getUcurIntegral() {
		return this.ucurIntegral;
	}
	public void setVersion(Integer value) {
		this.version = value;
	}

	public Integer getVersion() {
		return this.version;
	}
	//前端钱包格式化
	public String getUcurWalletStr(){
		return NumberUtils.formaterNumberPower(ucurWallet);
	}

}
