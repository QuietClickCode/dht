package com.retailers.hnc.com.service;

import com.alibaba.fastjson.JSON;
import com.retailers.hnc.com.base.TestBaseJunit;
import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.mybatis.common.entity.Attachment;
import com.retailers.mybatis.common.service.AttachmentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/9/27.
 */
public class AttachmentServiceTest extends TestBaseJunit {
    @Autowired
    private AttachmentService attachmentService;


    @Test
    public void queryAttachmentById(){
        long id=1;
        Attachment attachment =attachmentService.queryAttachmentById(id);
        System.out.println(JSON.toJSON(attachment));
    }

    @Test
    public void editorAttachment(){
        long attachment=70;
        List<Long> list = new ArrayList<Long>();
        list.add(16l);
        list.add(17l);
        boolean flag =attachmentService.editorAttachment(attachment, AttachmentConstant.ATTACHMENT_STATUS_NO);
        System.out.println(flag);
    }

    @Test
    public void clearUnUsedAttachemnt(){
        attachmentService.clearUnUsedAttachemnt();
    }
}
