package com.retailers.tools.utils;

/**
 * bootstrap 表格传入分页信息
 */
public class PageUtils {
    /**
     * 起始行
     */
    private Integer pageSize;
    /**
     * 显示行数
     */
    private Integer pageNo;
    /**
     * 排序类型（desc asc)
     */
    private String order;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
