package com.retailers.mybatis.common.vo;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2018/1/20
 */
public class SysParamVo {
    private String key;
    private String value;
    public SysParamVo(){

    }
    public SysParamVo(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
