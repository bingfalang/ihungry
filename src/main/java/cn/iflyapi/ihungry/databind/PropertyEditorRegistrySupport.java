package cn.iflyapi.ihungry.databind;

import com.sun.beans.editors.BooleanEditor;
import com.sun.beans.editors.StringEditor;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: qfwang
 * @date: 2018-11-19 2:20 PM
 */
public class PropertyEditorRegistrySupport implements PropertyEditorRegistry {

    private Map<Class<?>, PropertyEditor> defaultConverters = new HashMap<>();

    public PropertyEditorRegistrySupport() {
        createDefaultConverters();
    }

    @Override
    public void registerConverter(Class<?> clazz, PropertyEditor propertyEditor) {
        defaultConverters.put(clazz, propertyEditor);
    }

    @Override
    public PropertyEditor findCustomConverter(Class<?> clazz) {
        return defaultConverters.get(clazz);
    }

    private void createDefaultConverters() {
        registerConverter(Byte.class, new NumberEditor(Byte.class, true));
        registerConverter(byte.class, new NumberEditor(Byte.class, false));
        registerConverter(Short.class, new NumberEditor(Short.class, true));
        registerConverter(short.class, new NumberEditor(Short.class, false));
        registerConverter(Integer.class, new NumberEditor(Integer.class, true));
        registerConverter(int.class, new NumberEditor(Integer.class, false));
        registerConverter(Double.class, new NumberEditor(Double.class, true));
        registerConverter(double.class, new NumberEditor(Double.class, false));

        registerConverter(Boolean.class,new BooleanEditor());
        registerConverter(boolean.class,new BooleanEditor());

        registerConverter(String.class,new StringEditor());

    }
}
