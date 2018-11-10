package cn.iflyapi.ihungry.annotation;

import java.lang.annotation.*;

/**
 * @author: qfwang
 * @date: 2018-11-10 9:24 AM
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Qualifier {
    String value() default "";
}
