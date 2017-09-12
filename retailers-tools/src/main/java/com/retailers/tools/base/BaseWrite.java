package com.retailers.tools.base;

import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.SpringUtils;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * 基础返回类
 * Created by Administrator on 2014/10/29.
 */
public class BaseWrite {
	/**
	 * 系统错误
	 * @param msg
	 * @return
	 */
	public BaseResp errorForSystem(String msg) {
		BaseResp baseResp = new BaseResp();
		baseResp.setStatus(WriteData.SYSTEM_ERROR);
		baseResp.setMsg(getMessage(msg));
		return baseResp;
	}
	/**
	 * 系统错误
	 * @param msg
	 * @return
	 */
	public BaseResp errorLockShop(String msg) {
		BaseResp baseResp = new BaseResp();
		baseResp.setStatus(WriteData.LOCK_SHOP_ERROR);
		baseResp.setMsg(getMessage(msg));
		return baseResp;
	}

	/**
	 * 参数错误
	 * @param msg
	 * @param info
	 * @return
	 */
	public BaseResp errorForParam(String msg, Object... info) {
		BaseResp baseResp = new BaseResp();
		baseResp.setStatus(WriteData.PARAM_ERROR);
		baseResp.setMsg(getMessage(msg, info));
		return baseResp;
	}

	/**
	 * 业务错误
	 * @param msg
	 * @param info
	 * @return
	 */
	public BaseResp errorForServer(String msg, Object... info) {
		BaseResp baseResp = new BaseResp();
		baseResp.setStatus(WriteData.SERVER_ERROR);
		baseResp.setMsg(getMessage(msg, info));
		return baseResp;
	}

	public BaseResp success(Object obj) {
		BaseResp baseResp = new BaseResp();
		baseResp.setStatus(WriteData.SUCCESS);
		baseResp.setMsg("SUCCESS");
		if (obj instanceof String) {
			if (!ObjectUtils.isEmpty(obj)) {
				baseResp.setMsg(getMessage(obj.toString()));
			}
		} else {
			baseResp.setData(obj);
		}
		return baseResp;
	}

	/**
	 * 取得国际化消息
	 * @param msg
	 * @param info
	 * @return
	 */
	private String getMessage(String msg, Object... info) {
		if(ObjectUtils.isEmpty(msg)){
			return "";
		}
		if (msg.indexOf("{") >= 0 && msg.indexOf("}") >= 0) {
			MessageSource messageSource = SpringUtils.getBean("messageSource");
			msg = msg.replace("{", "").replace("}", "");
			Locale locales = new Locale("zh", "CN");
			return messageSource.getMessage(msg, info, msg, locales);
		} else {
			return msg;
		}
	}

}
