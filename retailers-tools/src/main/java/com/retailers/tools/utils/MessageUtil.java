package com.retailers.tools.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @author exu
 * @version V1.0
 * @Description:
 * @date 2016/10/17 21:30
 */
//泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注
@Component
public class MessageUtil {
    private static MessageUtil messageUtil;
    @Resource
    private MessageSource messageSource;

    //@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行,init()方法之前执行
    @PostConstruct
    public void initialize() {
        messageUtil = this;
        messageUtil.messageSource = this.messageSource;
        System.out.println("#####################messageUtil=执行init方法");
    }

    public static String getMessage(String code) {
        return getMessage(code, null, null);
    }

    public static String getMessage(String code, Object... info) {
        return getMessage(code, null, info);
    }

    public static String getMessage(String code, HttpServletRequest request, Object... info) {
        if (code.indexOf("{") >= 0 && code.indexOf("}") >= 0) {
            code = code.replace("{", "").replace("}", "");
            Locale locale = null;
            if (null == request) {
                locale = LocaleContextHolder.getLocale();
            } else {
                locale = RequestContextUtils.getLocale(request);
            }
            return messageUtil.messageSource.getMessage(code, info, "", locale);
        } else {
            return code;
        }
    }

    public MessageSource getMessageSource() {
        return messageUtil.messageSource;
    }
}
