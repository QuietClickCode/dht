package com.retailers.tools.utils;

/**
 * Created by admin on 2015/10/30.
 */
public class RegexConstant {
    /**
     * 手机号正则表达式
     */
    public static final String PHONE = "^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$";
    //只能包含数字、字母、汉字
    public static final String REGEX_NLC = "^[a-zA-Z0-9\\u4e00-\\u9fa5]+$";
    public static final String REGEX_CN = "^[\\u4e00-\\u9fa5]+$";
    //经纬度格式：ddd.dddddd格式
    public static final String REGEX_DEGREE = "^(0|1)?[0-9]{2}\\.\\d{6}$";
    //數字、字母、下劃線的正則表達式
    public static final String DIGITAL_LETTER_UNDERLINE  = "^\\w+$";

    public static void main(String[] args) {
        System.out.println("111sdfkdfkd_".matches(DIGITAL_LETTER_UNDERLINE));
        System.out.println("ddd111sdfkdfkd_".matches(DIGITAL_LETTER_UNDERLINE));
        System.out.println("_111sdfkdfkd_".matches(DIGITAL_LETTER_UNDERLINE));
        System.out.println("_sdfkdfkd111_".matches(DIGITAL_LETTER_UNDERLINE));
    }
}
