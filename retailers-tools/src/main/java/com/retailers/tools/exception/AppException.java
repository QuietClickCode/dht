package com.retailers.tools.exception;

/**
 *自定义异常
 * @author exu
 * @date 2015-07-06
 */
@SuppressWarnings("serial")//这个是压制警告注解
public class AppException extends Exception {

	private String message;//消息属性
    private Integer status;//状态属性
    private String code;//状态码
    private Object data;//数据
    public AppException(String message) {//构造方法,多个重载
        super(message);
        this.message = message;
    }

    public AppException(String code,String message) {//构造方法,多个重载
        super(message);
        this.message = message;
        this.code = code;
    }

    public AppException(String message, Integer status, Object data) {//构造方法,多个重载
        super(message);
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }//封装类,获取属性值的方法

    public Object getData() {
        return data;
    }//封装类,获取属性值的方法

	public String getCode() {
		return code;
	}//封装类,获取属性值的方法

    public String getMessage() {
        return message;
    }//封装类,获取属性值的方法
}
