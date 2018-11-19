package cn.iflyapi.ihungry.databind;

/**
 * @author: qfwang
 * @date: 2018-11-19 2:19 PM
 */
public interface ConverterRegistry {

    void registerConverter(Class<?> clazz, Converter converter);

    Converter findCustomConverter(Class<?> clazz);
}
