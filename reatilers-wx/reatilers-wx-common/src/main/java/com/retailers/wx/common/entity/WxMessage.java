package com.retailers.wx.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：用户发送消息内容对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-19 01:17:19
 */
public class WxMessage implements java.io.Serializable {

	/**wmId*/
	@NotEmpty
	private Long wmId;
	/**消息类型（0 用户发出，1 回复）*/
	@NotEmpty
	private Long wmType;
	/**消息类型*/
	@NotEmpty
	@Length(min = 1, max = 20)
	private String wmMessageType;
	/**wmContext*/
	@NotEmpty
	@Length(min = 1, max = 65535)
	private String wmContext;
	/**wmUuid*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String wmUuid;
	/**wmCreateDate*/
	@NotEmpty
	private Date wmCreateDate;
	//columns END

	public WxMessage(){
	}

	public WxMessage( Long wmId){
		this.wmId = wmId;
	}

	public void setWmId(Long value) {
		this.wmId = value;
	}

	public Long getWmId() {
		return this.wmId;
	}
	public void setWmType(Long value) {
		this.wmType = value;
	}

	public Long getWmType() {
		return this.wmType;
	}
	public void setWmMessageType(String value) {
		this.wmMessageType = value;
	}

	public String getWmMessageType() {
		return this.wmMessageType;
	}
	public void setWmContext(String value) {
		this.wmContext = value;
	}

	public String getWmContext() {
		return this.wmContext;
	}
	public void setWmUuid(String value) {
		this.wmUuid = value;
	}

	public String getWmUuid() {
		return this.wmUuid;
	}
	public void setWmCreateDate(Date value) {
		this.wmCreateDate = value;
	}

	public Date getWmCreateDate() {
		return this.wmCreateDate;
	}


}
