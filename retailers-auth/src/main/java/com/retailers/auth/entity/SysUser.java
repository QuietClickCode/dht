package com.retailers.auth.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
/**
 * 描述：公司员工表对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-22 17:51:06
 */
public class SysUser implements java.io.Serializable {

	/**uid*/
	private Long uid;
	/**登录帐号*/
	@NotEmpty(message = "登录账号不能为空")
	@Length(min = 4,max = 30,message = "职工登录账号长度在{min}-{max}之间")
	private String uaccount;
	/**用户名*/
	@NotEmpty(message = "职工姓名不能为空")
	@Length(min = 2,max = 10,message = "职工登录账号长度在{min}-{max}之间")
	private String uname;
	/**密码*/
	private String upassword;
	/**创建时间*/
	private Date ucreateTime;
	/**用户状态（0 启用，1 停用）*/
	private Long isValid;
	/**是否删除（0 未删除，1 删除）*/
	private Long isDelete;
	/**数据乐观锁*/
	private Long version;
	//columns END

	public SysUser(){
	}

	public SysUser( Long uid){
		this.uid = uid;
	}

	public void setUid(Long value) {
		this.uid = value;
	}

	public Long getUid() {
		return this.uid;
	}
	public void setUaccount(String value) {
		this.uaccount = value;
	}

	public String getUaccount() {
		return this.uaccount;
	}
	public void setUname(String value) {
		this.uname = value;
	}

	public String getUname() {
		return this.uname;
	}
	public void setUpassword(String value) {
		this.upassword = value;
	}

	public String getUpassword() {
		return this.upassword;
	}
	public void setUcreateTime(Date value) {
		this.ucreateTime = value;
	}

	public Date getUcreateTime() {
		return this.ucreateTime;
	}
	public void setIsValid(Long value) {
		this.isValid = value;
	}

	public Long getIsValid() {
		return this.isValid;
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


}
