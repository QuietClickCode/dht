package com.retailers.dht.manage.filter;

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
        //根目录，资源文件直接跳出
        String path =  request.getContextPath();
        //判断访问的url是否需要权限
        if(CheckUserPermissionUtils.checkUrl(uri)){
            //判断用户是否有权限限访问该请求路径
            SysUser info = (SysUser) request.getSession().getAttribute(SystemConstant.LOG_USER_SESSION_KEY);
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
        chain.doFilter(servletRequest, servletResponse);
        return;
    }
    public void destroy() {

    }
}
