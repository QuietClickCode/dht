package com.retailers.hnc.com.bean;

/**
 * Created by admin on 2017/11/20.
 */
public class City implements Comparable<City>{
    /**
     * 城市代码
     */
    private String code;
    /**
     * 城市名称
     */
    private String name;
    /**
     * 父级城市代码
     */
    private String parseCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParseCode() {
        return parseCode;
    }

    public void setParseCode(String parseCode) {
        this.parseCode = parseCode;
    }

    public int compareTo(City o) {
        return Integer.parseInt(code)-Integer.parseInt(o.code);
    }
}
