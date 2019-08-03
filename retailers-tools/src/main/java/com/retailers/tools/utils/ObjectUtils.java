package com.retailers.tools.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectUtils {//通用的工具类


	public static void main(String[] p) {
		List<String> list =new ArrayList<String>();
		Map map =new HashMap();
		 map =new TreeMap();
		Set set=new HashSet();
	}

	/**
	 * 判断两个对象是否相等
	 * @param object1
	 * @param object2
	 * @return
	 */
	public static boolean isEquals(Object object1, Object object2) {//判断两个对象是否相等
		boolean ret = false;
		try {
			if (object1 == null && object2 == null) {//两个都为空的话,返回true
				ret = true;
				return ret;
			}
			ret = object1.equals(object2);//否则,就直接调用java.lang包下的equals判断
		} catch (NullPointerException e) {
			ret = false;//抛出空指针直接返回false
		}
		return ret;

	}

	/**
	 * 比较两字符串是否相等，忽略大小写
	 *
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean equalsIgnorecase(String s1, String s2) {
		if (s1 == null && s2 == null)
			return true;
		if (s1 != null && s2 != null) {
			if (s1.toLowerCase().equals(s2.toLowerCase()))
				return true;
		}
		return false;
	}

	/**
	 * 判断对象是否为空
	 *
	 * @param obj
	 *            -参数对象
	 * @return boolean -true:表示对象为空;false:表示对象为非空 集合：
	 * 		   Collection.isEmpty()
	 *         数组：判断数组每个元素，所有元素都为空，即判定数组为空
	 *         字符串：判断字符串等于"null"，或去除两端""字窜后返回String.isEmpty()的结果 其它类型返回 false
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null)
			return true;
		if (obj instanceof Collection) {
			return ((Collection<?>) obj).isEmpty();
		}
		if (obj instanceof HashMap) {
			return ((HashMap<?,?>) obj).isEmpty();
		}
		if (obj instanceof TreeMap) {
			return ((TreeMap<?,?>) obj).isEmpty();
		}
		if (obj instanceof String) {
			return ((String) obj).equalsIgnoreCase("null") | ((String) obj).trim().isEmpty();
		}

		if (obj instanceof StringBuffer) {
			return ((StringBuffer) obj).length() == 0;
		}

		if (obj.getClass().isArray()) {
			try {
				Object[] a = (Object[]) obj;

				boolean b = true;
				for (Object o : a) {
					b = b & ObjectUtils.isEmpty(o);

					if (!b)
						break;
				}

				return b;
			} catch (ClassCastException e) {
			}
		}

		return false;
	}

	/**
	 *判断是否不为空,跟isNotEmpty相反
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	/**
	 * 校验邮箱是否合规
	 *
	 * @param email
	 *            邮箱
	 * @return true 合规 false 不合规
	 */
	public static boolean isEmailFormat(String email) {

		Pattern pattern = Pattern
				.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean isEmailAdressFormat(String email) {
		boolean isExist = false;
		if (isEmpty(email)) {
			return isExist;
		}
		Pattern p = Pattern.compile("\\w+@(\\w+\\.)+[a-z]{2,3}");
		Matcher m = p.matcher(email);
		boolean b = m.matches();
		if (b) {
			isExist = true;
		}
		return isExist;
	}
	//判断是否相等
	public static boolean compare(Long id,Long id1){
		if(ObjectUtils.isNotEmpty(id)&&ObjectUtils.isNotEmpty(id1)){
			if(id.intValue()!=id1.intValue()){
				return false;
			}
		}else if(ObjectUtils.isEmpty(id)&&ObjectUtils.isEmpty(id1)){
			return true;
		}else{
			return false;
		}
		return true;
	}



}
