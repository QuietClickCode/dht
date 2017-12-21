package com.retailers.wx.manage.controller;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.annotation.Menu;
import com.retailers.auth.annotation.Resourse;
import com.retailers.tools.base.BaseResp;
import com.retailers.wx.common.enm.WXAccountEnum;
import com.retailers.wx.common.entity.WxManager;
import com.retailers.wx.common.service.WxManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/30
 */
@Controller
@RequestMapping("wx")
public class WxController {

    @Autowired
    private WxManagerService wxManagerService;

    @Resourse(resourse = "system.manager.wx",label = "公众号",sort = 4)
    private String wxManager;

    @RequestMapping("openWxPage")
    @Menu(resourse = "wx.openWxPage",label = "公众号绑定",parentRes = "system.manager.wx")
    public ModelAndView openWxPage(HttpServletRequest request){
        String realpath = request.getSession().getServletContext().getRealPath("/");
        System.out.println(realpath);
        ModelAndView mav = new ModelAndView();
        WxManager wxManager= wxManagerService.queryCurUsedWx(WXAccountEnum.WX_GZH);
        mav.setViewName("wx/wxpage"); //返回的文件名
        mav.addObject("curWx",wxManager);
        mav.addObject("domainName","http://www.kuaiyis.com");
        return mav;
    }

    /**
     * 微信公众号设置
     * @param wxManager
     * @return
     */
    public BaseResp editorWxManager(WxManager wxManager){
        return null;
    }




}
