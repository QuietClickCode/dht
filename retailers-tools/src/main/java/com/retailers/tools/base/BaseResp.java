package com.retailers.tools.base;

/**
 * 基础返回类
 * Created by Administrator on 2014/10/29.
 */
public class BaseResp {
    /**
     * 编码
     */
    private int status;
    /**
     * 信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
