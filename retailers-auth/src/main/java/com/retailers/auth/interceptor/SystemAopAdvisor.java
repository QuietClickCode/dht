package com.retailers.auth.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SystemAopAdvisor {

	ThreadLocal<Long> time=new ThreadLocal<Long>();
	ThreadLocal<String> tag=new ThreadLocal<String>();

	@Pointcut("@annotation(com.retailers.auth.annotation.SystemAopLog)")
	public void log(){
		System.out.println("我是一个切入点");
	}

	/**
	 * 在所有标注@Log的地方切入
	 * @param joinPoint
	 */
	@Before("log()")
	public void beforeExec(JoinPoint joinPoint){
		System.out.println("开始执行络");
//		time.set(System.currentTimeMillis());
//		tag.set(UUID.randomUUID().toString());
//
////		info(joinPoint);fb
//
//		MethodSignature ms=(MethodSignature) joinPoint.getSignature();
//		Method method=ms.getMethod();
//		System.out.println(method.getAnnotation(SystemAopLog.class).name()+"标记"+tag.get());
	}

	@After("log()")
	public void afterExec(JoinPoint joinPoint){
//		info(joinPoint);
		System.out.println("开始执行络afterExec");
//		MethodSignature ms=(MethodSignature) joinPoint.getSignature();
//		Method method=ms.getMethod();
//		System.out.println("标记为"+tag.get()+"的方法"+method.getName()+"运行消耗"+(System.currentTimeMillis()-time.get())+"ms");
	}

	@Around("log()")
	public Object aroundExec(ProceedingJoinPoint pjp) throws Throwable{
		String name = pjp.getSignature().getName();// 获得目标方法名
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = pjp.proceed();
            long end = System.currentTimeMillis();
        } catch (Throwable e) {
        	e.printStackTrace();
        	throw e;
        }
        return result;
	}

	private void info(JoinPoint joinPoint){
		System.out.println("--------------------------------------------------");
		System.out.println("King:\t"+joinPoint.getKind());
		System.out.println("Target:\t"+joinPoint.getTarget().toString());
		Object[] os=joinPoint.getArgs();
		System.out.println("Args:");
		for(int i=0;i<os.length;i++){
			System.out.println("\t==>参数["+i+"]:\t"+os[i].toString());
		}
		System.out.println("Signature:\t"+joinPoint.getSignature());
		System.out.println("SourceLocation:\t"+joinPoint.getSourceLocation());
		System.out.println("StaticPart:\t"+joinPoint.getStaticPart());
		System.out.println("--------------------------------------------------");
	}

}
