package cn.iflyapi.ihungry.annotation;

import java.lang.annotation.*;

/**
 * @author: qfwang
 * @date: 2018-11-10 9:25 AM
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PostMapping {
    String value() default "";
    String method() default "POST";
}
