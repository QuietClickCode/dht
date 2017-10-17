package com.retailers.tools.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
	static  Set<String> set = new HashSet<String>();
	public static void main(String[] args) {
		for(int i=0;i<1000000;i++){
			set.add(getUUID());
		}
		System.out.println(set.size());
	}
}
