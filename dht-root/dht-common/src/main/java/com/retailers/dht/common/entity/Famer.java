package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：农夫信息表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-02-05 15:07:13
 */
public class Famer implements java.io.Serializable {

	/**农户id*/
	@NotEmpty
	private Long fid;
	/**农户姓名*/
	@NotEmpty
	@Length(min = 1, max = 11)
	private String fname;
	/**农户性别(0女 1男)*/
	@NotEmpty
	private Long fsex;
	/**出生日期*/
	@NotEmpty
	private Date fbirth;
	/**学历*/
	@NotEmpty
	@Length(min = 1, max = 11)
	private String feducation;
	/**民族*/
	@NotEmpty
	@Length(min = 1, max = 11)
	private String fethnic;
	/**政治面貌*/
	@NotEmpty
	@Length(min = 1, max = 11)
	private String fpolitical;
	/**电话号码*/
	@NotEmpty
	@Length(min = 1, max = 11)
	private String fphone;
	/**身份证号码*/
	@NotEmpty
	@Length(min = 1, max = 18)
	private String fcardNumber;
	/**头像id*/
	@NotEmpty
	private Long fimg;
	/**户籍地址*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String fcensus;
	/**详细地址*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String faddress;
	/**人口数*/
	@NotEmpty
	private Long fpopulation;
	/**留守儿童数*/
	@NotEmpty
	private Long fleavechildnum;
	/**家庭类型*/
	@NotEmpty
	@Length(min = 1, max = 100)
	private String ffamilytype;
	/**贫困户卡号*/
	@NotEmpty
	@Length(min = 1, max = 100)
	private String fpoornum;
	/**邮编*/
	@NotEmpty
	@Length(min = 1, max = 100)
	private String fpost;

	/**采集人*/
	@NotEmpty
	@Length(min = 1, max = 10)
	private String fcollector;
	/**采集人职位*/
	@NotEmpty
	@Length(min = 1, max = 10)
	private String fcollectorPosition;
	/**采集人电话*/
	@NotEmpty
	@Length(min = 1, max = 11)
	private String fcollectorPhone;

	/**备注*/
	@NotEmpty
	@Length(min = 1, max = 999)
	private String fremark;
	/**是否删除 0不删除 1删除*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public Famer(){
	}

	public Famer( Long fid){
		this.fid = fid;
	}

	public void setFid(Long value) {
		this.fid = value;
	}

	public Long getFid() {
		return this.fid;
	}
	public void setFname(String value) {
		this.fname = value;
	}

	public String getFname() {
		return this.fname;
	}
	public void setFsex(Long value) {
		this.fsex = value;
	}

	public Long getFsex() {
		return this.fsex;
	}
	public void setFbirth(Date value) {
		this.fbirth = value;
	}

	public Date getFbirth() {
		return this.fbirth;
	}
	public void setFeducation(String value) {
		this.feducation = value;
	}

	public String getFeducation() {
		return this.feducation;
	}
	public void setFethnic(String value) {
		this.fethnic = value;
	}

	public String getFethnic() {
		return this.fethnic;
	}
	public void setFpolitical(String value) {
		this.fpolitical = value;
	}

	public String getFpolitical() {
		return this.fpolitical;
	}
	public void setFphone(String value) {
		this.fphone = value;
	}

	public String getFphone() {
		return this.fphone;
	}
	public void setFcardNumber(String value) {
		this.fcardNumber = value;
	}

	public String getFcardNumber() {
		return this.fcardNumber;
	}
	public void setFimg(Long value) {
		this.fimg = value;
	}

	public Long getFimg() {
		return this.fimg;
	}
	public void setFcensus(String value) {
		this.fcensus = value;
	}

	public String getFcensus() {
		return this.fcensus;
	}
	public void setFaddress(String value) {
		this.faddress = value;
	}

	public String getFaddress() {
		return this.faddress;
	}
	public void setFpopulation(Long value) {
		this.fpopulation = value;
	}

	public Long getFpopulation() {
		return this.fpopulation;
	}
	public void setFleavechildnum(Long value) {
		this.fleavechildnum = value;
	}

	public Long getFleavechildnum() {
		return this.fleavechildnum;
	}
	public void setFfamilytype(String value) {
		this.ffamilytype = value;
	}

	public String getFfamilytype() {
		return this.ffamilytype;
	}
	public void setFpoornum(String value) {
		this.fpoornum = value;
	}

	public String getFpoornum() {
		return this.fpoornum;
	}
	public void setFpost(String value) {
		this.fpost = value;
	}

	public String getFpost() {
		return this.fpost;
	}
	public void setFremark(String value) {
		this.fremark = value;
	}

	public String getFremark() {
		return this.fremark;
	}
	public void setIsDelete(Long value) {
		this.isDelete = value;
	}

	public Long getIsDelete() {
		return this.isDelete;
	}
	public void setVersion(Long value) {
		this.version = value;
	}

	public Long getVersion() {
		return this.version;
	}

	public String getFcollector() {
		return fcollector;
	}

	public void setFcollector(String fcollector) {
		this.fcollector = fcollector;
	}

	public String getFcollectorPosition() {
		return fcollectorPosition;
	}

	public void setFcollectorPosition(String fcollectorPosition) {
		this.fcollectorPosition = fcollectorPosition;
	}

	public String getFcollectorPhone() {
		return fcollectorPhone;
	}

	public void setFcollectorPhone(String fcollectorPhone) {
		this.fcollectorPhone = fcollectorPhone;
	}
}
