package com.retailers.tools.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtils {
	private static Random random=new Random();
	/**
	 * 判断str是否数值类型
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[+,-]?[\\d.]+");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}


	/**
	 * 判断str是否数值类型
	 *
	 * @param str
	 * @return
	 */
	public static boolean isLong(String str) {
		Pattern pattern = Pattern.compile("[+,-]?[\\d]+");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	/**
	 * 小数四舍五入
	 * @param number
	 * @param decimalPlaces
	 *            小数位数
	 * @return
	 */
	public static double formaterNumber(double number, int decimalPlaces) {
		if (ObjectUtils.isNotEmpty(number)) {
			BigDecimal bigDecimal = new BigDecimal(number);
			double res = Double.parseDouble(bigDecimal.setScale(decimalPlaces,
					BigDecimal.ROUND_HALF_UP).toString());
			return res;
		}
		return 0;
	}
	/**
	 * 小数四舍五入
	 * @param number
	 *            小数位数
	 * @return
	 */
	public static long formaterNumber(double number) {
		if (ObjectUtils.isNotEmpty(number)) {
			BigDecimal bigDecimal = new BigDecimal(number);
			Long res = Long.parseLong(bigDecimal.setScale(0,
					BigDecimal.ROUND_HALF_UP).toString());
			return res;
		}
		return 0;
	}
	/**
	 * 小数四舍五入
	 * @param number
	 *            小数位数
	 * @return
	 */
	public static long formaterNumberDown(double number) {
		if (ObjectUtils.isNotEmpty(number)) {
			double num=formaterNumber(number,10);
			return (long)num;
		}
		return 0;
	}
	/**
	 * 把num转成指定长度的字符串
	 *
	 * @param num
	 *            要转换的数字
	 * @param length
	 *            转换后的字符串长度
	 *
	 * @return
	 */
	public static String numericToString(Integer num, Integer length) {
		String fmt = "0";

		for (int i = 0 ; i < length - 1; i ++) {
			fmt += "0";
		}

		DecimalFormat df = new DecimalFormat(fmt);

		return df.format(num);
	}

	/**
	 * 把num转成指定长度的字符串
	 *
	 * @param num
	 *            要转换的数字
	 * @param length
	 *            转换后的字符串长度
	 *
	 * @return
	 */
	public static String numericToString(Long num, Integer length) {
		String fmt = "0";

		for (int i = 0 ; i < length - 1; i ++) {
			fmt += "0";
		}

		DecimalFormat df = new DecimalFormat(fmt);

		return df.format(num);
	}

	/**
	 * 返回精度为小数点后两位的double数据
	 *
	 * @param divisor
	 * @param dividend
	 * @return
	 */
	public static String getDivision(String divisor, int dividend) {
		if (dividend == 0) {
			return "0.00";
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(Double.parseDouble(divisor) / dividend);
	}

	/**
	 * 判断str是否数字字符
	 *
	 * @param str
	 * @return
	 */
	public static boolean isDigit(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();

	}

	/**
	 * 价格元换成分为单位
	 * @param price
	 * @return
	 */
	public static long priceChangeFen(Double price){
		if(ObjectUtils.isNotEmpty(price)){
			BigDecimal b1 = new BigDecimal(Double.toString(price));
			return b1.multiply(new BigDecimal(100)).longValue();
		}else{
			return 0;
		}
	}
	/**
	 * 价格分换成元为单位
	 * @param price
	 * @return
	 */
	public static double priceChangeYuan(Long price){
		if(ObjectUtils.isNotEmpty(price)){
			return ((double)price)/100;
		}else{
			return 0d;
		}
	}
	/**
	 * 价格分换成元为单位
	 * @param price
	 * @return
	 */
	public static double priceChangeYuan(Double price){
		if(ObjectUtils.isNotEmpty(price)){
			return price/100;
		}else{
			return 0d;
		}
	}
	/**
	 * 判断double是否2位精度
	 * @param price
	 * @return
	 */
	public static boolean isDouble2Accuracy(double price) {
		String prices = price + "";
		int index = prices.lastIndexOf(".");
		if (index > -1) {
			String suffix = prices.substring(index+1);
			if (suffix.length() > 2) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 舍掉第三位
	 * @param number 小数位数
	 * @return
	 */
	public static double formaterNumbers(double number) {
		if (ObjectUtils.isNotEmpty(number)) {
			BigDecimal bigDecimal = new BigDecimal(number + "");
			double res = Double.parseDouble(bigDecimal.setScale(2,
					BigDecimal.ROUND_DOWN).toString());
			return res;
		}
		return 0;
	}

	/**
	 * 小数收取第三位
	 * @param number
	 * @return
	 */
	public static double formaterNumberr(double number) {
		if (ObjectUtils.isNotEmpty(number)) {
			BigDecimal bigDecimal = new BigDecimal(number+"");
			double res = Double.parseDouble(bigDecimal.setScale(2,
					BigDecimal.ROUND_UP).toString());
			return res;
		}
		return 0;
	}
	public static String formaterNumberrStr(double number) {
		String STR_FORMAT = "##0.00";
		DecimalFormat df = new DecimalFormat(STR_FORMAT);
		return  df.format(number);
	}

	public static String formaterNumberrDoulbe(Double number,int len) {
		String STR_FORMAT = "##0.00";
		number=formaterNumber(number,len);
		DecimalFormat df = new DecimalFormat(STR_FORMAT);
		return  df.format(number);
	}

	public static String formaterNumberLongs(Long number,int len) {
		return formaterNumberLongs(number,len,false);
	}
	public static String formaterNumberLongs(Long number,int len,boolean isNull) {
		if(isNull){
			if(ObjectUtils.isEmpty(number)){
				return null;
			}
		}
		String STR_FORMAT = "##0.00";
		double d=formaterNumber(priceChangeYuan(number),len);
		DecimalFormat df = new DecimalFormat(STR_FORMAT);
		return  df.format(d);
	}

	/**
	 * 格式化数字 （2位小数，传入数据除以100）
	 * @param number
	 * @return
	 */
	public static String formaterNumberPower(Long number){
		return formaterNumberPower(number,2,2);
	}
	/**
	 * 格式化数字 （2位小数，传入数据除以100）
	 * @param number
	 * @return
	 */
	public static String formaterNumberPower(Long number,int power){
		return formaterNumberPower(number,2,power);
	}
	/**
	 * 格式化数字
	 * @param number 传入数据
	 * @param decimalLen 小数位数
	 * @param power 10的n次方（如果为传入为正数 剩，负数 除）
	 * @return
	 */
	public static String formaterNumberPower(Long number,int decimalLen,int power) {
		if(ObjectUtils.isEmpty(number)){
			number=0l;
		}
		String STR_FORMAT = "##0.00";
		double d=formaterNumber(number/Math.pow(10,power),decimalLen);
		DecimalFormat df = new DecimalFormat(STR_FORMAT);
		String rtn=df.format(d);
		String[] num=rtn.split("\\.");
		String xs=num[1];
		while (xs.length()>0){
			if(xs.endsWith("0")){
				xs=xs.substring(0,xs.length()-1);
			}else{
				break;
			}
		}
		String rtnNum=num[0];
		if(xs.length()>0){
			rtnNum+="."+xs;
		}
		return  rtnNum;
	}


	public static String formaterRandNumberr(int randNum) {
		String STR_FORMAT = "000";
		DecimalFormat df = new DecimalFormat(STR_FORMAT);
		return  df.format(randNum);
	}
	public static String formaterNumberr(int number,int len) {
		String STR_FORMAT ="";
		for(int i=0;i<len;i++){
			STR_FORMAT+="0";
		}
		DecimalFormat df = new DecimalFormat(STR_FORMAT);
		return  df.format(number);
	}

	/**
	 * 比较传入参数的大小值
	 * @param prices
	 * @return
	 */
	public static Double compareNumber(Double...prices){
		double len=0.0;
		if(ObjectUtils.isNotEmpty(prices)){
			for(Double d:prices){
				if(ObjectUtils.isNotEmpty(d)){
					if(len==0){
						len = d;
					}else if(d<len){
						len =d;
					}
				}
			}
		}
		return len;
	}

	/**
	 * double相乘
	 * @param price 金额
	 * @param odds 赔率
	 * @return
	 */
	public static double multiply(double price, double odds) {
		double res_price = price * odds;
		res_price = NumberUtils.formaterNumbers(res_price);
		return res_price;
	}

	/**
	 * 取得随机值
	 * @param maxNum 随机值最大值
	 * @param min 随机值最小值
	 * @return
	 */
	public static int randomNumber(int min,int maxNum){
		if(maxNum==min||maxNum-min==1){
			return maxNum;
		}
		int randNum= random.nextInt(maxNum);
		while (randNum<min){
			randNum= random.nextInt(maxNum);
		}
		return randNum;
	}

	/**
	 * 取得几个数的最大值
	 * @param num
	 * @return
	 */
	public static long getMaxNum(Long ... num){
		long maxNum=0;
		if(ObjectUtils.isNotEmpty(num)){
			Long[] numbers=num;
			for(Long n:numbers){
				if(ObjectUtils.isNotEmpty(n)){
					if(n.intValue()>maxNum){
						maxNum=n;
					}
				}
			}
		}
		return maxNum;
	}

	/**
	 * 求合
	 * @param num
	 * @return
	 */
	public static long sumNumber(Long ... num){
		long sumNum=0;
		if(ObjectUtils.isNotEmpty(num)){
			Long[] numbers=num;
			for(Long n:numbers){
				if(ObjectUtils.isNotEmpty(n)){
					sumNum+=n.intValue();
				}
			}
		}
		return sumNum;
	}
	/**
	 * 取得几个数的最小值
	 * @param num
	 * @return
	 */
	public static long getMinNum(Long ... num){
		Long minNum=null;
		if(ObjectUtils.isNotEmpty(num)){
			Long[] numbers=num;
			for(Long n:numbers){
				if(ObjectUtils.isNotEmpty(n)){
					if(ObjectUtils.isEmpty(minNum)||minNum.intValue()>n.intValue()){
						minNum=n;
					}
				}
			}
		}
		return minNum;
	}
	public static void main(String[] args){
//		double q =formaterNumbers(1.30)*formaterNumbers(1.30)*formaterNumbers(1.01);
//		System.out.println(q);
//		System.out.println(formaterNumbers(q));
//		System.out.println(formaterRandNumberr(1));
//		System.out.println(formaterNumberr(100,4));
//		long price =1263;
//		Long[] rtn = NumberUtils.formaterNumberr(2900l,0.2,price);
//		if(rtn[1].longValue()>=price){
//			rtn[1]=price;
//		}
//		System.out.println(JSON.toJSON(rtn));
//		System.out.println(formaterNumberDown(6526.1));
//		System.out.println(isLong("12.01"));
//		System.out.println(formaterNumber(2.69));
//		for(int i=0;i<10;i++){
//			System.out.println(randomNumber(10,2));
//		}
//		System.out.println(getMaxNumber(null,5));
//		System.out.println(getMaxNum(10l,2l,5l,1l,null));
//		System.out.println(getMinNum(10l,2l,5l,-1l));
		Long num=null;
//		System.out.println(formaterNumberLong(num,2));

		System.out.println(formaterNumberPower(10001l,1));
	}
}
