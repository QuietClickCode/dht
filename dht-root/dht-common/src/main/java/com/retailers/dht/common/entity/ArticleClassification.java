package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 描述：文章分类对象
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-25 17:17:40
 */
public class ArticleClassification implements java.io.Serializable {

	/**aid*/
	@NotEmpty
	private Long aid;
	/**文章分类名称*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String aname;
	/**是否显示（1代表显示，0代表不显示，默认为1）*/
	@NotEmpty
	private Long isShow;
	/**是否删除（1代表删除，0代表不删除，默认为0）*/
	@NotEmpty
	private Long isDelete;
	/**排序*/
	@NotEmpty
	private Long aorder;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public ArticleClassification(){
	}

	public ArticleClassification( Long aid){
		this.aid = aid;
	}

	public void setAid(Long value) {
		this.aid = value;
	}

	public Long getAid() {
		return this.aid;
	}
	public void setAname(String value) {
		this.aname = value;
	}

	public String getAname() {
		return this.aname;
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
	public void setAorder(Long value) {
		this.aorder = value;
	}

	public Long getAorder() {
		return this.aorder;
	}
	public void setVersion(Long value) {
		this.version = value;
	}

	public Long getVersion() {
		return this.version;
	}


}
