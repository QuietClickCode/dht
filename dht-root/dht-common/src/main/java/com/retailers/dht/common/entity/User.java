package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：用户表对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-22 22:13:51
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
	private Integer uimgid;
	/**用户类型*/
	@NotEmpty
	private Integer utype;
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
	public void setUimgid(Integer value) {
		this.uimgid = value;
	}

	public Integer getUimgid() {
		return this.uimgid;
	}
	public void setUtype(Integer value) {
		this.utype = value;
	}

	public Integer getUtype() {
		return this.utype;
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
	public void setVersion(Integer value) {
		this.version = value;
	}

	public Integer getVersion() {
		return this.version;
	}


}
