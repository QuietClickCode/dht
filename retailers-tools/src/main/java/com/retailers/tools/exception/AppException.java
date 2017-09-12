package com.retailers.tools.exception;

/**
 *自定义异常
 * @author exu
 * @date 2015-07-06
 */
@SuppressWarnings("serial")
public class AppException extends Exception {

	private String message;
    private Integer status;
    private String code;
    private Object data;
    public AppException(String message) {
        super(message);
        this.message = message;
    }

    public AppException(String message, String code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public AppException(String message, Integer status, Object data) {
        super(message);
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

	public String getCode() {
		return code;
	}

    public String getMessage() {
        return message;
    }
}
