package com.retailers.sbj.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 描述：项目表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-13 17:30:49
 */
public class Project implements java.io.Serializable {

	/**项目id*/
	@NotEmpty
	private Long pid;
	/**项目名称*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String pname;
	/**logo图片id*/
	@NotEmpty
	private Long plogoid;
	/**项目地址*/
	@NotEmpty
	@Length(min = 1, max = 1000)
	private String paddress;
	/**经度*/
	@NotEmpty
	private Double plon;
	/**纬度*/
	@NotEmpty
	private Double plat;
	/**面积*/
	@NotEmpty
	private Long parea;
	/**户数*/
	@NotEmpty
	private Long pnum;
	/**项目描述*/
	@NotEmpty
	@Length(min = 1, max = 2000)
	private String pdescription;
	/**是否删除 0不删除 1删除*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public Project(){
	}

	public Project( Long pid){
		this.pid = pid;
	}

	public void setPid(Long value) {
		this.pid = value;
	}

	public Long getPid() {
		return this.pid;
	}
	public void setPname(String value) {
		this.pname = value;
	}

	public String getPname() {
		return this.pname;
	}
	public void setPlogoid(Long value) {
		this.plogoid = value;
	}

	public Long getPlogoid() {
		return this.plogoid;
	}
	public void setPaddress(String value) {
		this.paddress = value;
	}

	public String getPaddress() {
		return this.paddress;
	}
	public void setPlon(Double value) {
		this.plon = value;
	}

	public Double getPlon() {
		return this.plon;
	}
	public void setPlat(Double value) {
		this.plat = value;
	}

	public Double getPlat() {
		return this.plat;
	}
	public void setParea(Long value) {
		this.parea = value;
	}

	public Long getParea() {
		return this.parea;
	}
	public void setPnum(Long value) {
		this.pnum = value;
	}

	public Long getPnum() {
		return this.pnum;
	}
	public void setPdescription(String value) {
		this.pdescription = value;
	}

	public String getPdescription() {
		return this.pdescription;
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
