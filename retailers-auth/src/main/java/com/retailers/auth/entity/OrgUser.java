package com.retailers.auth.entity;

/**
 * 描述：部门人员表对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-22 17:54:20
 */
public class OrgUser implements java.io.Serializable {

	/**ouId*/
	private Long ouId;
	/**部门id*/
	private Long ouOrgId;
	/**用户id*/
	private Long ouSid;
	//columns END

	public OrgUser(){
	}

	public OrgUser( Long ouId){
		this.ouId = ouId;
	}

	public void setOuId(Long value) {
		this.ouId = value;
	}

	public Long getOuId() {
		return this.ouId;
	}
	public void setOuOrgId(Long value) {
		this.ouOrgId = value;
	}

	public Long getOuOrgId() {
		return this.ouOrgId;
	}
	public void setOuSid(Long value) {
		this.ouSid = value;
	}

	public Long getOuSid() {
		return this.ouSid;
	}


}
