package com.retailers.dht.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retailers.dht.common.constant.SysParameterConfigConstant;
import com.retailers.dht.common.constant.SystemConstant;
import com.retailers.dht.common.entity.User;
import com.retailers.dht.common.entity.WxAuthUser;
import com.retailers.dht.common.service.WxAuthUserService;
import com.retailers.dht.common.view.UserInfoVIew;
import com.retailers.tools.utils.HttpClientUtil;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import com.retailers.wx.common.config.WxConfig;
import com.retailers.wx.common.enm.ScopeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信授权接口
 * @author zhongp
 * @version 1.0.1
 */
@RestController
@RequestMapping("wx")
public class WxAuthController {
    Logger logger = LoggerFactory.getLogger(WxAuthController.class);
    @Autowired
    private WxAuthUserService wxAuthUserService;

    /**
     * 获得用户授权确认
     */
    @RequestMapping("toAuth")
    public void queryUserAuth(HttpServletResponse response,HttpServletRequest request) throws IOException {
        //取得微信原访问地址（授权成功后直接跳转至该页面)
        String redirectUrl=request.getParameter(SystemConstant.WX_ACCESS_ADDRESS_AUTH_URL)+"";
        //页面取得权限地址
        String redUrl= StringUtils.concat(SysParameterConfigConstant.getValue(SysParameterConfigConstant.MASTER_SERVER_MOBILE_URL),
                                            "wx/userLogin?",
                                            SystemConstant.WX_ACCESS_ADDRESS_AUTH_URL,
                                            "=", URLEncoder.encode(redirectUrl,SystemConstant.DEFAUT_CHARSET));
       logger.info("取得微信用户，重定向地址:[{}]",redUrl);
//        String url=buildAuthUrl(redUrl,ScopeEnum.snsapi_userinfo,"pc");
        String url=buildAuthUrl(redUrl,ScopeEnum.snsapi_userinfo,"pc");
        logger.info("微信用户请求地址:{}",url);
        response.sendRedirect(url);
    }

    /**
     * 拼装获取code的url
     * @param redirectUri 授权后登录地址
     * @param scope 授权类型
     * @param state 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @return
     */
    private String buildAuthUrl(String redirectUri,ScopeEnum scope,String state){
        StringBuilder urlBuilder = new StringBuilder();
        if(state==null){
            state="";
        }
        try {
            urlBuilder.append(WxConfig.OAUTH2_AUTHORIZE_URL)
                    .append("?")
                    .append("appid=").append(WxConfig.APP_ID)   //公众号的唯一标识
                    .append("&redirect_uri=").append(URLEncoder.encode(redirectUri,SystemConstant.DEFAUT_CHARSET)) //授权后重定向的回调链接地址
                    .append("&response_type=").append("code") //返回类型，请填写code
                    .append("&scope=").append(scope.name()) //应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo
                                                            // （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
                    .append("&state=").append(state)      //重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
                    .append("#wechat_redirect");       //无论直接打开还是做页面302重定向时候，必须带此参数
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return urlBuilder.toString();
    }


    /**
     * 取得微信用户 回调地址
     * @param request
     * @param code 微信code 用于取得微信用户数据
     */
    @RequestMapping("userLogin")
    public ModelAndView auth(HttpServletRequest request, String code) {
        String redUrl="/";
        logger.info("取得微信返回code:{}",code);
        String red=request.getParameter(SystemConstant.WX_ACCESS_ADDRESS_AUTH_URL);
        if(ObjectUtils.isNotEmpty(red)){
            redUrl=red;
        }
        logger.info("取得原始访问地址:{}",red);
        Map<String,Object> parms = WebUtils.getParametersStartingWith(request,"");
        logger.info("取得访问参数：{}", JSON.toJSON(parms));
        WxAuthUser wxAuthUser=wxAuthUserService.queryWxAuthUser(code);
        ModelAndView modelAndView=new ModelAndView();
        if(ObjectUtils.isNotEmpty(wxAuthUser)){
            //判断该微信用户是否绑定用户
            if(ObjectUtils.isEmpty(wxAuthUser.getWauUid())){
                request.getSession().setAttribute(com.retailers.auth.constant.SystemConstant.CUR_LOGIN_WXUSER_INFO,wxAuthUser);
                modelAndView.addObject("wx","isBindWx");
                modelAndView.addObject("redirectUrl",redUrl);
                redUrl="/loginPage";
            }else{
                UserInfoVIew userInfoVIew=new UserInfoVIew();
                userInfoVIew.setWauOpenid(wxAuthUser.getWauOpenid());
                userInfoVIew.setUid(wxAuthUser.getWauUid());
                request.getSession().setAttribute(com.retailers.auth.constant.SystemConstant.CUR_LOGIN_WXUSER_INFO,userInfoVIew);
                request.getSession().setAttribute(com.retailers.auth.constant.SystemConstant.IS_PULL_WX_USER_INFO,"yes");
            }
        }
        modelAndView.setViewName("redirect:"+redUrl);
        return modelAndView;
    }
}
