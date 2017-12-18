package com.retailers.dht.manage.filter;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.auth.entity.SysUser;
import com.retailers.auth.utils.CheckUserPermissionUtils;
import com.retailers.tools.base.WriteData;
import com.retailers.tools.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台管理过滤器
 * @author zhongp
 * @version  1.0.1
 * @data 2017-09-22
 */
public class ManagerFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(ManagerFilter.class);
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
        String uri = request.getRequestURI();
        String header = request.getHeader("X-Requested-With");
        System.out.println("header------------------------->>:"+header);
        if(uri.indexOf(".")<0){
            String path = request.getServletPath();
            // 如果用户未登录，通过在IE地址栏走login.jsp或者register.jsp的页面可以直接访问资源，否则就进行拦截
            if (uri.equalsIgnoreCase("/login")||uri.equalsIgnoreCase("/wechat/sendMsg")||uri.equals("/sysUser/querySyUserByAccount")) {
                chain.doFilter(request, response);
                return;
            } else {
                // 如果用户已经登录，则用户可以在同一个IE浏览器通过url来访问资源，否则直接进入登录页面
                if(!ObjectUtils.isEmpty(request.getSession().getAttribute(SystemConstant.LOG_USER_SESSION_KEY))){
                    chain.doFilter(request, response);
                }else{
                    String context="";
                    //判断是否是ajax 请求
                    if(ObjectUtils.isNotEmpty(header)&&header.equals("XMLHttpRequest")){
                        Map<String,Object> obj = new HashMap<String,Object>();
                        obj.put("status",WriteData.SC_UNAUTHORIZED);
                        obj.put("msg","未登陆或过期，请重新登陆");
                        context= JSON.toJSONString(obj);
                    }else{
                        context = "<script language='javascript'>window.top.location.href='"
                                + request.getContextPath()
                                + "login'</script>";

                    }
                    Writer writer = response.getWriter();
                    writer.write(context);
                    writer.flush();
                    writer.close();
                    return;
                }
            }

            SysUser info = (SysUser) request.getSession().getAttribute(SystemConstant.LOG_USER_SESSION_KEY);
            //判断访问的url是否需要权限
            if(CheckUserPermissionUtils.checkUrl(uri)){
                if(ObjectUtils.isNotEmpty(info)){
                    //取得当前登陆用户
                    boolean isflag = CheckUserPermissionUtils.checkPermission(info.getUid(),uri);
                    if(isflag){
                        chain.doFilter(servletRequest, servletResponse);
                        return;
                    }else{
                        try{
                            WriteData.authError("此次操作未授权!",response);
                            return;
                        }catch(Exception e){
                        }
                    }
                }
//            WriteDataUtils.paramError("未登录请重新登录",response);
                chain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        chain.doFilter(request, response);
        return;
    }
    public void destroy() {

    }
}
