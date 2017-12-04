package com.retailers.aliyun.sms.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：阿里云短信模版管理对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-04 15:05:09
 */
public class AliyunSmsTemplate implements java.io.Serializable {

	/**id*/
	@NotEmpty
	private Long id;
	/**短信功能code*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String funCode;
	/**短信用途*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String funName;
	/**短信模版code*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String code;
	/**模版内容*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String context;
	/**传入参数*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String param;
	/**创建人*/
	@NotEmpty
	private Long createUid;
	/**创建时间*/
	@NotEmpty
	private Date createTime;
	/**是否有效（0 有效，1 无效）*/
	@NotEmpty
	private Integer isValid;
	//columns END

	public AliyunSmsTemplate(){
	}

	public AliyunSmsTemplate( Long id){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}
	public void setFunCode(String value) {
		this.funCode = value;
	}

	public String getFunCode() {
		return this.funCode;
	}
	public void setFunName(String value) {
		this.funName = value;
	}

	public String getFunName() {
		return this.funName;
	}
	public void setCode(String value) {
		this.code = value;
	}

	public String getCode() {
		return this.code;
	}
	public void setContext(String value) {
		this.context = value;
	}

	public String getContext() {
		return this.context;
	}
	public void setParam(String value) {
		this.param = value;
	}

	public String getParam() {
		return this.param;
	}
	public void setCreateUid(Long value) {
		this.createUid = value;
	}

	public Long getCreateUid() {
		return this.createUid;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}
	public void setIsValid(Integer value) {
		this.isValid = value;
	}

	public Integer getIsValid() {
		return this.isValid;
	}


}
