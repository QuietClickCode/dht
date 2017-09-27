package com.retailers.auth.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 自定义菜单->功能按钮 注解功能
 *
 * @author zhongp
 *
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Function {
	/**
	 * 父级菜单 如果不存在或为空则为父级
	 *
	 * @return
	 */
	String parentRes() default "";

	/**
	 * 菜单标示（唯一值）
	 *
	 * @return
	 */
	String resourse();

	/**
	 * 菜单是否有效 默认为有效
	 *
	 * @return
	 */
	boolean isValid() default true;

	/**
	 * 菜单路径修改前的地址
	 *
	 * @return
	 */
	String changeRes() default "";

	/**
	 * 显示菜单名称
	 *
	 * @return
	 */
	String label();

	/**
	 * 菜单描述
	 *
	 * @return
	 */
	String description() default "";

	/**
	 * 菜单显示顺序
	 *
	 * @return
	 */
	int sort() default 1;

	/**
	 * 菜单显示图标
	 *
	 * @return
	 */
	String icon() default "";
}
