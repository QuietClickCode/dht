package com.retailers.dht.common.enm;

/**
 * 短信类型枚举
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/23
 */
public enum SmsSendRecordEnum {
    BIND_PHONE_CODE(0, "你正在大汇堂平台绑定手机号码，此次验证码是：{}");
    String context;
    long type;

    public String getContext() {
        return context;
    }
    public void setContext(String context) {
        this.context = context;
    }
    public long getType() {
        return type;
    }
    public void setType(long type) {
        this.type = type;
    }
    SmsSendRecordEnum(long type, String context){
        this.type=type;
        this.context=context;
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
