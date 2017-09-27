package com.retailers.auth.vo;

/**
 * ztree 树型结构展示状态
 * @author zhongp
 * @version 1.0.1
 */
public class ZTreeVo {
    /**
     * 主建id
     */
    private Long id;
    /**
     * 上级菜单id
     */
    private Long pId;
    /**
     * 节点名称
     */
    private String name;
    /**
     * 是否选中
     */
    private boolean checked;
//    /**
//     * 是否删除
//     */
//    private Long isDelete;
//    /**
//     * 是否有效
//     */
//    private Long isValid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
