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
 * @date 2017-09-27 16:28:01
 */
public class GoodsClassification implements java.io.Serializable {

	/**商品子类ID*/
	@NotEmpty
	private Long ggId;
	/**子分类名称*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String ggName;
	/**子类的上级分类ID*/
	@NotEmpty
	private Long ggManager;
	/**所属大类ID*/
	@NotEmpty
	private Long ggHome;
	/**显示图片的路径*/
	@NotEmpty
	@Length(min = 1, max = 2555)
	private String ggImgpath;
	/**排序*/
	@NotEmpty
	private Long ggOrder;
	/**是否是顶级元素*/
	@NotEmpty
	private Long isTop;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsClassification(){
	}

	public GoodsClassification( Long ggId){
		this.ggId = ggId;
	}

	public void setGgId(Long value) {
		this.ggId = value;
	}

	public Long getGgId() {
		return this.ggId;
	}
	public void setGgName(String value) {
		this.ggName = value;
	}

	public String getGgName() {
		return this.ggName;
	}
	public void setGgManager(Long value) {
		this.ggManager = value;
	}

	public Long getGgManager() {
		return this.ggManager;
	}
	public void setGgHome(Long value) {
		this.ggHome = value;
	}

	public Long getGgHome() {
		return this.ggHome;
	}
	public void setGgImgpath(String value) {
		this.ggImgpath = value;
	}

	public String getGgImgpath() {
		return this.ggImgpath;
	}
	public void setGgOrder(Long value) {
		this.ggOrder = value;
	}

	public Long getGgOrder() {
		return this.ggOrder;
	}
	public void setIsTop(Long value) {
		this.isTop = value;
	}

	public Long getIsTop() {
		return this.isTop;
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

	private GoodsClassification goodsClassification;

	public void setGoodsClassification(GoodsClassification goodsClassification){
		this.goodsClassification = goodsClassification;
	}

	public GoodsClassification getGoodsClassification() {
		return goodsClassification;
	}

	private GoodsType goodsType;

	public void setGoodsType(GoodsType goodsType){
		this.goodsType = goodsType;
	}

	public GoodsType getGoodsType() {
		return goodsType;
	}


}
