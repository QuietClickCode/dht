package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：平台会员对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-19 11:06:52
 */
public class User implements java.io.Serializable {

	/**uid*/
	@NotEmpty
	private Long uid;
	/**推荐人id*/
	@NotEmpty
	private Long urecommendId;
	/**登陆帐户*/
	@NotEmpty
	@Length(min = 1, max = 12)
	private String uaccount;
	/**会员手机号（唯一值）*/
	@NotEmpty
	@Length(min = 1, max = 16)
	private String uphone;
	/**用户邮箱*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String uemail;
	/**会员姓名*/
	@NotEmpty
	@Length(min = 1, max = 12)
	private String uname;
	/**登录密码*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String upwd;
	/**支付密码*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String upayPwd;
	/**用户头像Id*/
	@NotEmpty
	private Long uimgid;
	/**用户类型(0 平台会员，1 兼职人员，2推广人员）*/
	@NotEmpty
	private Integer utype;
	/**首单推荐提成*/
	@NotEmpty
	private Long ufirstCommission;
	/**推荐消费提成*/
	@NotEmpty
	private Long urecommendCommission;
	/**会员充值id*/
	@NotEmpty
	private Long urechage;
	/**用户状态（0 正常，1 禁用）*/
	@NotEmpty
	private Integer ustatus;
	/**是否删除*/
	@NotEmpty
	private Integer isDelete;
	/**ucreateTime*/
	@NotEmpty
	private Date ucreateTime;
	/**是否是老板用户（0 新平台用户，1 老平台用户）*/
	@NotEmpty
	private Integer uisOld;
	/**是否是新加密规则（0 是，1 否 )老平台用户迁移用户*/
	@NotEmpty
	private Integer uoldPwd;
	/**姓别（0 男，1 女）*/
	@NotEmpty
	private Integer usex;
	/**
	 * 选择模块
	 */
	private Integer uUseModule;
	/**数据行版本本号*/
	@NotEmpty
	private Integer version;
	//columns END

	public User(){
	}

	public User( Long uid){
		this.uid = uid;
	}

	public void setUid(Long value) {
		this.uid = value;
	}

	public Long getUid() {
		return this.uid;
	}
	public void setUrecommendId(Long value) {
		this.urecommendId = value;
	}

	public Long getUrecommendId() {
		return this.urecommendId;
	}
	public void setUaccount(String value) {
		this.uaccount = value;
	}

	public String getUaccount() {
		return this.uaccount;
	}
	public void setUphone(String value) {
		this.uphone = value;
	}

	public String getUphone() {
		return this.uphone;
	}
	public void setUemail(String value) {
		this.uemail = value;
	}

	public String getUemail() {
		return this.uemail;
	}
	public void setUname(String value) {
		this.uname = value;
	}

	public String getUname() {
		return this.uname;
	}
	public void setUpwd(String value) {
		this.upwd = value;
	}

	public String getUpwd() {
		return this.upwd;
	}
	public void setUpayPwd(String value) {
		this.upayPwd = value;
	}

	public String getUpayPwd() {
		return this.upayPwd;
	}
	public void setUimgid(Long value) {
		this.uimgid = value;
	}

	public Long getUimgid() {
		return this.uimgid;
	}
	public void setUtype(Integer value) {
		this.utype = value;
	}

	public Integer getUtype() {
		return this.utype;
	}
	public void setUfirstCommission(Long value) {
		this.ufirstCommission = value;
	}

	public Long getUfirstCommission() {
		return this.ufirstCommission;
	}
	public void setUrecommendCommission(Long value) {
		this.urecommendCommission = value;
	}

	public Long getUrecommendCommission() {
		return this.urecommendCommission;
	}
	public void setUrechage(Long value) {
		this.urechage = value;
	}

	public Long getUrechage() {
		return this.urechage;
	}
	public void setUstatus(Integer value) {
		this.ustatus = value;
	}

	public Integer getUstatus() {
		return this.ustatus;
	}
	public void setIsDelete(Integer value) {
		this.isDelete = value;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}
	public void setUcreateTime(Date value) {
		this.ucreateTime = value;
	}

	public Date getUcreateTime() {
		return this.ucreateTime;
	}
	public void setUisOld(Integer value) {
		this.uisOld = value;
	}

	public Integer getUisOld() {
		return this.uisOld;
	}
	public void setUoldPwd(Integer value) {
		this.uoldPwd = value;
	}

	public Integer getUoldPwd() {
		return this.uoldPwd;
	}
	public void setUsex(Integer value) {
		this.usex = value;
	}

	public Integer getUsex() {
		return this.usex;
	}

	public Integer getuUseModule() {
		return uUseModule;
	}

	public void setuUseModule(Integer uUseModule) {
		this.uUseModule = uUseModule;
	}

	public void setVersion(Integer value) {
		this.version = value;
	}

	public Integer getVersion() {
		return this.version;
	}


}
