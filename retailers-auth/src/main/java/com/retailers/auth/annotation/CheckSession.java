package com.retailers.auth.annotation;

/**
 * Created by admin on 2017/5/3.
 */

import java.lang.annotation.*;

/**
 * 校验session
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckSession {
    /**
     * session 中的key
     * @return
     */
    String key();

    /**
     * 提示消息
     * @return
     */
    String msg() default "";

    /**
     * 重定向地址页面地址
     * @return
     */
    String redirectUrl() default "";

    /**
     * 是否重定向页面
     * @return
     */
    boolean isOpenPage() default  false;
}
