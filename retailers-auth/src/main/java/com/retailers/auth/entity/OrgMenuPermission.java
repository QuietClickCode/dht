package com.retailers.auth.entity;
/**
 * 描述：菜单部门权限对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-21 17:09:47
 */
public class OrgMenuPermission implements java.io.Serializable {

	/**omId*/
	private Long omId;
	/**rsId*/
	private Long rsId;
	/**orgId*/
	private Long orgId;
	//columns END

	public OrgMenuPermission(){
	}

	public OrgMenuPermission( Long omId){
		this.omId = omId;
	}

	public void setOmId(Long value) {
		this.omId = value;
	}

	public Long getOmId() {
		return this.omId;
	}
	public void setRsId(Long value) {
		this.rsId = value;
	}

	public Long getRsId() {
		return this.rsId;
	}
	public void setOrgId(Long value) {
		this.orgId = value;
	}

	public Long getOrgId() {
		return this.orgId;
	}


}
