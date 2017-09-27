package com.retailers.auth.vo;

/**
 * Created by zpapj on 2017/9/15.
 */
public class MenuVo {
    /** 菜单id */
    private Long id;
    /** 父级菜单id */
    private Long parentId;
    /** 菜单类型（0资源 ，1 菜单，2 功能按钮) */
    private Long type;
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
    private Long level;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
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

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
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
}
