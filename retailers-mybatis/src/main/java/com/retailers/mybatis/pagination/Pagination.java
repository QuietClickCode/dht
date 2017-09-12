package com.retailers.mybatis.pagination;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 分页类
 * Created by Administrator on 2014/10/21.
 */
public class Pagination<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8329425768093103284L;

    /**
     * 总条数
     */
    private int totalCount;
    /**
     * 当前页
     */
    private int pageNo = 1;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 一页显示几条
     */
    private int pageSize = 10;
    /**
     * 数据集
     */
    private List<T> data = new LinkedList<T>();
    /**
     * 其他的参数我们把它分装成一个Map对象
     */
    private Map<String, Object> params = new HashMap<String, Object>();

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        if (pageNo < 1) {
            pageNo = 1;
        }
        this.pageNo = pageNo;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
    /**
     * 开始条数
     * @return
     */
    public int getStartNo(){
    	return (getPageNo()-1)*getPageSize();
    }
    /**
     * 结束 条数
     * @return
     */
    public int getEndNo(){
    	return getPageNo()*getPageSize();
    }
}
