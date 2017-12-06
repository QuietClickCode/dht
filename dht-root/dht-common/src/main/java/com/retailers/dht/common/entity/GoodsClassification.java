package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 描述：商品子类表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-09-30 13:41:37
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
	private Long parentId;
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
	/**是否立即返现 0不立即返现 1立即返现*/
	@NotEmpty
	private Long isReturnnow;
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

	public GoodsClassification(){
	}

	public Long getIsReturnnow() {
		return isReturnnow;
	}

	public void setIsReturnnow(Long isReturnnow) {
		this.isReturnnow = isReturnnow;
	}

	public GoodsClassification(Long ggId){
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
	public void setParentId(Long value) {
		this.parentId = value;
	}

	public Long getParentId() {
		return this.parentId;
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

	private GoodsType goodsType;

	public void setGoodsType(GoodsType goodsType){
		this.goodsType = goodsType;
	}

	public GoodsType getGoodsType() {
		return goodsType;
	}


}
