package com.retailers.tools.http;

import com.retailers.tools.exception.AppException;
import org.apache.http.client.CookieStore;

/**
 * http 请求连接后的回传值 和cookies 信息
 * @author zhongp
 */
public interface HttpConnectionCallBack {
	/**
	 * 请求连接回传值
	 * @param obj 得到的请示数据
	 * @param cookieStore cookie信息
	 */
	public Object callback(String obj, CookieStore cookieStore) throws AppException;
}
