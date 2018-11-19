package cn.iflyapi.ihungry.databind;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: qfwang
 * @date: 2018-11-19 2:20 PM
 */
public class ConverterRegistrySupport implements ConverterRegistry {

    private Map<Class<?>, Converter> defaultConverters = new HashMap<>();

    @Override
    public void registerConverter(Class<?> clazz, Converter converter) {
        defaultConverters.put(clazz, converter);
    }

    @Override
    public Converter findCustomConverter(Class<?> clazz) {
        return defaultConverters.get(clazz);
    }

    public void createDefaultConverters(){

    }
}
