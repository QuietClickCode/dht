package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：支付回调数据对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-14 23:00:16
 */
public class PayCallback implements java.io.Serializable {

	/**pcId*/
	@NotEmpty
	private Long pcId;
	/**回调订单号*/
	@NotEmpty
	@Length(min = 1, max = 20)
	private String pcOrderNo;
	/**支付方式（0 微信，1 支付宝）*/
	@NotEmpty
	private Integer pcWay;
	/**支付类型（1 微信公众号支付,2 扫描支付,3 h5支付）*/
	@NotEmpty
	private Integer pcType;
	/**是否处理*/
	@NotEmpty
	private Integer pcStatus;
	/**回调ip*/
	@NotEmpty
	@Length(min = 1, max = 50)
	private String pcRemoteAdd;
	/**是否验签成功(0 成功，1 失败)*/
	@NotEmpty
	private Integer pcIsSign;
	/**传入sign 与生成sign（中间用：隔开）*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String pcSign;
	/**回调状态值*/
	@NotEmpty
	@Length(min = 1, max = 20)
	private String pcReturnCode;
	/**回调内容*/
	@NotEmpty
	@Length(min = 1, max = 2000)
	private String pcContext;
	/**回调时间*/
	@NotEmpty
	private Date pcCreateTime;
	//columns END

	public PayCallback(){
	}

	public PayCallback( Long pcId){
		this.pcId = pcId;
	}

	public void setPcId(Long value) {
		this.pcId = value;
	}

	public Long getPcId() {
		return this.pcId;
	}
	public void setPcOrderNo(String value) {
		this.pcOrderNo = value;
	}

	public String getPcOrderNo() {
		return this.pcOrderNo;
	}
	public void setPcWay(Integer value) {
		this.pcWay = value;
	}

	public Integer getPcWay() {
		return this.pcWay;
	}
	public void setPcType(Integer value) {
		this.pcType = value;
	}

	public Integer getPcType() {
		return this.pcType;
	}
	public void setPcStatus(Integer value) {
		this.pcStatus = value;
	}

	public Integer getPcStatus() {
		return this.pcStatus;
	}
	public void setPcRemoteAdd(String value) {
		this.pcRemoteAdd = value;
	}

	public String getPcRemoteAdd() {
		return this.pcRemoteAdd;
	}
	public void setPcIsSign(Integer value) {
		this.pcIsSign = value;
	}

	public Integer getPcIsSign() {
		return this.pcIsSign;
	}
	public void setPcSign(String value) {
		this.pcSign = value;
	}

	public String getPcSign() {
		return this.pcSign;
	}
	public void setPcReturnCode(String value) {
		this.pcReturnCode = value;
	}

	public String getPcReturnCode() {
		return this.pcReturnCode;
	}
	public void setPcContext(String value) {
		this.pcContext = value;
	}

	public String getPcContext() {
		return this.pcContext;
	}
	public void setPcCreateTime(Date value) {
		this.pcCreateTime = value;
	}

	public Date getPcCreateTime() {
		return this.pcCreateTime;
	}


}
