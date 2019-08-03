package com.retailers.dht.web.base;


import com.alibaba.fastjson.JSON;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.User;
import com.retailers.dht.common.view.UserInfoVIew;
import com.retailers.dht.web.constant.WebSystemConstant;
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
public class BaseController extends BaseWrite {//基本控制器,用于被继承,子类自动具有相应的通用方法
    protected void validateForm(Object obj)throws AppException {//校验表单方法
        ValidationUtils.validate(obj);//这儿利用hibernate提供的的注解式validator工具写了一个工具类和自定义异常来进行对实体类的字段进行校验
    }
    /**
     * 取得当前登陆用户相关的信息
     * @param request
     * @return
     */
    /**UserInfo
     * 用户信息（详情）
     * @author zhongp
     * @version 1.0.1
     * @data 2017/11/22
     */
    //从session中获取登录用户的对象
    protected UserInfoVIew getCurLoginUser(HttpServletRequest request){
        Object obj =request.getSession().getAttribute(SystemConstant.LOG_USER_SESSION_KEY);//从session中获取登录用户的对象user
        UserInfoVIew userInfo=null;
        if(ObjectUtils.isNotEmpty(obj)){
            userInfo=(UserInfoVIew)obj;
        }
        return userInfo;
    }
    /**
     * 取得当前登陆用户 id
     * @param request
     * @return
     */
    protected Long getCurLoginUserId(HttpServletRequest request){//
        Object obj =request.getSession().getAttribute(SystemConstant.LOG_USER_SESSION_KEY);
        UserInfoVIew sysUser=null;
        if(ObjectUtils.isNotEmpty(obj)){
            sysUser=(UserInfoVIew)obj;
            return sysUser.getUid();
        }
        return null;
    }
    /**
     * 取得分享用户 id
     * @param request
     * @return
     */
    protected Long getShareUserId(HttpServletRequest request){
        Object obj =request.getSession().getAttribute(SystemConstant.SHARE_USER_SESSION_KEY);//shareUser
        if(ObjectUtils.isNotEmpty(obj)){
            return (Long) obj;
        }
        return null;
    }


    /**
     * 取得分享用户 id
     * @param request
     * @return
     */
    protected void setShareUserId(HttpServletRequest request,Long uid){//设置分享用户id
        request.getSession().setAttribute(SystemConstant.SHARE_USER_SESSION_KEY,uid);
    }
    /**
     * 设置分享商品 id
     * @param request
     * @return
     */
    protected void setShareGoodsId(HttpServletRequest request,Long goodsId){
        request.getSession().setAttribute(SystemConstant.SHARE_GOODSID_SESSION_KEY,goodsId);
    }

    protected Long getShareGoodsId(HttpServletRequest request){
        return (Long)request.getSession().getAttribute(SystemConstant.SHARE_GOODSID_SESSION_KEY);
    }
    /**
     * 取得当前登陆用户
     * @param request
     * @return
     */
    protected void setCurLoginUser(HttpServletRequest request,UserInfoVIew userInfo){
        request.getSession().setAttribute(SystemConstant.LOG_USER_SESSION_KEY,userInfo);
    }

    /**
     * 返回分页信息
     * @param pages
     * @return
     */
    protected Map<String,Object> queryPages(Pagination<?> pages){
        System.out.println(JSON.toJSON(pages));
        Map<String,Object> rtn=new HashMap<String, Object>();
        rtn.put("total",pages.getTotalCount());
        rtn.put("rows",pages.getData());
        rtn.put("totalCount",pages.getTotalCount());
        return rtn;
    }

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
