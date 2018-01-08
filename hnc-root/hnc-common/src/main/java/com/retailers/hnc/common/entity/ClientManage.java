package com.retailers.hnc.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：用户表对象
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2018-01-08 15:03:40
 */
public class ClientManage implements java.io.Serializable {

	/**客户ID*/
	@NotEmpty
	private Long tmId;
	/**客户姓名*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String tmName;
	/**客户性别(0代表女，1代表男)*/
	@NotEmpty
	private Integer tmSex;
	/**所属置业顾问*/
	@NotEmpty
	private Long tmEmployee;
	/**常驻区域*/
	@NotEmpty
	@Length(min = 1, max = 20)
	private String tmArea;
	/**客户年龄*/
	@NotEmpty
	@Length(min = 1, max = 20)
	private String tmAge;
	/**购房状态(0代表未购房，1代表已购房)*/
	@NotEmpty
	private Integer tmStatus;
	/**0代表客户，1代表置业顾问，2代表管理员*/
	@NotEmpty
	private Integer tmLoginStatus;
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
	public void setTmArea(String value) {
		this.tmArea = value;
	}

	public String getTmArea() {
		return this.tmArea;
	}
	public void setTmAge(String value) {
		this.tmAge = value;
	}

	public String getTmAge() {
		return this.tmAge;
	}
	public void setTmStatus(Integer value) {
		this.tmStatus = value;
	}

	public Integer getTmStatus() {
		return this.tmStatus;
	}
	public void setTmLoginStatus(Integer value) {
		this.tmLoginStatus = value;
	}

	public Integer getTmLoginStatus() {
		return this.tmLoginStatus;
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
