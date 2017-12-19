package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：用户钱包/第三方总消费/剩余返现金额/钱包消费/消费对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-20 01:16:42
 */
public class UserCardPackage implements java.io.Serializable {

	/**id*/
	@NotEmpty
	private Long id;
	/**充值总金额*/
	@NotEmpty
	private Long utotalWallet;
	/**当前可用金额*/
	@NotEmpty
	private Long ucurWallet;
	/**第三方支付消费总额*/
	@NotEmpty
	private Long utotalIntegral;
	/**第三方剩余消费（满20000 返2000之后减掉20000）*/
	@NotEmpty
	private Long ucurIntegral;
	/**总消费(第三方消费+钱包消费）*/
	@NotEmpty
	private Long utotalConsume;
	/**钱包总消费*/
	@NotEmpty
	private Long uwalletConsumeTotal;
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
	public void setUtotalConsume(Long value) {
		this.utotalConsume = value;
	}

	public Long getUtotalConsume() {
		return this.utotalConsume;
	}
	public void setUwalletConsumeTotal(Long value) {
		this.uwalletConsumeTotal = value;
	}

	public Long getUwalletConsumeTotal() {
		return this.uwalletConsumeTotal;
	}
	public void setVersion(Integer value) {
		this.version = value;
	}

	public Integer getVersion() {
		return this.version;
	}


}
