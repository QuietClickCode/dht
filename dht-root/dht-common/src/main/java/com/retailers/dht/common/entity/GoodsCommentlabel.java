package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品评论表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-06 11:18:22
 */
public class GoodsCommentlabel implements java.io.Serializable {

	/**商品评论标签ID*/
	@NotEmpty
	private Long gclId;
	/**评论标签名称*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String gclName;
	/**评论标签状态 0停用 1启用*/
	@NotEmpty
	private Long gclStatus;
	/**评论等级*/
	@NotEmpty
	@Length(min = 1, max = 100)
	private String gclLevel;
	/**父类*/
	@NotEmpty
	private Long gclParenid;
	/**是否是类型评论*/
	@NotEmpty
	private Long isClass;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsCommentlabel(){
	}

	public GoodsCommentlabel( Long gclId){
		this.gclId = gclId;
	}

	public void setGclId(Long value) {
		this.gclId = value;
	}

	public Long getGclId() {
		return this.gclId;
	}
	public void setGclName(String value) {
		this.gclName = value;
	}

	public String getGclName() {
		return this.gclName;
	}
	public void setGclStatus(Long value) {
		this.gclStatus = value;
	}

	public Long getGclStatus() {
		return this.gclStatus;
	}
	public void setGclLevel(String value) {
		this.gclLevel = value;
	}

	public String getGclLevel() {
		return this.gclLevel;
	}
	public void setGclParenid(Long value) {
		this.gclParenid = value;
	}

	public Long getGclParenid() {
		return this.gclParenid;
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

	public Long getIsClass() {
		return isClass;
	}

	public void setIsClass(Long isClass) {
		this.isClass = isClass;
	}
}
