package com.retailers.mybatis.pagination;

/**
 * Created by admin on 2016/12/14.
 */
public class PageFrom {
    //起始行
    private Integer iDisplayStart;
    //每页行数
    private Integer iDisplayLength;
    //标志
    private String sEcho;


    public Integer getiDisplayStart() {
        return iDisplayStart;
    }
    public void setiDisplayStart(Integer iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }
    public Integer getiDisplayLength() {
        return iDisplayLength;
    }
    public void setiDisplayLength(Integer iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }
    public String getsEcho() {
        return sEcho;
    }
    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }
}
