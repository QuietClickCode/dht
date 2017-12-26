package com.retailers.hnc.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：客户管理对象
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-26 13:47:05
 */
public class ClientManage implements java.io.Serializable {

	/**客户ID*/
	@NotEmpty
	private Long tmId;
	/**客户姓名*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String tmName;
	/**客户性别*/
	@NotEmpty
	private Integer tmSex;
	/**所属置业顾问*/
	@NotEmpty
	private Long tmEmployee;
	/**客户年龄*/
	@NotEmpty
	private Integer tmAge;
	/**购房状态*/
	@NotEmpty
	private Integer tmStatus;
	/**客户电话*/
	@NotEmpty
	@Length(min = 1, max = 11)
	private String tmPhone;
	/**登记时间*/
	@NotEmpty
	private Date tmRegisterTime;
	/**客户身份证号码*/
	@NotEmpty
	@Length(min = 1, max = 18)
	private String tmIdCard;
	/**来访渠道*/
	@NotEmpty
	private Long tmChannel;
	/**客户备注*/
	@NotEmpty
	@Length(min = 1, max = 20000)
	private String tmInfo;
	/**是否删除（0代表不删除，1代表删除）默认为0*/
	@NotEmpty
	private Integer isDelete;
	/**排序*/
	@NotEmpty
	private Long tmOrder;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public ClientManage(){
	}

	public ClientManage( Long tmId){
		this.tmId = tmId;
	}

	public void setTmId(Long value) {
		this.tmId = value;
	}

	public Long getTmId() {
		return this.tmId;
	}
	public void setTmName(String value) {
		this.tmName = value;
	}

	public String getTmName() {
		return this.tmName;
	}
	public void setTmSex(Integer value) {
		this.tmSex = value;
	}

	public Integer getTmSex() {
		return this.tmSex;
	}
	public void setTmEmployee(Long value) {
		this.tmEmployee = value;
	}

	public Long getTmEmployee() {
		return this.tmEmployee;
	}
	public void setTmAge(Integer value) {
		this.tmAge = value;
	}

	public Integer getTmAge() {
		return this.tmAge;
	}
	public void setTmStatus(Integer value) {
		this.tmStatus = value;
	}

	public Integer getTmStatus() {
		return this.tmStatus;
	}
	public void setTmPhone(String value) {
		this.tmPhone = value;
	}

	public String getTmPhone() {
		return this.tmPhone;
	}
	public void setTmRegisterTime(Date value) {
		this.tmRegisterTime = value;
	}

	public Date getTmRegisterTime() {
		return this.tmRegisterTime;
	}
	public void setTmIdCard(String value) {
		this.tmIdCard = value;
	}

	public String getTmIdCard() {
		return this.tmIdCard;
	}
	public void setTmChannel(Long value) {
		this.tmChannel = value;
	}

	public Long getTmChannel() {
		return this.tmChannel;
	}
	public void setTmInfo(String value) {
		this.tmInfo = value;
	}

	public String getTmInfo() {
		return this.tmInfo;
	}
	public void setIsDelete(Integer value) {
		this.isDelete = value;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}
	public void setTmOrder(Long value) {
		this.tmOrder = value;
	}

	public Long getTmOrder() {
		return this.tmOrder;
	}
	public void setVersion(Long value) {
		this.version = value;
	}

	public Long getVersion() {
		return this.version;
	}


}
