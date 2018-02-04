package com.retailers.tools.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
 */
public class DateUtil {
	/** 简单年日期格式 */
	public static final String DATE_YEAR = "yyyy";
	/** 简单月日期格式 */
	public static final String DATE_MONTH = "MM";
	/** 简单日日期格式 */
	public static final String DATE_DAY = "dd";
	/** 简单周日期格式 */
	public static final String DATE_WEEK = "EE";

	/** 简单年月日期格式 */
	public static final String DATE_SHORT_SIMPLE_MONTH_FORMAT = "yyyyMM";
	/** 简单年月日期格式 */
	public static final String DATE_SHORT_SIMPLE_MONTH_FORMAT_NEW = "yyMM";
	/** 简单年月日日期格式 */
	public static final String DATE_SHORT_SIMPLE_FORMAT = "yyyyMMdd";
	/** 简单年月日日期格式 */
	public static final String DATE_SHORT_YEAR_SIMPLE_FORMAT = "yyMMdd";
	/** 简单年月日 时格式 */
	public static final String DATE_SHORT_SIMPLE_FORMAT_WITHHOUR = "yyyyMMddHH";
	/** 简单年月日 时 分格式 */
	public static final String DATE_SHORT_SIMPLE_FORMAT_WITHMINUTE = "yyyyMMddHHmm";
	/** 年月日时分秒格式 */
	public static final String DATE_LONG_SMAIL_FORMAT = "yyMMddHHmmss";
	/** 年月日时分秒格式 */
	public static final String DATE_LONG_SIMPLE_FORMAT = "yyyyMMddHHmmss";
	/** 简单时分秒毫秒 */
	public static final String DATE_SHORT_TIME_FORMAT = "HHmmss.S";
	/** 简单时分秒毫秒 */
	public static final String DATE_SHORT_TIME_FORMATS = "HHmmssS";
	/** 简单时分钞 **/
	public static final String DATE_TIME_FORMAT = "HHmmss";
	/** 年月日日期格式 */
	public static final String DATE_SHORT_FORMAT = "yyyy-MM-dd";
	/** 年月日期格式 */
	public static final String DATE_SHORT_YEAR_MONTH = "yyyy-MM";
	/** 中文年月日日期格式 */
	public static final String DATE_SHORT_CHN_FORMAT = "yyyy年MM月dd日";
	/** 年月日时日期格式 */
	public static final String DATE_WITHHOUR_FORMAT = "yyyy-MM-dd HH";
	/** 中文年月日时日期格式 */
	public static final String DATE_WITHHOUR_CHN_FORMAT = "yyyy年MM月dd日 HH";
	/** 年月日时分日期格式 */
	public static final String DATE_WITHMINUTE_FORMAT = "yyyy-MM-dd HH:mm";
	/** 中文年月日时分日期格式 */
	public static final String DATE_WITHMINUTE_CHN_FORMAT = "yyyy年MM月dd日 HH:mm";
	/** 年月日时分秒日期格式 */
	public static final String DATE_WITHSECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/** 中文年月日时分秒日期格式 */
	public static final String DATE_WITHSECOND_CHN_FORMAT = "yyyy年MM月dd日 HH:mm:ss";
	/** 年月日时分秒毫秒日期格式 */
	public static final String DATE_WITHMILLISECOND_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
	/** 中文年月日时分秒毫秒日期格式 */
	public static final String DATE_WITHMILLISECOND_CHN_FORMAT = "yyyy年MM月dd日 HH:mm:ss.S";
	/**
	 * 格林时间格式
	 */
	public static final String DATE_UTC_FORMAT="yyyy-MM-dd'T'HH:mm:ss'Z'";

	/**
	 * 将给定应用服务器日期按照给定格式化类型转换成字符串
	 *
	 * @param date
	 *            -java日期对象
	 * @param format
	 *            -日期格式化类型
	 * @return String -返回转换后的字符串
	 */
	public static String dateToString(Date date, String format) {
		if (ObjectUtils.isEmpty(date))
			return null;

		if (ObjectUtils.isEmpty(format))
			format = DATE_WITHSECOND_FORMAT;

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 将给定应用服务器日期按照默认格式化(yyyy-MM-dd HH:mm:ss)类型转换成字符串
	 *
	 * @param date
	 *            -java日期对象
	 * @return String -返回转换后的字符串
	 */
	public static String dateToString(Date date) {
		return dateToString(date, DATE_WITHSECOND_FORMAT);
	}

	/**
	 * 将给定应用服务器日期按照默认格式化(HHmmss.S)类型转换成字符串
	 *
	 * @param date
	 *            -java日期对象
	 * @return String -返回转换后的字符串
	 */
	public static String shortTimeToString(Date date) {
		return dateToString(date, DATE_SHORT_TIME_FORMAT);
	}

	/**
	 * 将给定应用服务器日期按照默认格式化(yyyyMMdd)类型转换成字符串
	 *
	 * @param date
	 *            -java日期对象
	 * @return String -返回转换后的字符串
	 */
	public static String shortDateToString(Date date) {
		return dateToString(date, DATE_SHORT_SIMPLE_FORMAT);
	}

	/**
	 * 将应用服务器当前日期按照给定格式化类型转换成字符串
	 *
	 * @param format
	 *            -日期格式化类型
	 * @return String -返回转换后的字符串
	 */
	public static String currentTimeToString(String format) {
		return dateToString(getCurrentDateTime(), format);
	}

	/**
	 * 将字符串转换成日期 注意：一定要选用匹配的格式，否则不能解析，将返回null
	 *
	 * @param strDate
	 *            - 日期
	 * @param format
	 *            - 格式
	 * @return Date -转换后的日期
	 */
	public static Date stringToDate(String strDate, String format) {
		if (ObjectUtils.isEmpty(strDate))
			return null;
		if (ObjectUtils.isEmpty(format))
			format = DATE_SHORT_FORMAT;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 将字符串转换成日期，默认格式：yyyy-MM-dd
	 *
	 * @param strDate
	 *            - 日期
	 * @return Date -转换后的日期
	 */
	public static Date stringToDate(String strDate) {
		if (ObjectUtils.isEmpty(strDate))
			return null;
		return stringToDate(strDate, DATE_SHORT_FORMAT);
	}

	/**
	 * 获取当前日期
	 *
	 * @return Date -转换后的日期
	 */
	public static Date getCurrentDateTime() {
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

	/**
	 * 获取当前时间，精确到秒（没转化格式）
	 *
	 * @return Date -转换后的日期
	 */
	public static Date getCurrentTime() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 获取当前年份-长
	 *
	 * @return String -当前年份
	 */
	public static String getCurrentLongYear() {
		Calendar c = Calendar.getInstance();
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year;
	}

	/**
	 * 获取指定时间年份
	 *
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		if (!ObjectUtils.isEmpty(date)) {
			c.clear();
			c.setTime(date);
		}
		return c.get(Calendar.YEAR);
	}

	/**
	 * 获取指定时间月份
	 *
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {

		Calendar c = Calendar.getInstance();
		if (!ObjectUtils.isEmpty(date)) {
			c.clear();
			c.setTime(date);
		}
		return c.get(Calendar.MONTH) + 1;

	}

	/**
	 * 传入日期加上年
	 *
	 * @param date
	 * @param year
	 * @return
	 */
	public static String addYear(Date date, int year) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, year);
		return dateToString(c.getTime(), DATE_SHORT_YEAR_MONTH);
	}

	/**
	 * 传入日期加上年
	 *
	 * @param date
	 * @param year
	 * @return
	 */
	public static String addYears(Date date, int year) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, year);
		return dateToString(c.getTime(), DATE_WITHSECOND_FORMAT);
	}


	/**
	 * 传入日期加上年
	 *
	 * @param date
	 * @param year
	 * @return
	 */
	public static String addYear(Date date, int year,String formater) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, year);
		return dateToString(c.getTime(), formater);
	}


	/**
	 * 传入日期加上月份
	 *
	 * @param date
	 * @param month
	 * @return
	 */
	public static String addMonth(Date date, int month,String formater) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, month);
		return dateToString(c.getTime(), formater);
	}
	/**
	 * 传入日期加上月份
	 *
	 * @param date
	 * @param month
	 * @return
	 */
	public static String addMonth(Date date, int month) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, month);
		return dateToString(c.getTime(), DATE_SHORT_FORMAT);
	}
	/**
	 * 取得传入时间的月份第一天
	 * @return
	 */
	public static String monthFirst(Date date,String formater){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return dateToString(calendar.getTime(), DATE_SHORT_FORMAT);
	}
	/**
	 * 取得传入时间的月份第一天
	 * @return
	 */
	public static String monthFirst(){
		return monthFirst(new Date(),DATE_SHORT_FORMAT);
	}

	/**
	 * 取得传入时间的月份第一天
	 * @return
	 */
	public static String monthFirst(String formater){
		return monthFirst(new Date(),formater);
	}


	/**
	 * 传入日期加上天
	 *
	 * @param date
	 * @param day
	 * @return
	 */
	public static String addDay(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, day);
		return dateToString(c.getTime(), DATE_SHORT_FORMAT);
	}
	/**
	 * 传入日期加上天
	 *
	 * @param date
	 * @param day
	 * @return
	 */
	public static String addDay(String date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(DateUtil.stringToDate(date,DateUtil.DATE_SHORT_FORMAT));
		c.add(Calendar.DAY_OF_YEAR, day);
		return dateToString(c.getTime(), DATE_SHORT_FORMAT);
	}
	/**
	 * 传入日期加上天
	 * @param date 传入时间
	 * @param day 天数
	 * @param formate 日期格式
	 * @return
	 */
	public static String addDay(Date date, int day,String formate) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, day);
		return dateToString(c.getTime(), formate);
	}
	/**
	 * 传入日期加上天
	 *
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDays(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, day);
		return c.getTime();
	}

	/**
	 * 传入日期加上天
	 *
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDays(Date date, int day,String formate) {
		String date_=addDay(date,day,formate);
		return stringToDate(date_);
	}
	public static  Date addSecond(Date date,int second){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, second);
		return c.getTime();
	}

	/**
	 * 根据指定时间获取所在天数
	 *
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		if (!ObjectUtils.isEmpty(date)) {
			c.clear();
			c.setTime(date);
		}
		return c.get(Calendar.DATE);
	}

	/**
	 * 传入的时间加上分钟数
	 *
	 * @param strDate
	 * @param format
	 * @param mu
	 * @return
	 */
	public static Date dateAdd(String strDate, String format, Integer mu) {
		Date par = stringToDate(strDate, format);
		Long time = (long) 1000 * 60 * mu;
		return new Date(par.getTime() + time);
	}
    /**
     * 添加分钟数
     * @param date 时间
     * @param minue 添加分钟数
     * @return
     */
    public static Date addMinute(Date date,int minue){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE,minue);
        return c.getTime();
    }
    /**
     * 添加小时
     * @param date 时间
     * @param format 转换后的格式
     * @param minue 添加分钟数
     * @return
     */
    public static String addMinute(Date date,int minue,String format){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, minue);
        if(ObjectUtils.isEmpty(format)){
            format =DATE_WITHSECOND_FORMAT;
        }
        return dateToString(c.getTime(),format);
    }
	/**
	 * 添加小时
	 * @param date 时间
	 * @param format 转换后的格式
	 * @param hour 添加小时数
	 * @return
	 */
    public static String addHour(Date date,long hour,String format){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, (int)hour);
        if(ObjectUtils.isEmpty(format)){
            format =DATE_WITHSECOND_FORMAT;
        }
        return dateToString(c.getTime(),format);
    }

	/**
	 *传入时间添加小时
	 * @param date
	 * @param hour
	 * @return
	 */
    public static Date addHour(Date date,long hour){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, (int)hour);
        return c.getTime();
    }
	/**
	 *传入时间添加小时
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date addHour(Date date,Double hour){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int min=0;
		if(ObjectUtils.isNotEmpty(hour)){
			min=(int)(hour*60);
		}
//		c.add(Calendar.HOUR, (int)hour);
		c.add(Calendar.MINUTE,min);
		return c.getTime();
	}
	/**
	 * 传入的时间加上分钟数
	 *
	 * @param strDate
	 * @param mu
	 * @param mu
	 * @return
	 */
	public static Date dateAdd(Date strDate, Integer mu) {
		Long time = (long) 1000 * 60 * mu;
		return new Date(strDate.getTime() + time);
	}

	/**
	 * 传入时间减去分钟数
	 *
	 * @param strDate
	 * @param format
	 * @param mu
	 * @return
	 */
	public static Date dateReduce(String strDate, String format, Integer mu) {
		Date par = stringToDate(strDate, format);
		Long time = (long) 1000 * 60 * mu;
		return new Date(par.getTime() - time);

	}

	/**
	 * 传入时间减去分钟数
	 *
	 * @param strDate
	 * @param mu
	 * @return
	 */
	public static Date dateReduce(Date strDate, Integer mu) {
		Long time = (long) 1000 * 60 * mu;
		return new Date(strDate.getTime() - time);

	}
	/**
	 * 传入时间减去分钟数
	 *
	 * @param strDate
	 * @param mu
	 * @return
	 */
	public static Date dateAdd(Date strDate, double mu) {
		Long time = (long)(1000 * 60 * mu);
		return new Date(strDate.getTime() + time);

	}
	/**
	 * 获取指定时间的后一天的指定类型日期
	 *
	 * @param date
	 * @param month
	 * @return String
	 */
	public static Date getAfterMonth(Date date, int month) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTime(date);
		c.add(Calendar.MONTH, month);
		return c.getTime();
	}

	/**
	 * 获取指定时间的后一天的指定类型日期
	 * @param date
	 * @param day
	 * @return String
	 */
	public static Date getAfterDay(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, day);
		return c.getTime();
	}

	/**
	 * 获取指定时间的季度开始时间和结束时间
	 *
	 * @param date
	 * @return
	 */
	public static String[] getQuarterly(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 取得年份
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		// 取得月份
		String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		String[] rtn = new String[3];
		switch (Integer.parseInt(month) / 3) {
		case 0:
			rtn[0] = year + "-01-01";
			rtn[1] = year + "-03-31";
			rtn[2] = "1";
			break;
		case 1:
			rtn[0] = year + "-04-01";
			rtn[1] = year + "-06-31";
			rtn[2] = "2";
			break;
		case 2:
			rtn[0] = year + "-07-01";
			rtn[1] = year + "-09-30";
			rtn[2] = "3";
			break;
		case 3:
			rtn[0] = year + "-10-01";
			rtn[1] = year + "-12-31";
			rtn[2] = "3";
			break;
		default:
			rtn[0] = year + "-01-01";
			rtn[1] = year + "-03-31";
			rtn[2] = "1";
			break;
		}
		return rtn;
	}

	/**
	 * 获取指定时间的季度开始时间和结束时间
	 *
	 * @param date
	 * @return
	 */
	public static String[] getQuarterly(String date) {
		String[] rtn = new String[3];
		String year = date.substring(0, 4);
		String month = "";
		if (date.length() == 8) {
			month = date.substring(4, 6);
		} else {
			month = date.substring(5, 7);
		}
		switch (Integer.parseInt(month) / 3) {
		case 0:
			rtn[0] = year + "-01-01";
			rtn[1] = year + "-03-31";
			rtn[2] = "1";
			break;
		case 1:
			rtn[0] = year + "-04-01";
			rtn[1] = year + "-06-31";
			rtn[2] = "2";
			break;
		case 2:
			rtn[0] = year + "-07-01";
			rtn[1] = year + "-09-30";
			rtn[2] = "3";
			break;
		case 3:
			rtn[0] = year + "-10-01";
			rtn[1] = year + "-12-31";
			rtn[2] = "3";
			break;
		default:
			rtn[0] = year + "-01-01";
			rtn[1] = year + "-03-31";
			rtn[2] = "1";
			break;
		}
		return rtn;
	}

	/**
	 * 取得传入时间的月份的最大天数
	 *
	 * @param date
	 * @return
	 */
	public static int getActualMaximum(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DATE);
	}

	/**
	 * 取得传入时间的月份的最大天数
	 *
	 * @param date
	 * @return
	 */
	public static int getActualMaximum(String date) {
		return getActualMaximum(stringToDate(date));
	}

	/**
	 * 取得传入时间的月份的最大天数
	 *
	 * @param date
	 * @return
	 */
	public static int getActualMaximum(String date, String fomatter) {
		return getActualMaximum(stringToDate(date, fomatter));
	}

	/**
	 * 报表类型取得时间
	 *
	 * @param date
	 *            时间
	 * @param type
	 *            类型
	 * @return
	 */
	public static Date[] reportDate(String date, String type) {
		if (ObjectUtils.isEmpty(date)) {
			date = dateToString(new Date(), DATE_SHORT_FORMAT);
		}

		Date startDate = null;
		Date endDate = null;
		// 判断报表类型
		if ("year".equals(type)) {
			startDate = stringToDate(
					date.substring(0, 4) + "-01-01",
					DATE_SHORT_FORMAT);
			endDate = stringToDate(
					(Integer.parseInt(date.substring(0, 4)) + 1) + "-01-01",
					DATE_SHORT_FORMAT);
		} else if ("month".equals(type)) {
			startDate = stringToDate(date.substring(0, 7) + "-01",
					DATE_SHORT_FORMAT);
			endDate = getAfterMonth(startDate, 1);
		} else if ("day".equals(type)) {
			startDate = stringToDate(date,
					DATE_SHORT_FORMAT);
			endDate = getAfterDay(startDate, 1);
		}

		return new Date[] { startDate, endDate };
	}

	/**
	 * 取得传入时间的相差的月数
	 *
	 * @param startDate
	 *            开始
	 * @param endDate
	 *            结束
	 * @return
	 */
	public static int calculateMonthIn(Date startDate, Date endDate) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(startDate);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(endDate);
		int c = (cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR)) * 12
				+ cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
		return c;
	}

	/**
	 * 取得传入时间的相差天数
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int daysOfTwo(Date startDate, Date endDate) {
		if (null == startDate || null == endDate) {
			return -1;
		}
		long intervalMilli = endDate.getTime() - startDate.getTime();
		return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}


	public static boolean days(Date start,Date endDate,Date conterDate){
		boolean falg = true;
		if (conterDate.getTime()>endDate.getTime()||conterDate.getTime()<start.getTime()) {
			falg = false;
		}
		return falg;
	}

    /**
     * 功能: 返回date1与date2相差的分钟数
     *
     * @param date1
     * @param date2
     * @return int
     */
    public static int MinDiff(Date date1, Date date2) {
        int i = (int) ((date1.getTime() - date2.getTime()) / 1000 / 60);
        return i;
    }

	/**
	 * 获取连个时间相隔秒钟数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long calLastedTime(Date startDate,Date endDate) {
		long time = (endDate.getTime() - startDate.getTime()) / 1000;
		return time;
	}

	/**
	 * 取得日期的周一
	 * @param date
	 * @return
	 */
	public static String getWeekFirstDay(Date date){
		  Calendar calendar = Calendar.getInstance();
	      calendar.setTime(new Date());
	      while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
	            calendar.add(Calendar.DATE, -1);
	      }
	      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	      return dateFormat.format(calendar.getTime());
	}
	public static  List<String> dateList(String startDate,String endDate){
		List<String> rtn = new ArrayList<String>();
		Date startDate_=stringToDate(startDate,DATE_SHORT_FORMAT);
		Date endDate_=stringToDate(endDate,DATE_SHORT_FORMAT);
		while (startDate_.compareTo(endDate_)<0){
			rtn.add(dateToString(startDate_,DATE_SHORT_FORMAT));
			startDate_=addDays(startDate_,1);
		}
		return rtn;
	}

	/**
	 * 取得当前日期所在周
	 * @param date
	 * @return
	 */
	public static int getWeekOfDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK);
		if (w < 0)
			w = 0;
		return w;
	}

	/**
	 * 取得当前秒
	 * @param date
	 * @return
	 */
	public static  int getSecond(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.SECOND);
		return w;
	}

	public static long changeSecond(long second){
		return  second*1000;
	}

	public static void main(String[] args){
//		Date curDate = new Date();
//		String date = DateUtil.addDay(curDate,-1,DATE_WITHSECOND_FORMAT);
//		System.out.println(date);
		//
//		List<String> lists = dateList("2016-12-01","2016-12-23");
//		System.out.println(lists);

//		Date curDate=new Date();
////		Date lastDate = DateUtil.addDays(curDate,1,DateUtil.DATE_SHORT_FORMAT);
////		System.out.println(lastDate);
////		System.out.println(dateToString(lastDate));
////		System.out.println(getWeekOfDate(curDate));
//
//
//
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(curDate);
//		int w = cal.get(Calendar.SECOND);
//		System.out.println(dateToString(curDate,DATE_WITHSECOND_FORMAT));
//		System.out.println(w);
		Date curDate =new Date();
		System.out.println(StringUtils.formate("123456", DateUtil.dateToString(curDate, DateUtil.DATE_LONG_SIMPLE_FORMAT)));

	}
}
