package com.retailers.dht.common.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

/**
 * 描述：部门人员表对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-27 16:32:45
 */
public class GoodsType implements java.io.Serializable {

	/**商品类型ID*/
	@NotEmpty
	private Long gtId;
	/**商品类型名称*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String gtName;
	/**是否关联参数*/
	@NotEmpty
	private Long isParams;
	/**是否关联品牌*/
	@NotEmpty
	private Long isTrademark;
	/**是否关联规格*/
	@NotEmpty
	private Long isSpecification;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsType(){
	}

	public GoodsType( Long gtId){
		this.gtId = gtId;
	}

	public void setGtId(Long value) {
		this.gtId = value;
	}

	public Long getGtId() {
		return this.gtId;
	}
	public void setGtName(String value) {
		this.gtName = value;
	}

	public String getGtName() {
		return this.gtName;
	}
	public void setIsParams(Long value) {
		this.isParams = value;
	}

	public Long getIsParams() {
		return this.isParams;
	}
	public void setIsTrademark(Long value) {
		this.isTrademark = value;
	}

	public Long getIsTrademark() {
		return this.isTrademark;
	}
	public void setIsSpecification(Long value) {
		this.isSpecification = value;
	}

	public Long getIsSpecification() {
		return this.isSpecification;
	}
	public void setVersion(Long value) {
		this.version = value;
	}

	public Long getVersion() {
		return this.version;
	}

	private Set goodsClassifications = new HashSet(0);
	public void setGoodsClassifications(Set<GoodsClassification> goodsClassification){
		this.goodsClassifications = goodsClassification;
	}

	public Set<GoodsClassification> getGoodsClassifications() {
		return goodsClassifications;
	}


}
