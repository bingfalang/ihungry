package cn.iflyapi.ihungry.databind;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;

/**
 * @author: qfwang
 * @date: 2018-11-17 2:41 PM
 */
public class NumberEditor extends PropertyEditorSupport {

    private Class clazz;
    private boolean allowEmpty;

    public NumberEditor(Class clazz, boolean allowEmpty) {
        this.clazz = clazz;
        this.allowEmpty = allowEmpty;
    }

    @Override
    public Object getValue() {
        return super.getValue();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Object o;
        if (Byte.class == clazz) {
            o = Byte.valueOf(text);
        } else if (Short.class == clazz) {
            o = Short.valueOf(text);
        } else if (Integer.class == clazz) {
            o = Integer.valueOf(text);
        } else if (Long.class == clazz) {
            o = Long.valueOf(text);
        } else if (BigInteger.class == clazz) {
            o = new BigInteger(text);
        } else if (Float.class == clazz) {
            o = Float.valueOf(text);
        } else if (Double.class == clazz) {
            o = Double.valueOf(text);
        } else if (BigDecimal.class == clazz || Number.class == clazz) {
            o = new BigDecimal(text);
        } else {
            throw new IllegalArgumentException(
                    "Cannot convert String [" + text + "] to target class [" + clazz.getName() + "]");
        }
        super.setValue(o);
    }
}
