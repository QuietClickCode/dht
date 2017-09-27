package com.retailers.auth.exception;

/**
 * Created by admin on 2017/5/3.
 */
public class RedirectException extends Exception {
    /**
     * 跳转路径
     */
    private String redirect;
    /**
     * 消息
     */
    private String message;

    public RedirectException(String message) {
        super(message);
        this.message = message;
    }
    public RedirectException(String message,String redirect){
        super(message);
        this.message = message;
        this.redirect = redirect;
    }
    public String getRedirect() {
        return redirect;
    }
    public String getMessage() {
        return message;
    }

}
