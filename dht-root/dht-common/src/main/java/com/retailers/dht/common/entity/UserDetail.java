package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：用户信息详情表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-02-07 17:37:18
 */
public class UserDetail implements java.io.Serializable {

	/**用户信息详情id*/
	@NotEmpty
	private Long udId;
	/**用户id*/
	@NotEmpty
	private Long uid;
	/**用户真实姓名*/
	@NotEmpty
	@Length(min = 1, max = 10)
	private String udName;
	/**用户性别*/
	@NotEmpty
	private Long udSex;
	/**用户手机*/
	@NotEmpty
	@Length(min = 1, max = 11)
	private String udPhone;
	/**身份证号码*/
	@NotEmpty
	@Length(min = 1, max = 18)
	private String udIdCard;
	/**是否删除 0不删除 1删除*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public UserDetail(){
	}

	public UserDetail( Long udId){
		this.udId = udId;
	}

	public void setUdId(Long value) {
		this.udId = value;
	}

	public Long getUdId() {
		return this.udId;
	}
	public void setUid(Long value) {
		this.uid = value;
	}

	public Long getUid() {
		return this.uid;
	}
	public void setUdName(String value) {
		this.udName = value;
	}

	public String getUdName() {
		return this.udName;
	}
	public void setUdSex(Long value) {
		this.udSex = value;
	}

	public Long getUdSex() {
		return this.udSex;
	}
	public void setUdPhone(String value) {
		this.udPhone = value;
	}

	public String getUdPhone() {
		return this.udPhone;
	}
	public void setUdIdCard(String value) {
		this.udIdCard = value;
	}

	public String getUdIdCard() {
		return this.udIdCard;
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
