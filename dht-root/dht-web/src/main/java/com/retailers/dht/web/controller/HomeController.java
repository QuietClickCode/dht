package com.retailers.dht.web.controller;

import com.retailers.dht.common.view.UserInfoVIew;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/9
 */
@Controller
public class HomeController extends BaseController {
    /**
     * 主页访问地址
     * @param request
     * @return
     */
    @RequestMapping(value = {"","/","/index"})
    public String home(HttpServletRequest request){
        /*UserInfoVIew userInfo=new UserInfoVIew();
        userInfo.setUid(11l);
        setCurLoginUser(request,userInfo);*/
        String url = "";
        UserInfoVIew userInfoVIew = getCurLoginUser(request);
        if(ObjectUtils.isNotEmpty(userInfoVIew)){
            Integer userModal = userInfoVIew.getuUseModule();
            if(userModal==0){
                url = "n-index";
            }else{
                url = "index";
            }
        }else{
            url = "index";
        }
        return redirectUrl(request,"index");
    }


    @RequestMapping("/home/{id}.html")
    public String randomHome(HttpServletRequest request, @PathVariable("id")String id){
        return redirectUrl(request,"index");
    }

    @RequestMapping("/home/lostHtml")
    public String lostHtml(HttpServletRequest request){
        return redirectUrl(request,"404");
    }

    @RequestMapping("/ton-index")
    public String tonIndex(HttpServletRequest request){
        return redirectUrl(request,"n-index");
    }

    @RequestMapping("/toIndex")
    public String toIndex(HttpServletRequest request){
        return redirectUrl(request,"index");
    }
}
