package com.retailers.sbj.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 描述：团队表对象
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-22 13:58:29
 */
public class Team implements java.io.Serializable {

	/**团队ID*/
	@NotEmpty
	private Long tid;
	/**团队名称*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String tname;
	/**所属公司*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String tcompany;
	/**排序*/
	@NotEmpty
	private Long torder;
	/**是否删除（0代表不删除，1代表删除）默认为0*/
	@NotEmpty
	private Integer isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public Team(){
	}

	public Team( Long tid){
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
	public void setTcompany(String value) {
		this.tcompany = value;
	}

	public String getTcompany() {
		return this.tcompany;
	}
	public void setTorder(Long value) {
		this.torder = value;
	}

	public Long getTorder() {
		return this.torder;
	}
	public void setIsDelete(Integer value) {
		this.isDelete = value;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}
	public void setVersion(Long value) {
		this.version = value;
	}

	public Long getVersion() {
		return this.version;
	}


}
