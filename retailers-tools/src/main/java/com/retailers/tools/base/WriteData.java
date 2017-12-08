package com.retailers.tools.base;


import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;


public class WriteData {
	//参数错误
	public static final int PARAM_ERROR = 1;
	//系统错误
	public static final int SYSTEM_ERROR = -1;
	//服务器错误
	public static final int SERVER_ERROR = 2;
	/**
	 * 未登录用户
	 */
	public static final int LOGIN_OUT=3;
	//成功
	public static final int SUCCESS = 0;
	//验证码错误
	public static final int VALIDATE_CODE_ERROR=9;
	/**
	 * 权限认证未通过
	 */
	public static final int SC_UNAUTHORIZED=401;
	public static final String MSG_SUCCESS = "SUCCESS";

	public static void write(String data,HttpServletResponse response) {
		try {
			response.reset();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getOutputStream().write(data.toString().getBytes("UTF-8"));
			if (null != response.getOutputStream()) {
				response.getOutputStream().close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void writeObject(int status,String msg,Object object,HttpServletResponse response) {
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("msg", msg);
		if (null == object) {
			json.put("data", new JSONObject());
		} else {
			json.put("data", object);
		}
		write(json.toString(), response);
	}
//	public static void writeObjects(int status,String msg,Object object,HttpServletResponse response) {
//		JSONObject json = new JSONObject();
//		json.put("status", status + "");
//		json.put("msg", msg);
//		if (null == object) {
//			json.put("data", new JSONObject());
//		} else {
//			json.put("data", object);
//		}
//		write(json.toString(), response);
//	}
	public static void paramError(HttpServletResponse response) {
		writeObject(PARAM_ERROR, "获取参数错误!", new JSONObject(), response);
	}
	public static void paramError(String msg,HttpServletResponse response) {
		writeObject(PARAM_ERROR, msg, new JSONObject(), response);
	}
	public static void systemError(HttpServletResponse response) {
		writeObject(SYSTEM_ERROR, "系统错误,请进行其他操作!", new JSONObject(), response);
	}
	public static void serverError(String msg,HttpServletResponse response) {
		writeObject(SERVER_ERROR, msg, new JSONObject(), response);
	}
	public static void writeSuccess(JSONObject json,HttpServletResponse response) {
		writeObject(SUCCESS, MSG_SUCCESS, json, response);
	}
	/**
	 * 用户未通过 权限校验
	 * @param msg
	 * @param response
	 * @throws Exception
	 */
	public static void authError(String msg, HttpServletResponse response)throws Exception{
		writeObject(SC_UNAUTHORIZED,msg,null,response);
	}
}
