package com.retailers.auth.bean;

public class ResBean {
	/** 菜单id */
	private Long id;
	/** 父级菜单id */
	private String parentRes;
	/** 资源名称 */
	private String resourse;
	/** 菜单名称 */
	private String label;
	/** 图片地址（相对） */
	private String icon;
	/** 是否有效 */
	private int isValid;
	/** 菜单描述 */
	private String description;
	/** 菜单排序 */
	private int sort;
	/**
	 * 变更新的资源路径
	 */
	private String changeRes;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParentRes() {
		return parentRes;
	}

	public void setParentRes(String parentRes) {
		this.parentRes = parentRes;
	}

	public String getResourse() {
		return resourse;
	}

	public void setResourse(String resourse) {
		this.resourse = resourse;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getChangeRes() {
		return changeRes;
	}

	public void setChangeRes(String changeRes) {
		this.changeRes = changeRes;
	}

}
