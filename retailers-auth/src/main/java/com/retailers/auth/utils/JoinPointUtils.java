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
        String targetName=joinPoint.getTarget().getClass().getName();//取得目标对象,目标对象的字节码对象,取得类的全路径名称
        //getSimpleName()就是取得类的名称
        String methodName = joinPoint.getSignature().getName();//获取连接点的方法签名对象.获取目标对象切点方法名字


        Object[] arguments = joinPoint.getArgs();//获取获取传入目标方法的参数对象
        Class targetClass = Class.forName(targetName);//利用反射得到目标对象的字节码对象
        Method[] methods = targetClass.getMethods();//取得所有目标对象的所有方法对象
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {//方法名和连接点的方法名一样
                Class[] clazzs = method.getParameterTypes();//得到参数类型列表
                if (clazzs.length == arguments.length) {//列表长度和目标方法参数长度一样
                    return method.getAnnotation(t);//得到注解
                    //这儿其实,又判断方法名一样,有判断方法参数是否一样,其实就是怕重载的情况
                    //至于为什么getClass()得到字节码为什么还要用反射得到字节码,要么就是代码冗余
                    //要么就是这个targetName其他地方还有用
                    //这儿要把jointpoint想成pointcut
                }
            }
        }
        return null;
    }
}
