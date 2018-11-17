package cn.iflyapi.ihungry.databind;

/**
 * @author: qfwang
 * @date: 2018-11-17 2:39 PM
 */
public interface Converter<S,T> {
    T convert(S source, Class clazz);
}
