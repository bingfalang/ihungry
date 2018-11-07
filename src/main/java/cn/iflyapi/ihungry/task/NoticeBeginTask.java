package cn.iflyapi.ihungry.task;

/**
 * @author: qfwang
 * @date: 2018-11-04 9:27 PM
 */

import cn.iflyapi.ihungry.HttpClient;
import cn.iflyapi.ihungry.util.Constant;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.entity.ContentType;

import java.util.TimerTask;
import java.util.logging.Logger;


public class NoticeBeginTask extends TimerTask {

    private static final Logger logger = Logger.getLogger("NoticeBeginTask");

    @Override
    public void run() {
        try {
            JSONObject jsonParam = new JSONObject();
            JSONObject content = new JSONObject();
            content.put("content", "诸位,订餐时间到了，赶快进入下面地址报名吧！http://*:9110/ihungry");
            jsonParam.put("msgtype", "text");
            jsonParam.put("text", content);

            HttpClient.postJSON(Constant.URL, jsonParam, ContentType.APPLICATION_JSON.getMimeType());
        } catch (Exception e) {
            logger.warning("NoticeBeginTask|解析信息发生异常");
        }
    }

}
