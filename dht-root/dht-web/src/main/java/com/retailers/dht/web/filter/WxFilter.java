package com.retailers.dht.web.filter;


import com.retailers.auth.constant.SystemConstant;
import com.retailers.tools.utils.CheckMobile;
import com.retailers.tools.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 微信过滤器(微信端 访问时取得用户openid）
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/25
 */
public class WxFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(WxFilter.class);
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * 过滤器
     * @param servletRequest
     * @param servletResponse
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        logger.info("进入ManagerFilter 的doFilter 方法");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //取得访问端口类型
        String userAgent= request.getHeader( "USER-AGENT" );
        if(ObjectUtils.isNotEmpty(userAgent)){
            userAgent = userAgent.toLowerCase();
        }else{
            userAgent="";
        }

        boolean isFromMobile= CheckMobile.check(userAgent);
        //判断是否为移动端访问 移动端访问
        if(isFromMobile){
            //判断是否是微信
            if(userAgent.indexOf("micromessenger")>0){
                String uri = request.getRequestURI();
                //判断是否己授权登录
                Object obj =  request.getSession().getAttribute(SystemConstant.IS_PULL_WX_USER_INFO);
                //未登录 页面重定向 获取用户openid
                if(ObjectUtils.isEmpty(obj)&&!uri.endsWith("wx/userLogin")){
                    //跳转至公众号授权注册页面
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/wx/toAuth?"+
                            com.retailers.dht.common.constant.SystemConstant.WX_ACCESS_ADDRESS_AUTH_URL+"="+
                            URLEncoder.encode(urlParams(request), com.retailers.dht.common.constant.SystemConstant.DEFAUT_CHARSET));
                    dispatcher.forward(request, response);
                    return;
                }
            }
        }
        chain.doFilter(servletRequest, servletResponse);
        return;
    }
    public void destroy() {

    }

    /**
     * 取得访问的url参数并组成字符
     * @param request
     * @return
     */
    private String urlParams(HttpServletRequest request){
        Map<String,Object> params=WebUtils.getParametersStartingWith(request,"");
        String uri = request.getRequestURI();
        StringBuffer sb = new StringBuffer(uri);
        if(ObjectUtils.isNotEmpty(params)){
            int i=0;
            for(String key : params.keySet()) {
                i++;
                if(i==1){
                    sb.append("?" + key + "=" + params.get(key));
                }else{
                    sb.append("&" + key + "=" + params.get(key));
                }
            }
        }
        return sb.toString();
    }
}
