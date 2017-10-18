package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：后台请求日志对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-18 10:02:09
 */
public class LogManagerReq implements java.io.Serializable {

	/**id*/
	@NotEmpty
	private Long id;
	/**请求url*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String url;
	/**传入参数*/
	@NotEmpty
	@Length(min = 1, max = 65535)
	private String params;
	/**执行是否异常(0 成功，1 异常）*/
	@NotEmpty
	private Long isError;
	/**请求ip*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String reqIp;
	/**接口执行时间*/
	@NotEmpty
	private Long executeTime;
	/**回传内容(如果异常则为异常信息)*/
	@NotEmpty
	@Length(min = 1, max = 65535)
	private String context;
	/**创建时间*/
	@NotEmpty
	private Date createTime;
	/**请求用户id*/
	@NotEmpty
	private Long reqUser;
	//columns END

	public LogManagerReq(){
	}

	public LogManagerReq( Long id){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}
	public void setUrl(String value) {
		this.url = value;
	}

	public String getUrl() {
		return this.url;
	}
	public void setParams(String value) {
		this.params = value;
	}

	public String getParams() {
		return this.params;
	}
	public void setIsError(Long value) {
		this.isError = value;
	}

	public Long getIsError() {
		return this.isError;
	}
	public void setReqIp(String value) {
		this.reqIp = value;
	}

	public String getReqIp() {
		return this.reqIp;
	}
	public void setExecuteTime(Long value) {
		this.executeTime = value;
	}

	public Long getExecuteTime() {
		return this.executeTime;
	}
	public void setContext(String value) {
		this.context = value;
	}

	public String getContext() {
		return this.context;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}
	public void setReqUser(Long value) {
		this.reqUser = value;
	}

	public Long getReqUser() {
		return this.reqUser;
	}


}
