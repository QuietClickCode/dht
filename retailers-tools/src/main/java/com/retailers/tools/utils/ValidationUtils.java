package com.retailers.tools.utils;

import com.retailers.tools.exception.AppException;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by admin on 2017/9/26.
 */
public class ValidationUtils {//校验工具类
    /**
     * 使用hibernate的注解来进行验证
     *
     */
    private static Validator validator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();
    //利用反射得到一个Hibernate提供的一个校验器

    /**
     * 功能描述: <br>
     * 〈注解验证参数〉
     *
     * @param obj
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T> void validate(T obj) throws AppException {//泛型通配校验各种类型
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        // 抛出检验异常
        if (constraintViolations.size() > 0) {//抛出自定义异常
           /* private String message;//消息属性
            private Integer status;//状态属性
            private String code;//状态码
            private Object data;//数据*/
           //抛出的这个重载方法
        /*  public AppException(String code,String message) {//构造方法,多个重载
                super(message);
                this.message = message;
                this.code = code;
            }*/
            throw new AppException("0001", String.format("参数校验失败:%s", constraintViolations.iterator().next().getMessage()));
        }
    }

}
