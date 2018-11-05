package cn.iflyapi.ihungry.task;

/**
 * @author: qfwang
 * @date: 2018-11-04 9:27 PM
 */

import cn.iflyapi.ihungry.HttpClient;
import cn.iflyapi.ihungry.util.Constant;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.entity.ContentType;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * 在 NoticeBeginManager 这个类里面，大家一定要注意 时间点的问题。如果你设定在凌晨2点执行任务。但你是在2点以后
 *发布的程序或是重启过服务，那这样的情况下，任务会立即执行，而不是等到第二天的凌晨2点执行。为了，避免这种情况
 *发生，只能判断一下，如果发布或重启服务的时间晚于定时执行任务的时间，就在此基础上加一天。
 * @author wls
 *
 */
public class NoticeBeginTask extends TimerTask {

    private static final Logger logger = Logger.getLogger("NoticeBeginTask");

    @Override
    public void run() {
        try {
            JSONObject jsonParam = new JSONObject();
            JSONObject content = new JSONObject();
            content.put("content", "诸位,订餐时间到了，赶快进入下面地址报名吧！http://test.dfocus.top:92110/ihungry");
            jsonParam.put("msgtype", "text");
            jsonParam.put("text", content);

            HttpClient.postJSON(Constant.URL,jsonParam, ContentType.APPLICATION_JSON.getMimeType());
        } catch (Exception e) {
            logger.warning("NoticeBeginTask|解析信息发生异常");
        }
    }

}
