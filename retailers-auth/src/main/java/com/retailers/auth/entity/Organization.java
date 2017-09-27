package com.retailers.auth.entity;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
/**
 * 描述：组织结构对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-19 14:32:28
 */
public class Organization implements java.io.Serializable {

	/**组织结构id*/
	private Long orgId;
	/**组织结构名称*/
	@NotEmpty(message = "部门名称不能为空")
	private String orgName;
	/**上级组织*/
	private Long orgPid;
	/**组织结构描述*/
	private String orgDes;
	/**
	 * 显示顺序
	 */
	private Integer orgSort;
	/**是否启用（0 启用，1 未启用）*/
	private Integer isValid;
	/**是否删除（0 正常，1 删除）*/
	private Integer isDelete;
	/**数据版本*/
	private Integer version;
	//columns END

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getOrgPid() {
		return orgPid;
	}

	public void setOrgPid(Long orgPid) {
		this.orgPid = orgPid;
	}

	public String getOrgDes() {
		return orgDes;
	}

	public void setOrgDes(String orgDes) {
		this.orgDes = orgDes;
	}

	public Integer getOrgSort() {
		return orgSort;
	}

	public void setOrgSort(Integer orgSort) {
		this.orgSort = orgSort;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
