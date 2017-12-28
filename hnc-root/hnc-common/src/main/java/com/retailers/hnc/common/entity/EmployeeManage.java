package com.retailers.hnc.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：置业顾问对象
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-28 16:30:35
 */
public class EmployeeManage implements java.io.Serializable {

	/**员工ID*/
	@NotEmpty
	private Long emId;
	/**员工姓名*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String emName;
	/**员工性别(1代表男，0代表女)*/
	@NotEmpty
	private Integer emSex;
	/**入职时间*/
	@NotEmpty
	private Date emEntryTime;
	/**1代表置业顾问，2代表管理员*/
	@NotEmpty
	private Integer emType;
	/**所属团队*/
	@NotEmpty
	private Long emTeam;
	/**职位*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String emPosition;
	/**员工手机号*/
	@NotEmpty
	@Length(min = 1, max = 11)
	private String emPhone;
	/**微信绑定手机号*/
	@NotEmpty
	@Length(min = 1, max = 11)
	private String wxPhone;
	/**员工身份证号*/
	@NotEmpty
	@Length(min = 1, max = 18)
	private String emIdCard;
	/**调动时间*/
	@NotEmpty
	private Date emRemoveTime;
	/**备注*/
	@NotEmpty
	@Length(min = 1, max = 20000)
	private String emInfo;
	/**是否显示（0代表不显示，1代表显示）默认为1*/
	@NotEmpty
	private Integer isShow;
	/**是否删除（0代表不删除，1代表删除）默认为0*/
	@NotEmpty
	private Integer isDelete;
	/**排序*/
	@NotEmpty
	private Long emOrder;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public EmployeeManage(){
	}

	public EmployeeManage( Long emId){
		this.emId = emId;
	}

	public void setEmId(Long value) {
		this.emId = value;
	}

	public Long getEmId() {
		return this.emId;
	}
	public void setEmName(String value) {
		this.emName = value;
	}

	public String getEmName() {
		return this.emName;
	}
	public void setEmSex(Integer value) {
		this.emSex = value;
	}

	public Integer getEmSex() {
		return this.emSex;
	}
	public void setEmEntryTime(Date value) {
		this.emEntryTime = value;
	}

	public Date getEmEntryTime() {
		return this.emEntryTime;
	}
	public void setEmType(Integer value) {
		this.emType = value;
	}

	public Integer getEmType() {
		return this.emType;
	}
	public void setEmTeam(Long value) {
		this.emTeam = value;
	}

	public Long getEmTeam() {
		return this.emTeam;
	}
	public void setEmPosition(String value) {
		this.emPosition = value;
	}

	public String getEmPosition() {
		return this.emPosition;
	}
	public void setEmPhone(String value) {
		this.emPhone = value;
	}

	public String getEmPhone() {
		return this.emPhone;
	}
	public void setWxPhone(String value) {
		this.wxPhone = value;
	}

	public String getWxPhone() {
		return this.wxPhone;
	}
	public void setEmIdCard(String value) {
		this.emIdCard = value;
	}

	public String getEmIdCard() {
		return this.emIdCard;
	}
	public void setEmRemoveTime(Date value) {
		this.emRemoveTime = value;
	}

	public Date getEmRemoveTime() {
		return this.emRemoveTime;
	}
	public void setEmInfo(String value) {
		this.emInfo = value;
	}

	public String getEmInfo() {
		return this.emInfo;
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
	public void setEmOrder(Long value) {
		this.emOrder = value;
	}

	public Long getEmOrder() {
		return this.emOrder;
	}
	public void setVersion(Long value) {
		this.version = value;
	}

	public Long getVersion() {
		return this.version;
	}


}
