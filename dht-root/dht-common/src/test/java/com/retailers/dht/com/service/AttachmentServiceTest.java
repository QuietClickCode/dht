package com.retailers.dht.com.service;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.entity.Attachment;
import com.retailers.dht.common.service.AttachmentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by admin on 2017/9/27.
 */
public class AttachmentServiceTest extends TestBaseJunit{
    @Autowired
    private AttachmentService attachmentService;


    @Test
    public void queryAttachmentById(){
        long id=1;
        Attachment attachment =attachmentService.queryAttachmentById(id);
        System.out.println(JSON.toJSON(attachment));
    }
}
