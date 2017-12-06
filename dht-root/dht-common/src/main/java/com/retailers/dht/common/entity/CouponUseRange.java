package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：优惠卷使用范围对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-06 00:57:03
 */
public class CouponUseRange implements java.io.Serializable {

	/**cpurId*/
	@NotEmpty
	private Long cpurId;
	/**
	 * 类型（0 商品优惠，1 优惠卷）
	 */
	private Long type;
	/**优惠卷id*/
	@NotEmpty
	private Long cpId;
	/**使用范围类型（1 商品种类，2商品）*/
	@NotEmpty
	private Long cpurType;
	/**优惠卷使用范围关联id*/
	@NotEmpty
	private Long cpurRelevanceId;
	/**是否允许（0 允许，1 禁止）*/
	@NotEmpty
	private Integer cpurIsAllow;
	//columns END

	public CouponUseRange(){
	}

	public CouponUseRange( Long cpurId){
		this.cpurId = cpurId;
	}

	public void setCpurId(Long value) {
		this.cpurId = value;
	}

	public Long getCpurId() {
		return this.cpurId;
	}
	public void setCpId(Long value) {
		this.cpId = value;
	}

	public Long getCpId() {
		return this.cpId;
	}
	public void setCpurType(Long value) {
		this.cpurType = value;
	}

	public Long getCpurType() {
		return this.cpurType;
	}
	public void setCpurRelevanceId(Long value) {
		this.cpurRelevanceId = value;
	}

	public Long getCpurRelevanceId() {
		return this.cpurRelevanceId;
	}
	public void setCpurIsAllow(Integer value) {
		this.cpurIsAllow = value;
	}

	public Integer getCpurIsAllow() {
		return this.cpurIsAllow;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
}
