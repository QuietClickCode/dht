package com.retailers.dht.manage.filter;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.auth.entity.SysUser;
import com.retailers.dht.common.entity.LogManagerReq;
import com.retailers.dht.common.service.LogManagerReqService;
import com.retailers.tools.utils.DateUtil;
import com.retailers.tools.utils.IPUtil;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * 描述：接口请求日志拦截器
 * @author zhongp
 * @date 2016/12/08
 */
public class ReqInterceptor implements HandlerInterceptor {
    Logger reqLog = LoggerFactory.getLogger(ReqInterceptor.class);
    Logger logger = LoggerFactory.getLogger(ReqInterceptor.class);
    @Autowired
    private LogManagerReqService logManagerReqService;

    //reqLog
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        logger.info("进入拦截");
        req.setAttribute("sysManagerReqTime", new Date());
        return true;
    }

    public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle进入拦截");
    }

    /**
     * 访问结束时的回调
     * @param req
     * @param resp
     * @param handler
     * @param e
     * @throws Exception
     */
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception e) throws Exception {
        logger.info("afterCompletion 完成拦截");
        //资源文件
        if (req.getRequestURI().indexOf(".") != -1 || req.getRequestURI().equals("/")){
            return;
        }
        Map<String,Object> parms = WebUtils.getParametersStartingWith(req,"");
        Date reqTime = (Date) req.getAttribute("sysManagerReqTime");
        Date respTime = new Date();
        long actionTime = respTime.getTime() - reqTime.getTime();
        reqLog.info("请求地址：[{}],请求时间:[{}],响应时间：:[{}],执行时间::[{}],请求参数：[{}]",req.getRequestURI(),
                DateUtil.dateToString(reqTime,"YYYY-MM-dd HH:mm:ss:SSS"), DateUtil.dateToString(respTime,"YYYY-MM-dd HH:mm:ss:SSS"),actionTime,JSON.toJSONString(parms));
        long isError=0;
        LogManagerReq log=new LogManagerReq();
        if(ObjectUtils.isNotEmpty(e)){
            log.setContext(StringUtils.getErrorInfoFromException(e));
        }
        log.setExecuteTime(actionTime);
        log.setParams(JSON.toJSONString(parms));
        log.setCreateTime(reqTime);
        log.setUrl(req.getRequestURI());
        log.setIsError(isError);
        log.setReqIp(IPUtil.getIpAddr(req));
        if(ObjectUtils.isNotEmpty(req.getSession().getAttribute(SystemConstant.LOG_USER_SESSION_KEY))){
            SysUser sysUser=(SysUser)req.getSession().getAttribute(SystemConstant.LOG_USER_SESSION_KEY);
            log.setReqUser(sysUser.getUid());
        }
        logManagerReqService.saveLogManagerReq(log);
    }
}

