package cn.iflyapi.ihungry.task;

import cn.iflyapi.ihungry.HttpClient;
import cn.iflyapi.ihungry.util.DateUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Timer;
import java.util.logging.Logger;

import static cn.iflyapi.ihungry.util.Constant.*;

/**
 * @author: qfwang
 * @date: 2018-11-04 9:25 PM
 */
public class NoticeBeginManager {

    private static final Logger logger = Logger.getLogger("NoticeBeginManager");

    public NoticeBeginManager() {

        long dt = DateUtils.getTodaySomeTime(17, 0);
        Date date = new Date(dt);
        if (date.before(new Date())) {
            long tt = LocalDate.now().plusDays(1).atTime(17, 0).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
            date = new Date(tt);
        }

        Timer timer = new Timer();
        NoticeBeginTask task = new NoticeBeginTask();
        timer.schedule(task, date, PERIOD_DAY);

    }

}
