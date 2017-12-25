package com.retailers.hnc.web.annotation;

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
public @interface CheckOpenId {
    String status() default "";

    String msg() default "";
}
