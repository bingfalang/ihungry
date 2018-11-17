package cn.iflyapi.ihungry.databind;

/**
 * @author: qfwang
 * @date: 2018-11-17 2:41 PM
 */
public class NumberConverter implements Converter<String,Number> {
    @Override
    public Number convert(String source, Class clazz) {
        if (clazz == int.class) {
            return Integer.valueOf(source);
        }
        if (clazz == long.class) {
            return Long.valueOf(source);
        }
        if (clazz == double.class) {
            return Double.valueOf(source);
        }
        if (clazz == byte.class) {
            return Byte.valueOf(source);
        }
        if (clazz == float.class) {
            return Float.valueOf(source);
        }
        if (clazz == short.class) {
            return Short.valueOf(source);
        }
        return null;
    }
}
