package com.retailers.tools.utils;

import java.util.UUID;

public class UUIDUtils {
	/**
	 * 取得唯一的UUID码
	 * @return
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		return str.replaceAll("-", "");
	}
}
