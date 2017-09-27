package com.retailers.auth.interceptor;

import java.lang.reflect.Method;

import com.retailers.auth.annotation.Menu;
import com.retailers.tools.utils.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Controller层中对注解有@Menu的方法，系统自动验证有无权限访问菜单
 *
 * @author zhongp
 *
 */
@Aspect
@Component
public class MenuBeforeAdvisor {
	Logger logger = LoggerFactory.getLogger(MenuBeforeAdvisor.class);
	/**
	 * 验证权限的开关，默认不验证权限, authSwitch= ON时，验证访问权限，默认不验证
	 */
	private static String authSwitch = null;

	@Before("@within(org.springframework.stereotype.Controller)&&@annotation(com.retailers.auth.annotation.Menu)&&@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void menuCheckPointcut(JoinPoint joinPoint) throws Throwable {
		// 读入外部开关配置
		if (ObjectUtils.isEmpty(authSwitch)) {
			authSwitch = System.getProperty("authSwitch", null);
		}

		// 判断 是否打开菜单访问权限验证，如果
		if (authSwitch != null && "ON".equalsIgnoreCase(authSwitch)) {
			logger.info("开始验证菜单访问权限。。。" + joinPoint.toLongString());

			Method method = ((MethodSignature) joinPoint.getSignature())
					.getMethod();

			Menu menu = (Menu) method.getAnnotation(Menu.class);
			if (ObjectUtils.isNotEmpty(menu)) {
//				String resource = menu.resource();
//				// 判断是否有权限操作
//				boolean result = CRA.canReadResource(resource);
//				logger.debug("权限验证：" + result);
//				if (!result) {
//					AccountDTO a = (AccountDTO) CRA.getAccount();
//					logger.error("恶意的功能访问:" + resource + ", 用户帐号："
//							+ (a == null ? "未认证的用户" : a.getAccount()));
//					throw new AccessDenyedException(resource, menu.label());
//				}
			}
		}
	}
}
