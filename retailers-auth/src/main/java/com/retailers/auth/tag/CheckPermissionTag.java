package com.retailers.auth.tag;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.auth.entity.SysUser;
import com.retailers.auth.utils.CheckUserPermissionUtils;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义权限校验标签
 * @author zhongp
 * @version 1.0.1
 */
public class CheckPermissionTag extends SimpleTagSupport {
    //需要校验的url
    private String url;
    public void setUrl(String url) {
        this.url = url;
    }

    StringWriter sw = new StringWriter();

    public void doTag()throws JspException, IOException
    {
        if(ObjectUtils.isNotEmpty(url)){
            url = StringUtils.replaceFirstChart(url, CheckUserPermissionUtils.URL_START_CHART,CheckUserPermissionUtils.URL_START_REPLACE);
            //取得当前登陆用户
            HttpSession session = ((PageContext)this.getJspContext()).getSession();
            SysUser obj = (SysUser) session.getAttribute(SystemConstant.LOG_USER_SESSION_KEY);
            if(ObjectUtils.isNotEmpty(obj)){
                if(obj.getUaccount().equals("admin")){
                    getJspBody().invoke(sw);
                    getJspContext().getOut().println(sw.toString());
                }
                if(CheckUserPermissionUtils.permUrl.containsKey(obj.getUid())&&CheckUserPermissionUtils.permUrl.get(obj.getUid()).containsKey(url)){
                    getJspBody().invoke(sw);
                    getJspContext().getOut().println(sw.toString());
                }
            }
        }
    }
}
