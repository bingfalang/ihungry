package cn.iflyapi.ihungry.util;

import java.text.SimpleDateFormat;

/**
 * @author: qfwang
 * @date: 2018-11-04 10:36 PM
 */
public class Constant {
    public static final long PERIOD_DAY = 24 * 60 * 60 * 1000;

    public static final String URL = "https://oapi.dingtalk.com/robot/send?access_token=*";

    public static final String API_HOLIDAY_DATE = "https://api.goseek.cn/Tools/holiday?date=";

    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

}
