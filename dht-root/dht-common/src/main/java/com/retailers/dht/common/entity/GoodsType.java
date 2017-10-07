package com.retailers.dht.common.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

/**
 * 描述：商品大类表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-09-29 14:57:58
 */
public class GoodsType implements java.io.Serializable {

	/**商品类型ID*/
	@NotEmpty
	private Long gtId;
	/**商品类型名称*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String gtName;
	/**是否关联参数 0代表没有关联参数 1代表有关联参数*/
	@NotEmpty
	private Long isParams;
	/**是否关联品牌 0代表没有关联品牌 1代表有关联品牌*/
	@NotEmpty
	private Long isTrademark;
	/**是否关联规格 0代表没有关联规格 1代表有关联规格*/
	@NotEmpty
	private Long isSpecification;
	/**是否显示*/
	@NotEmpty
	private Long isShow;
	/**是否删除*/
	@NotEmpty
	private Long isDelete;
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
	public void setIsShow(Long value) {
		this.isShow = value;
	}

	public Long getIsShow() {
		return this.isShow;
	}
	public void setIsDelete(Long value) {
		this.isDelete = value;
	}

	public Long getIsDelete() {
		return this.isDelete;
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
