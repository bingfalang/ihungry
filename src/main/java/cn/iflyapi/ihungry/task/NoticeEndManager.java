package cn.iflyapi.ihungry.task;

import cn.iflyapi.ihungry.util.DateUtils;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Timer;

import static cn.iflyapi.ihungry.util.Constant.PERIOD_DAY;

/**
 * @author: qfwang
 * @date: 2018-11-04 9:25 PM
 */
public class NoticeEndManager {

    public NoticeEndManager() {

        long dt = DateUtils.getTodaySomeTime(17, 30);
        Date date = new Date(dt);
        if (date.before(new Date())) {
            long tt = LocalDate.now().plusDays(1).atTime(17, 30).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
            date = new Date(tt);
        }
        Timer timer = new Timer();
        NoticeEndTask task = new NoticeEndTask();
        timer.schedule(task, date, PERIOD_DAY);

    }

}
