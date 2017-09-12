package com.retailers.tools.base;


import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;


public class WriteData {
	public static final int PARAM_ERROR = 1;
	public static final int SYSTEM_ERROR = -1;
	public static final int SERVER_ERROR = 2;
	//单点登录,注销账号，锁定账号
	public static final int SERVER_ERROR_LOGIN = 3;
	//非信用用户支付
	public static final int SERVER_ERROR_CREDIT = 4;
	//app版本不可使用
	public static final int VERSION_NOT_USE = 5;
	//锁定商品异常
	public static final int LOCK_SHOP_ERROR=6;
	public static final int SUCCESS = 0;
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
		json.put("status", status + "");
		json.put("msg", msg);
		if (null == object) {
			json.put("data", new JSONObject());
		} else {
			json.put("data", object);
		}
		write(json.toString(), response);
	}
	public static void writeObjects(int status,String msg,Object object,HttpServletResponse response) {
		JSONObject json = new JSONObject();
		json.put("status", status + "");
		json.put("msg", msg);
		if (null == object) {
			json.put("data", new JSONObject());
		} else {
			json.put("data", object);
		}
		write(json.toString(), response);
	}
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
	public static void versionNotUse(HttpServletResponse response) {
		writeObject(VERSION_NOT_USE, "该版本过低，请下载最新版本!", new JSONObject(), response);
	}
	public static void writeSuccess(JSONObject json,HttpServletResponse response) {
		writeObject(SUCCESS, MSG_SUCCESS, json, response);
	}

}
