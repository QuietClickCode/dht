package com.retailers.wx.manage.controller;

import com.retailers.auth.annotation.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 微信素材管理
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/2
 */
@Controller
@RequestMapping("wxAttachment")
public class WxAttachmentController {

    /**
     * 打开微信素材管理页面
     * @return
     */
    @Menu(resourse = "wxAttachment.openWxAttachmentPage",parentRes = "system.manager.wx",label = "素材管理",description = "素材管理",sort = 5)
    @RequestMapping("openWxAttachmentPage")
    public String openWxAttachmentPage(){
        return "wx/wxAttachment";
    }

}
