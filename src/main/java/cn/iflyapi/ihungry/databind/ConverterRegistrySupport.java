package cn.iflyapi.ihungry.databind;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: qfwang
 * @date: 2018-11-19 2:20 PM
 */
public class ConverterRegistrySupport implements ConverterRegistry {

    private Map<Class<?>, PropertyEditor> defaultConverters = new HashMap<>();

    @Override
    public void registerConverter(Class<?> clazz, PropertyEditor propertyEditor) {
        defaultConverters.put(clazz, propertyEditor);
    }

    @Override
    public PropertyEditor findCustomConverter(Class<?> clazz) {
        return defaultConverters.get(clazz);
    }

    public void createDefaultConverters() {
        registerConverter(Byte.class, new NumberConverter(Byte.class, true));
        registerConverter(byte.class, new NumberConverter(Byte.class, false));
        registerConverter(Short.class, new NumberConverter(Short.class, true));
        registerConverter(short.class, new NumberConverter(Short.class, false));
        registerConverter(Integer.class, new NumberConverter(Integer.class, true));
        registerConverter(int.class, new NumberConverter(Integer.class, false));
        registerConverter(Double.class, new NumberConverter(Double.class, true));
        registerConverter(double.class, new NumberConverter(Double.class, false));

    }
}
