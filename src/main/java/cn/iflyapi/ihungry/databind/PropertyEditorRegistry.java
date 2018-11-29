package cn.iflyapi.ihungry.databind;

import java.beans.PropertyEditor;

/**
 * @author: qfwang
 * @date: 2018-11-19 2:19 PM
 */
public interface PropertyEditorRegistry {

    void registerConverter(Class<?> clazz, PropertyEditor propertyEditor);

    PropertyEditor findCustomConverter(Class<?> clazz);
}
