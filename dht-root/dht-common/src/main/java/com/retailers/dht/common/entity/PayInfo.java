package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：支付信息对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-14 22:59:01
 */
public class PayInfo implements java.io.Serializable {

	/**piId*/
	@NotEmpty
	private Long piId;
	/**支付方式（0 微信，1 支付宝）*/
	@NotEmpty
	private Integer piWay;
	/**支付类型（1 微信公众号支付,2 扫描支付,3 h5支付）*/
	@NotEmpty
	private Integer piType;
	/** 支付订单号*/
	@NotEmpty
	@Length(min = 1, max = 20)
	private String piOrderNo;
	/**回调ip*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String pcRemoteAdd;
	/**确认id*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String piPrepayId;
	/**支付内容*/
	@NotEmpty
	@Length(min = 1, max = 1000)
	private String piContext;
	/**piCreateTime*/
	@NotEmpty
	private Date piCreateTime;
	//columns END

	public PayInfo(){
	}

	public PayInfo( Long piId){
		this.piId = piId;
	}

	public void setPiId(Long value) {
		this.piId = value;
	}

	public Long getPiId() {
		return this.piId;
	}
	public void setPiWay(Integer value) {
		this.piWay = value;
	}

	public Integer getPiWay() {
		return this.piWay;
	}
	public void setPiType(Integer value) {
		this.piType = value;
	}

	public Integer getPiType() {
		return this.piType;
	}
	public void setPiOrderNo(String value) {
		this.piOrderNo = value;
	}

	public String getPiOrderNo() {
		return this.piOrderNo;
	}
	public void setPcRemoteAdd(String value) {
		this.pcRemoteAdd = value;
	}

	public String getPcRemoteAdd() {
		return this.pcRemoteAdd;
	}
	public void setPiPrepayId(String value) {
		this.piPrepayId = value;
	}

	public String getPiPrepayId() {
		return this.piPrepayId;
	}
	public void setPiContext(String value) {
		this.piContext = value;
	}

	public String getPiContext() {
		return this.piContext;
	}
	public void setPiCreateTime(Date value) {
		this.piCreateTime = value;
	}

	public Date getPiCreateTime() {
		return this.piCreateTime;
	}


}
