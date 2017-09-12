package com.retailers.tools.utils;

import javax.sql.rowset.serial.SerialClob;
import java.io.Reader;
import java.sql.Clob;


public class ClobUtil {
	/**
	 * 将Clob转成String
	 *
	 * @param clob
	 * @return
	 */
	public static String clobToString(Clob clob) {
		if (ObjectUtils.isEmpty(clob))
			return null;
		StringBuffer sb = new StringBuffer();
		Reader clobStream = null;
		try {
			clobStream = clob.getCharacterStream();
			char[] b = new char[1024];// 每次获取1K
			int i = 0;
			while ((i = clobStream.read(b)) != -1) {
				sb.append(b, 0, i);
			}
		} catch (Exception ex) {
			sb = null;
		} finally {
			try {
				if (!ObjectUtils.isEmpty(clobStream)) {
					clobStream.close();
				}
			} catch (Exception e) {
			}
		}
		if (ObjectUtils.isEmpty(sb))
			return null;
		else
			return sb.toString();
	}

	/**
	 * 将String转成Clob
	 *
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static Clob stringToClob(String str) throws Exception {
		if (ObjectUtils.isEmpty(str))
			return null;

		return new SerialClob(str.toCharArray());
	}
}
