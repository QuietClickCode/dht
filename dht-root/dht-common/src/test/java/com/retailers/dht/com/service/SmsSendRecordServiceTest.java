package com.retailers.dht.com.service;

import com.retailers.aliyun.sms.constant.SmsSendRecordConstant;
import com.retailers.aliyun.sms.service.SmsSendRecordService;
import com.retailers.dht.com.base.TestBaseJunit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/23
 */
public class SmsSendRecordServiceTest extends TestBaseJunit {
    @Autowired
    SmsSendRecordService recordService;

    @Test
    public void sendSmsCode()throws Exception{
        try{
            recordService.sendSmsCode(-1l,"18623325918", SmsSendRecordConstant.SMS_SEND_TYPE_BIND_PHONE);
        }catch(Exception e){
            e.printStackTrace();
        }
        Thread.sleep(1000);
    }

}
