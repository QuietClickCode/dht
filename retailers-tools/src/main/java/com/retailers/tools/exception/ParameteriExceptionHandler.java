package com.retailers.tools.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retailers.tools.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@EnableWebMvc
@ControllerAdvice
public class ParameteriExceptionHandler {
    private Logger logger = Logger.getLogger(ParameteriExceptionHandler.class);

    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Object handleTypeMismatchException(HttpServletRequest request, TypeMismatchException ex) {

        Map<String, Object> params = WebUtils.getParametersStartingWith(request, "");
        String errorMsg = "请求url:" + request.getRequestURI();
        errorMsg += "\r\n请求参数：" + JSON.toJSONString(params) + "\r\n";
        errorMsg += "错误类型：【" + "参数类型不匹配,参数" + ex.getPropertyName() + "类型应该为" + ex.getRequiredType() + "，参数值：" + ex.getValue() + "】\r\n";
        String msg= "错误类型：【" + "参数类型不匹配,参数" + ex.getPropertyName() + "类型应该为" + ex.getRequiredType() + "，参数值：" + ex.getValue() + "】\r\n";
        logger.info(errorMsg);
        ex.printStackTrace();
        JSONObject json = new JSONObject();
        json.put("status", "-1");
        json.put("msg", "请求参数错误\r\n"+msg);
        json.put("data", new JSONObject());
        return json;
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Object handleBindException(HttpServletRequest request, BindException ex) {

        Map<String, Object> params = WebUtils.getParametersStartingWith(request, "");
        String errorMsg = "请求url:" + request.getRequestURI();
        errorMsg += "\r\n请求参数：" + JSON.toJSONString(params) + "\r\n";
        errorMsg += "错误类型：\r\n"+ StringUtils.getErrorInfoFromException(ex);
        logger.info(errorMsg);
        JSONObject json = new JSONObject();
        json.put("status", "-1");
        json.put("msg", "请求参数错误\r\n"+ex.getMessage());
        json.put("data", ex.getMessage());
        return json;
    }
}
