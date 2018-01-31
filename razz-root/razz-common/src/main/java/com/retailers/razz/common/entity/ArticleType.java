package com.retailers.razz.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 描述：文章类别对象
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2018-01-30 14:34:45
 */
public class ArticleType implements java.io.Serializable {

	/**tid*/
	@NotEmpty
	private Long tid;
	/**文章类别名*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String tname;
	/**父级ID*/
	@NotEmpty
	private Long parentId;
	/**是否删除（0代表不删除，1代表删除）*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public ArticleType(){
	}

	public ArticleType( Long tid){
		this.tid = tid;
	}

	public void setTid(Long value) {
		this.tid = value;
	}

	public Long getTid() {
		return this.tid;
	}
	public void setTname(String value) {
		this.tname = value;
	}

	public String getTname() {
		return this.tname;
	}
	public void setParentId(Long value) {
		this.parentId = value;
	}

	public Long getParentId() {
		return this.parentId;
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


}
