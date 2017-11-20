package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：用户收货地址列表对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-20 16:19:49
 */
public class UserAddress implements java.io.Serializable {

	/**uaId*/
	@NotEmpty
	private Long uaId;
	/**uuid(每次修改后 uuid 不变，id变更）*/
	@NotEmpty
	@Length(min = 1, max = 32)
	private String uaUuid;
	/**用户id*/
	@NotEmpty
	private Long uaUid;
	/**收货人姓名*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String uaName;
	/**收货电话*/
	@NotEmpty
	@Length(min = 1, max = 11)
	private String uaPhone;
	/**邮政编码*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String uaZipCode;
	/**省份编码code*/
	@NotEmpty
	@Length(min = 1, max = 24)
	private String uaProvince;
	/**城市编码code*/
	@NotEmpty
	@Length(min = 1, max = 24)
	private String uaCity;
	/**区级地域编码*/
	@NotEmpty
	@Length(min = 1, max = 24)
	private String uaDistrict;
	/**详细地址*/
	@NotEmpty
	@Length(min = 1, max = 500)
	private String uaAddress;
	/**全部地址（包含省 市 区 详细地址)*/
	@NotEmpty
	@Length(min = 1, max = 500)
	private String uaAllAddress;
	/**是否是默认地址*/
	@NotEmpty
	private Integer uaIsDefault;
	/**uaCreateTime*/
	@NotEmpty
	private Date uaCreateTime;
	/**uaUpdateTime*/
	@NotEmpty
	private Date uaUpdateTime;
	/**是否有效*/
	@NotEmpty
	private Integer isValida;
	/**是否删除*/
	@NotEmpty
	private Integer isDelete;
	/**数据版本号*/
	@NotEmpty
	private Integer version;
	//columns END

	public UserAddress(){
	}

	public UserAddress( Long uaId){
		this.uaId = uaId;
	}

	public void setUaId(Long value) {
		this.uaId = value;
	}

	public Long getUaId() {
		return this.uaId;
	}
	public void setUaUuid(String value) {
		this.uaUuid = value;
	}

	public String getUaUuid() {
		return this.uaUuid;
	}
	public void setUaUid(Long value) {
		this.uaUid = value;
	}

	public Long getUaUid() {
		return this.uaUid;
	}
	public void setUaName(String value) {
		this.uaName = value;
	}

	public String getUaName() {
		return this.uaName;
	}
	public void setUaPhone(String value) {
		this.uaPhone = value;
	}

	public String getUaPhone() {
		return this.uaPhone;
	}
	public void setUaZipCode(String value) {
		this.uaZipCode = value;
	}

	public String getUaZipCode() {
		return this.uaZipCode;
	}
	public void setUaProvince(String value) {
		this.uaProvince = value;
	}

	public String getUaProvince() {
		return this.uaProvince;
	}
	public void setUaCity(String value) {
		this.uaCity = value;
	}

	public String getUaCity() {
		return this.uaCity;
	}
	public void setUaDistrict(String value) {
		this.uaDistrict = value;
	}

	public String getUaDistrict() {
		return this.uaDistrict;
	}
	public void setUaAddress(String value) {
		this.uaAddress = value;
	}

	public String getUaAddress() {
		return this.uaAddress;
	}
	public void setUaAllAddress(String value) {
		this.uaAllAddress = value;
	}

	public String getUaAllAddress() {
		return this.uaAllAddress;
	}
	public void setUaIsDefault(Integer value) {
		this.uaIsDefault = value;
	}

	public Integer getUaIsDefault() {
		return this.uaIsDefault;
	}
	public void setUaCreateTime(Date value) {
		this.uaCreateTime = value;
	}

	public Date getUaCreateTime() {
		return this.uaCreateTime;
	}
	public void setUaUpdateTime(Date value) {
		this.uaUpdateTime = value;
	}

	public Date getUaUpdateTime() {
		return this.uaUpdateTime;
	}
	public void setIsValida(Integer value) {
		this.isValida = value;
	}

	public Integer getIsValida() {
		return this.isValida;
	}
	public void setIsDelete(Integer value) {
		this.isDelete = value;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}
	public void setVersion(Integer value) {
		this.version = value;
	}

	public Integer getVersion() {
		return this.version;
	}


}
