package com.retailers.tools.exception;

import com.retailers.tools.base.WriteData;
import com.retailers.tools.encrypt.DESUtils;
import com.retailers.tools.encrypt.DesKey;
import com.retailers.tools.encrypt.ParamKey;
import com.retailers.tools.encrypt.PlatformContant;
import com.retailers.tools.utils.MessageUtil;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 自定义异常处理
 * @author exu
 * @date 2015-07-06
 */
public class AppSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {
    private Logger appLog = Logger.getLogger("appLog");
    private Logger reqLog = Logger.getLogger("reqLog");

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        writeLog(request);
        String basePath = "";
        String upath = getRemoteAddrIp(request);
        if (ex instanceof AppException) {
            AppException appEx = (AppException) ex;
            if (ObjectUtils.isEmpty(appEx.getStatus())) {
                //返回json
                WriteData.writeObjects(WriteData.SERVER_ERROR, MessageUtil.getMessage(appEx.getMessage()),null,response);

            } else {
                WriteData.writeObjects(appEx.getStatus(), MessageUtil.getMessage(appEx.getMessage()),appEx.getData(),response);
            }
            return new ModelAndView("error");
        }

        //给log4j.properties文件中参数赋值
        StringBuffer ip = new StringBuffer();
        ip.append("服务器:").append(basePath).append("-用户:").append(upath);
        MDC.put("ip", ip.toString());
        String message =  ex.getMessage() +"\n" +"接口："+request.getRequestURI()+ ",参数：" + getRequestParam(request);
        appLog.error(message, ex);
        //返回json
        WriteData.systemError(response);
        return null;
    }
    private String getRequestParam(HttpServletRequest request) {
        //获取参数
        Map<String,String> map = new TreeMap<String, String>();
        Enumeration<String> en = request.getParameterNames();
        while (en.hasMoreElements()) {
            String key = en.nextElement();
            map.put(key,request.getParameter(key));
        }
        try {
            String field = ParamKey.getPropertyParam(PlatformContant.PF_WEB, request.getRequestURI());
            if(field != null){
                //取得必要的参数
                String[] f = field.split(",");
                for (int i = 0; i < f.length; i++) {
                    //des解密参数
                    String value = map.get(f[i]);
                    value = DESUtils.decryptDES(value, DesKey.WEB_KEY);
                    map.put(f[i],value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuffer param = new StringBuffer();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            param.append(entry.getKey()).append("=").append(entry.getValue());
            if (it.hasNext()) {
                param.append("&");
            }
        }
        return param.toString();
    }
    public static String getRemoteAddrIp(HttpServletRequest request) {
        String ipFromNginx = request.getHeader("X-Real-IP");
        return StringUtils.isEmpty(ipFromNginx) ? request.getRemoteAddr() : ipFromNginx;
    }

    public void writeLog(HttpServletRequest req) {
        Date respTime = new Date();
        Date reqTime = (Date) req.getAttribute("reqTime");
        if (reqTime == null) {
            reqTime = respTime;
        }
        long actionTime = respTime.getTime() - reqTime.getTime();
//        reqLog.info("接口={" + req.getRequestURI() + "},请求时间={" + DateUtil.dateToString(respTime, "yyyy-MM-dd HH:mm:ss:SSS") + "},响应时间={"
//                + DateUtil.dateToString(respTime, "yyyy-MM-dd HH:mm:ss:SSS") + "},执行时间=" + actionTime + "毫秒,请求参数=" + JsonUtil.write2JsonStr(req.getParameterMap()));
    }
}
