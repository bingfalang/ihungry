package cn.iflyapi.ihungry.databind;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;

/**
 * @author: qfwang
 * @date: 2018-11-17 2:41 PM
 */
public class NumberConverter extends PropertyEditorSupport {

    private Class clazz;
    private NumberFormat numberFormat;
    private boolean allowEmpty;

    public NumberConverter(Class clazz, NumberFormat numberFormat, boolean allowEmpty) {
        this.clazz = clazz;
        this.numberFormat = numberFormat;
        this.allowEmpty = allowEmpty;
    }

    @Override
    public void setValue(Object value) {
        super.setValue(value);
    }

    @Override
    public Object getValue() {
        return super.getValue();
    }

    @Override
    public String getAsText() {
        return super.getAsText();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        super.setAsText(text);
    }
}
