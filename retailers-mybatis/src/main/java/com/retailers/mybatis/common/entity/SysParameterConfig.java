package com.retailers.mybatis.common.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * 描述：系统参数配置表对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-08 11:21:04
 */
public class SysParameterConfig implements java.io.Serializable {

	/**参数标识关键字(主键)*/
	@NotEmpty
	@Length(min = 1, max = 60)
	private String parameterKey;
	/**参数值(xml格式)*/
	@NotEmpty
	@Length(min = 1, max = 1000)
	private String parameterValue;
	/**参数名称*/
	@NotEmpty
	@Length(min = 1, max = 100)
	private String parameterName;
	/**参数描述*/
	@NotEmpty
	@Length(min = 1, max = 500)
	private String parameterDes;
	/**创建时间*/
	@NotEmpty
	private Date parameterCreateTime;
	/**是否允许后台用户操作该常量（0 允许，1 不允许）*/
	@NotEmpty
	private Integer allowUpdate;
	/**系统版本*/
	@NotEmpty
	private Integer parameterVersion;
	//columns END

	public SysParameterConfig(){
	}

	public SysParameterConfig(String parameterKey){
		this.parameterKey = parameterKey;
	}

	public void setParameterKey(String value) {
		this.parameterKey = value;
	}

	public String getParameterKey() {
		return this.parameterKey;
	}
	public void setParameterValue(String value) {
		this.parameterValue = value;
	}

	public String getParameterValue() {
		return this.parameterValue;
	}
	public void setParameterName(String value) {
		this.parameterName = value;
	}

	public String getParameterName() {
		return this.parameterName;
	}
	public void setParameterDes(String value) {
		this.parameterDes = value;
	}

	public String getParameterDes() {
		return this.parameterDes;
	}
	public void setParameterCreateTime(Date value) {
		this.parameterCreateTime = value;
	}

	public Date getParameterCreateTime() {
		return this.parameterCreateTime;
	}
	public void setAllowUpdate(Integer value) {
		this.allowUpdate = value;
	}

	public Integer getAllowUpdate() {
		return this.allowUpdate;
	}
	public void setParameterVersion(Integer value) {
		this.parameterVersion = value;
	}

	public Integer getParameterVersion() {
		return this.parameterVersion;
	}


}
