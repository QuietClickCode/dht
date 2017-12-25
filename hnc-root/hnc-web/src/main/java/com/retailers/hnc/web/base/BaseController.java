package com.retailers.hnc.web.base;


import com.retailers.hnc.web.constant.WebSystemConstant;
import com.retailers.tools.base.BaseWrite;

import javax.servlet.http.HttpServletRequest;

/**
 * 公用controller
 */
public class BaseController extends BaseWrite {

    /**
     * 设置页面跳转路径
     * @param request
     * @param url 原本指向路径
     * @return
     */
    protected  String redirectUrl(HttpServletRequest request,String url){
        String type=request.getAttribute(WebSystemConstant.WEB_REQ_TYPE).toString();
        if(url.indexOf("/")!=0){
            url="/"+url;
        }
        return type+url;
    }
}
