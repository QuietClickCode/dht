package com.retailers.aliyun.sms.enm;

/**
 * 短信类型枚举
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/23
 */
public enum SmsSendRecordEnum {
    BIND_PHONE_CODE(0, "binding_phone");
    String funCode;
    long type;

    public String getFunCode() {
        return funCode;
    }

    public void setFunCode(String funCode) {
        this.funCode = funCode;
    }

    public long getType() {
        return type;
    }
    public void setType(long type) {
        this.type = type;
    }
    SmsSendRecordEnum(long type, String funCode){
        this.type=type;
        this.funCode=funCode;
    }

    public static SmsSendRecordEnum getSmsSend(long type) {
        SmsSendRecordEnum[] typeEnumArray = SmsSendRecordEnum.values();
        for (SmsSendRecordEnum typeEnum : typeEnumArray) {
            if (type==typeEnum.getType()) {
                return typeEnum;
            }
        }
        return null;
    }
}
