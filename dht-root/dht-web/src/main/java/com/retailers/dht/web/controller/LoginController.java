package com.retailers.dht.web.controller;

import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户登陆
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/10
 */
@Controller
public class LoginController extends BaseController{

    /**
     * 打开登陆页面
     * @param request
     * @param wx
     * @return
     */
    @RequestMapping("loginPage")
    public ModelAndView openLogin(HttpServletRequest request, String wx,String redirectUrl){
        String url=redirectUrl(request,"login/login");
        ModelAndView model =new ModelAndView(url);
        boolean isBindWx=false;
        if(ObjectUtils.isNotEmpty(wx)){
            isBindWx=true;
        }
        model.addObject("isBindWx",isBindWx);
        model.addObject("redirectUrl",redirectUrl);
        return model;
    }

}
