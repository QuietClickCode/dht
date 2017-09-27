package com.retailers.auth.entity;

import java.util.ArrayList;
import java.util.List;


/**
 * Menu对象
 *
 * @author zhongp
 * @version 1.0
 * @date 2016-06-07
 */
public class Menu implements Comparable<Menu>{

	/** 菜单id */
	private Integer id;
	/** 父级菜单id */
	private Integer parentId;
	/** 菜单类型（0资源 ，1 菜单，2 功能按钮) parseId*/
	private Integer type;
	/** 资源名称 */
	private String resourse;
	/** 菜单的url */
	private String url;
	/** 菜单名称 */
	private String label;
	/** 图片地址（相对） */
	private String icon;
	/** 是否有效 */
	private Integer isValid;
	/** 菜单描述 */
	private String description;
	/** 菜单排序 */
	private Integer sort;
	/**
	 * 是否删除（0 未删除，1 删除）
	 */
	private Long isDelete;
	/**是否变更（前端是否变更过资源属性，如果己变更则以前端口为准）*/
	private Long isChange;
	/**
	 * 数据版本号
	 */
	private Long version;

	private List<Menu> child=new ArrayList<Menu>();

	public Menu() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getResourse() {
		return resourse;
	}

	public void setResourse(String resourse) {
		this.resourse = resourse;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
	}

	public Long getIsChange() {
		return isChange;
	}

	public void setIsChange(Long isChange) {
		this.isChange = isChange;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public List<Menu> getChild() {
		return child;
	}

	public void setChild(List<Menu> child) {
		this.child = child;
	}

	public int compareTo(Menu m) {
		return this.getSort()-m.getSort();
	}
}
