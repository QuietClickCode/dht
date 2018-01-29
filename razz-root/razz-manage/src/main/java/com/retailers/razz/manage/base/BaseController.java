package com.retailers.razz.manage.base;


import com.retailers.auth.constant.SystemConstant;
import com.retailers.auth.entity.SysUser;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseWrite;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.ValidationUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 公用controller
 */
public class BaseController extends BaseWrite {
    protected void validateForm(Object obj)throws AppException {
        ValidationUtils.validate(obj);
    }
    /**
     * 取得当前登陆用户
     * @param request
     * @return
     */
    protected SysUser getCurLoginUser(HttpServletRequest request){
        Object obj =request.getSession().getAttribute(SystemConstant.LOG_USER_SESSION_KEY);
        SysUser sysUser=null;
        if(ObjectUtils.isNotEmpty(obj)){
            sysUser=(SysUser)obj;
        }
        return sysUser;
    }
    /**
     * 取得当前登陆用户 id
     * @param request
     * @return
     */
    protected Long getCurLoginUserId(HttpServletRequest request){
        Object obj =request.getSession().getAttribute(SystemConstant.LOG_USER_SESSION_KEY);
        SysUser sysUser=null;
        if(ObjectUtils.isNotEmpty(obj)){
            sysUser=(SysUser)obj;
            return sysUser.getUid();
        }
        return null;
    }
    /**
     * 取得当前登陆用户
     * @param request
     * @return
     */
    protected void setCurLoginUser(HttpServletRequest request,SysUser sysUser){
        request.getSession().setAttribute(SystemConstant.LOG_USER_SESSION_KEY,sysUser);
    }

    /**
     * 返回分页信息
     * @param pages
     * @return
     */
    protected Map<String,Object> queryPages(Pagination<?> pages){
        Map<String,Object> rtn=new HashMap<String, Object>();
        rtn.put("total",pages.getTotalCount());
        rtn.put("rows",pages.getData());
        return rtn;
    }
}
