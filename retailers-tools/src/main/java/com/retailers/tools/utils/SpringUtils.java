package com.retailers.tools.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>User: zhongp
 * <p>Date: 13-12-16
 * <p>Version: 1.0
 */
@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringUtils.context = context;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T)context.getBean(name);
    }
}
