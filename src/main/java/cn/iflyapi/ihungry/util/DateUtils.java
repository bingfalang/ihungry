package cn.iflyapi.ihungry.util;

import java.time.LocalDate;
import java.time.ZoneId;

/**
 * @author: qfwang
 * @date: 2018-11-04 9:55 AM
 */
public class DateUtils {

    public static Long getTodayStart() {
        return LocalDate.now().atTime(0, 0, 0)
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
    }

    public static Long getTodayEnd() {
        return LocalDate.now().atTime(23, 59, 59)
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
    }

    public static Long getTodaySomeTime(int hour, int minutes) {
        return LocalDate.now().atTime(hour, minutes, 0)
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
    }

    public static void main(String[] args) {
        System.out.println(getTodayStart());
        System.out.println(getTodayEnd());
        System.out.println(System.currentTimeMillis());
    }
}
