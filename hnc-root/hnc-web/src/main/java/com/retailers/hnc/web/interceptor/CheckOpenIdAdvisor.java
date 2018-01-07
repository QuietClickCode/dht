package com.retailers.hnc.web.interceptor;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.utils.JoinPointUtils;
import com.retailers.hnc.web.annotation.CheckOpenId;
import com.retailers.hnc.web.constant.WebSystemConstant;
import com.retailers.tools.base.WriteData;
import com.retailers.tools.encrypt.DESUtils;
import com.retailers.tools.encrypt.DesKey;
import com.retailers.tools.utils.ObjectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;

/**
 * Created by admin on 2017/5/3.
 */
@Aspect
@Component
public class CheckOpenIdAdvisor {

    // Service层切点
    @Pointcut("@annotation(com.retailers.hnc.web.annotation.CheckOpenId)")
    public void CheckOpenId() {
        System.out.println("CheckOpenId==============================================================>>>>");
    }

    /**
     * 执行方法前进行校验
     * @param joinPoint
     * @return
     * @throws Throwable
     * @throws Exception
     */
    @Around(value="CheckOpenId()")
    public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable,Exception {
        System.out.println("CheckOpenId================================doBefore==============================>>>>");
        Object result = null;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String randStr = request.getParameter("randStr");
        String msg = "";
        String redirect = "";
        try {
            if(!"".equals(randStr)&&null!=randStr){
                Object obj = JoinPointUtils.getMethod(joinPoint,CheckOpenId.class);
                if(ObjectUtils.isNotEmpty(obj)&&obj instanceof CheckOpenId){
                    CheckOpenId co = (CheckOpenId)obj;
//                    randStr = URLDecoder.decode(randStr,"utf-8");
//                    randStr = DESUtils.decryptDES(randStr, DesKey.WEB_KEY);
                    Map<String,Object> map = WebSystemConstant.globelOpenId;
                    Object randStrObj =  map.get(randStr);

                    if(ObjectUtils.isEmpty(randStrObj)){
                        msg = "用户无效";
                        redirect="/failCheckOpenId";
                        HttpServletResponse response= ((ServletRequestAttributes) RequestContextHolder
                                .getRequestAttributes()).getResponse();
                        WriteData.writeObject(WriteData.LOGIN_OUT,msg,redirect,response);
                    }else{
                        result = joinPoint.proceed();
                    }
                }
            }else{
                HttpServletResponse response= ((ServletRequestAttributes) RequestContextHolder
                        .getRequestAttributes()).getResponse();
                WriteData.writeObject(WriteData.LOGIN_OUT,"用户不能为空","/failCheckOpenId",response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }


}
