package cn.iflyapi.ihungry.task;

/**
 * @author: qfwang
 * @date: 2018-11-04 9:27 PM
 */

import cn.iflyapi.ihungry.HttpClient;
import cn.iflyapi.ihungry.service.ApplyService;
import cn.iflyapi.ihungry.service.ApplyServiceImpl;
import cn.iflyapi.ihungry.util.Constant;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.entity.ContentType;

import java.util.TimerTask;
import java.util.logging.Logger;

public class NoticeEndTask extends TimerTask {

    private static final Logger logger = Logger.getLogger("NoticeEndTask");

    @Override
    public void run() {
        try {
            ApplyService applyService = new ApplyServiceImpl();
            JSONObject jsonParam = new JSONObject();
            JSONObject content = new JSONObject();
            content.put("content", "诸位,订餐报名结束，共" + applyService.countTodayApply() + "人。");
            jsonParam.put("msgtype", "text");
            jsonParam.put("text", content);

            HttpClient.postJSON(Constant.URL, jsonParam, ContentType.APPLICATION_JSON.getMimeType());
        } catch (Exception e) {
            logger.warning("NoticeBeginTask|解析信息发生异常");
        }
    }

}
