package com.retailers.auth.utils;

import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Method;

/**
 * 取得注解
 */
public class JoinPointUtils {
    /**
     * 取得注解中的方法
     * @param joinPoint
     * @param t
     * @return
     * @throws Exception
     */
    public static Object getMethod(JoinPoint joinPoint, Class t)throws Exception{
        String targetName=joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    return method.getAnnotation(t);
                }
            }
        }
        return null;
    }
}
