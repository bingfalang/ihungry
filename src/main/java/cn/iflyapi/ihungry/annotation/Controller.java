package cn.iflyapi.ihungry.annotation;

import java.lang.annotation.*;

/**
 * @author: qfwang
 * @date: 2018-11-10 9:20 AM
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
    String value() default "";
}
