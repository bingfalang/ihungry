package cn.iflyapi.ihungry.databind;

import java.beans.PropertyEditorSupport;

/**
 * @author: qfwang
 * @date: 2018-11-29 8:27 PM
 */
public class BoleanEditor extends PropertyEditorSupport {

    public static final String VALUE_TRUE = "true";
    public static final String VALUE_FALSE = "false";

    public static final String VALUE_ON = "on";
    public static final String VALUE_OFF = "off";

    public static final String VALUE_YES = "yes";
    public static final String VALUE_NO = "no";

    public static final String VALUE_1 = "1";
    public static final String VALUE_0 = "0";


    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (VALUE_TRUE.equalsIgnoreCase(text) || VALUE_ON.equalsIgnoreCase(text) ||
                VALUE_YES.equalsIgnoreCase(text) || VALUE_1.equalsIgnoreCase(text)) {
            setValue(Boolean.TRUE);
        } else {
            setValue(Boolean.FALSE);
        }
    }
}
