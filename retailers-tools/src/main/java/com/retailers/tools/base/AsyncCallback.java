package com.retailers.tools.base;


import com.retailers.tools.exception.AppException;

/**
 * Created by admin on 2017/8/15.
 */
public interface AsyncCallback {
    public void callback(String context)throws AppException,Exception;
}
