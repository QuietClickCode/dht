package com.retailers.auth.interceptor;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.auth.utils.JoinPointUtils;
import com.retailers.tools.base.WriteData;
import com.retailers.tools.utils.ObjectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by admin on 2017/5/3.
 */
@Aspect//这是一个切面=切点+通知
@Component
public class CheckSessionAdvisor {
    Logger logger = LoggerFactory.getLogger(CheckSessionAdvisor.class);

    // Service层切点
    @Pointcut("@annotation(com.retailers.auth.annotation.CheckSession)")//这是切点,切点为注解
    public void checkSession() {
    }

    /**
     * 执行方法前进行校验
     * @param joinPoint
     * @return
     * @throws Throwable
     * @throws Exception
     */
    @Around(value="checkSession()")
    public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable,Exception {
        logger.info("进入session校验aop 开始");
        Object result = null;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();//springmvc获取request的api
        HttpSession session = request.getSession();//取得session
        if(ObjectUtils.isNotEmpty(session)){
            Object obj = JoinPointUtils.getMethod(joinPoint,CheckSession.class);//得到注解
            if(ObjectUtils.isNotEmpty(obj)&&obj instanceof CheckSession){
                CheckSession cs = (CheckSession)obj;
                if(ObjectUtils.isNotEmpty(session.getAttribute(cs.key()))){
                    result = joinPoint.proceed();//注解key不为空,执行目标方法
                }else{
                    String msg = cs.msg();
                    if(ObjectUtils.isEmpty(msg)){
                        msg="["+cs.key()+"]不能为空";
                    }
                    String referer=request.getHeader("Referer");
                    String redirect=cs.redirectUrl();
                    if(ObjectUtils.isEmpty(redirect)){
                        redirect="/loginPage";
                    }
                    //是否需要重定向
                    boolean isRedirect=cs.isOpenPage();
                    boolean isResponse=false;
                    for (Object param : joinPoint.getArgs()) {
                        if (param instanceof HttpServletResponse) {
                            isResponse=true;
                        }
                    }
                    //是否需要重定向
                    if(isRedirect){
                        Map<String,Object> params = WebUtils.getParametersStartingWith(request,"");
                        String oldUrl=generateRedirectParams(request,params);
                        if(isResponse){
                            HttpServletResponse response= ((ServletRequestAttributes) RequestContextHolder
                                    .getRequestAttributes()).getResponse();
                            redirect=redirect+"?redirectUrl="+oldUrl;
                            response.sendRedirect(redirect);
                        }else{
                            ModelAndView modelAndView=new ModelAndView(redirect);
                            modelAndView.addObject("redirectUrl",oldUrl);
                            return modelAndView;
                        }
//                        result="/loginPage";
                    }else{
                        HttpServletResponse response= ((ServletRequestAttributes) RequestContextHolder
                                .getRequestAttributes()).getResponse();
                        WriteData.writeObject(WriteData.SESSION_TIME_OUT,msg,redirect,response);
                    }

//                    if(ObjectUtils.isNotEmpty(redirect)&&!isLoca){
//                        response.sendRedirect(redirect);
//                    }else{
//                        WriteData.writeObject(WriteData.LOGIN_OUT,msg,redirect,response);
//                    }
                }
            }
        }else{
            logger.info("进入session校验aop 结束");
            throw new Exception("session异常");
        }
        return result;
    }

    private String generateRedirectParams(HttpServletRequest request,Map<String,Object> params){
        String uri=request.getRequestURI();
        String rtn="";
        if(ObjectUtils.isNotEmpty(params)){
            for(String key:params.keySet()){
                rtn+=key+"="+params.get(key)+"&";
            }
            if(rtn.endsWith("&")){
                rtn=rtn.substring(0,rtn.length()-1);
            }
            if(ObjectUtils.isNotEmpty(rtn)){
                try{
                    rtn=uri+URLEncoder.encode("?"+rtn,"utf-8");
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }else{
            rtn=uri;
        }
        return rtn;
    }

}
