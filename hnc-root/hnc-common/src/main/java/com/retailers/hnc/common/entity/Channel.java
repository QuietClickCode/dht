package com.retailers.hnc.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 描述：来访渠道对象
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-26 17:11:32
 */
public class Channel implements java.io.Serializable {

	/**cid*/
	@NotEmpty
	private Long cid;
	/**来访渠道*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String cchannel;
	/**排序*/
	@NotEmpty
	private Long corder;
	/**是否显示（1代表显示，0代表隐藏）默认为1*/
	@NotEmpty
	private Integer isShow;
	/**是否删除（0代表不删除，1代表删除）默认为0*/
	@NotEmpty
	private Integer isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public Channel(){
	}

	public Channel( Long cid){
		this.cid = cid;
	}

	public void setCid(Long value) {
		this.cid = value;
	}

	public Long getCid() {
		return this.cid;
	}
	public void setCchannel(String value) {
		this.cchannel = value;
	}

	public String getCchannel() {
		return this.cchannel;
	}
	public void setCorder(Long value) {
		this.corder = value;
	}

	public Long getCorder() {
		return this.corder;
	}
	public void setIsShow(Integer value) {
		this.isShow = value;
	}

	public Integer getIsShow() {
		return this.isShow;
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
