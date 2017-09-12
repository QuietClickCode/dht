package com.retailers.tools.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyUtil {
	/**数字正则表达式*/
	public static final String REGEX_NUMBER = "^[0-9]*$";
	public static final String REGEX_PASS = "^[A-Za-z0-9]+$";

	/**
	 * 方法说明:是否是空值<br>
	 * @param str
	 * @return
	 * @author exu
	 * @date 2014-10-16下午4:07:46
	 */
	public static boolean isEmpty(String str) {
		return null == str || str.trim().length() <= 0;
	}
	/**
	 * 方法说明:是否是空值<br>
	 * @param str
	 * @return
	 * @author exu
	 * @date 2014-10-16下午4:07:46
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 方法说明:是否是数字<br>
	 * @param str
	 * @return
	 * @author exu
	 * @date 2014-10-16下午4:07:46
	 */
	public static boolean isNumber(String str) {
		if (isNotEmpty(str)) {
			return Pattern.matches(REGEX_NUMBER, str);
		}
		return false;
	}

	/**
	 * 是否有标点符号
	 * @param str
	 * @return true，有，false，没得
	 */
	public static boolean isHavePunctuation(String str) {
		if (isNotEmpty(str)) {
			return !Pattern.matches(REGEX_PASS, str);
		}
		return false;
	}
	/**
	 * 验证银行卡
	 * @param bankno
	 * @return
	 */
	public static boolean isBankno (String bankno) {
		if (isNotEmpty(bankno)) {
			String regex = "\\d{12,21}";
			return Pattern.matches(regex, bankno);
		}
		return false;
	}
	/**
	 * 验证身份证号
	 * @param licenc
	 * @return true 合法生分证号
	 */
	public static boolean isIdentityCard(String licenc) {
		boolean flag = true;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		/*
		 * { 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
		 * 21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",
		 * 33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",
		 * 42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",
		 * 51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",
		 * 63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外" }
		 */
		String provinces = "11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91";

		Pattern pattern = Pattern.compile("^[1-9]\\d{14}");
		Matcher matcher = pattern.matcher(licenc);
		Pattern pattern2 = Pattern.compile("^[1-9]\\d{16}[\\d,x,X]$");
		Matcher matcher2 = pattern2.matcher(licenc);
		// 粗略判断
		if (!matcher.find() && !matcher2.find()) {
			flag = false;
		} else {
			// 判断出生地
			if (provinces.indexOf(licenc.substring(0, 2)) == -1) {
				flag = false;
			}

			// 判断出生日期
			if (licenc.length() == 15) {
				String birth = "19" + licenc.substring(6, 8) + "-"
						+ licenc.substring(8, 10) + "-"
						+ licenc.substring(10, 12);
				try {
					Date birthday = sdf.parse(birth);
					if (!sdf.format(birthday).equals(birth)) {
						flag = false;
					}
					if (birthday.after(new Date())) {
						flag = false;
					}
				} catch (ParseException e) {
					flag = false;
				}
			} else if (licenc.length() == 18) {
				String birth = licenc.substring(6, 10) + "-"
						+ licenc.substring(10, 12) + "-"
						+ licenc.substring(12, 14);
				try {
					Date birthday = sdf.parse(birth);
					if (!sdf.format(birthday).equals(birth)) {
						flag = false;
					}
					if (birthday.after(new Date())) {
						flag = false;
					}
				} catch (ParseException e) {
					flag = false;
				}
			} else {
				flag = false;
			}
		}

		return flag;
	}
	/**
	 * 是否是邮箱
	 * @param email
	 * @return
	 */
	public static boolean isEmail (String email) {
		if (null != email) {
			String regex = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
			return email.matches(regex);
		}
		return false;
	}

	/**
	 * 是否是时间
	 * @param date
	 * @return
	 */
	public static boolean isDateTime(String date) {
		return isDateTime(date,DateUtil.DATE_WITHSECOND_FORMAT);
	}
	/**
	 * 是否是时间格式
	 * @param str 字符串时间
	 * @param pattern 时间格式
	 * @return
	 */
	public static boolean isDateTime(String str, String pattern) {
		DateFormat formatter = new SimpleDateFormat(pattern);
		try {
			Date date = formatter.parse(str);
			return str.equals(formatter.format(date));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 截取附件根路径
	 * @return
	 */
	public static String subFileRootPath(String filePath,String resPath) {
		if (isEmpty(filePath)) {
			return "";
		}
//		int index = filePath.indexOf(resPath);
//		while (index!=-1) {
//			filePath = filePath.substring(filePath.length()-resPath.length()+1);
//			index = filePath.indexOf(resPath);
//
//		}
		filePath = filePath.replaceAll(resPath,"");
//		int index = filePath.indexOf(resPath);
//		if (index != -1) {
//			filePath = filePath.substring(resPath.length());
//		}
		return filePath;
	}
	/**
	 * 取得全路径
	 * @param filePath
	 * @return
	 */
	public static String fullPath(String filePath,String resPath){
		if(ObjectUtils.isEmpty(filePath)){
			return "";
		}
		if(filePath.indexOf(resPath)>=0){
			filePath = subFileRootPath(filePath, resPath);
		}
		return resPath+filePath;
	}

}
