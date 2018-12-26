package cn.iflyapi.ihungry.task;

/**
 * @author: qfwang
 * @date: 2018-11-04 9:27 PM
 */

import cn.iflyapi.ihungry.HttpClient;
import cn.iflyapi.ihungry.model.FavoriteCount;
import cn.iflyapi.ihungry.model.User;
import cn.iflyapi.ihungry.service.ApplyService;
import cn.iflyapi.ihungry.service.ApplyServiceImpl;
import cn.iflyapi.ihungry.service.FavoriteService;
import cn.iflyapi.ihungry.service.FavoriteServiceImpl;
import cn.iflyapi.ihungry.util.Constant;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TimerTask;
import java.util.logging.Logger;

import static cn.iflyapi.ihungry.util.Constant.API_HOLIDAY_DATE;
import static cn.iflyapi.ihungry.util.Constant.simpleDateFormat;

public class NoticeEndTask extends TimerTask {

    private static final Logger logger = Logger.getLogger("NoticeEndTask");

    @Override
    public void run() {
        try {

            String newDate = simpleDateFormat.format(new Date());
            try {
                if (!HttpClient.isWorkDay(API_HOLIDAY_DATE + newDate)) {
                    return;
                }
            } catch (IOException e) {
                logger.warning("NoticeBeginManager|判断节假日失败");
                return;
            }

            ApplyService applyService = new ApplyServiceImpl();
            JSONObject jsonParam = new JSONObject();
            JSONObject content = new JSONObject();
            List<User> userList = applyService.getTodayApplyUsers();

            FavoriteService favoriteService = new FavoriteServiceImpl();
            List<FavoriteCount> favoriteCountList = favoriteService.countTodayFavorite();
            StringBuilder names = new StringBuilder();
            for (User user : userList) {
                names.append(user.getName() + ",");
            }
            if (names.length() == 0) {
                content.put("content", "@所有人 ，订餐报名结束，今晚无人订餐。");

            } else {
                if (Objects.isNull(favoriteCountList) || favoriteCountList.size() == 0) {
                    content.put("content", "@所有人 ，订餐报名结束，其中有：" + names.toString().substring(0, names.length() - 1) + "，共" + userList.size() + "人。");
                } else {
                    StringBuilder fav = new StringBuilder();
                    for (FavoriteCount count : favoriteCountList) {
                        fav.append(count.getTotal() + "人想吃：" + count.getName() + "\n");
                    }
                    content.put("content", "@所有人 ，订餐报名结束，其中有：" + names.toString().substring(0, names.length() - 1) + "，共" + userList.size() + "人\n" + fav.toString());
                }

            }
            jsonParam.put("msgtype", "text");
            jsonParam.put("text", content);

            HttpClient.postJSON(Constant.URL, jsonParam, ContentType.APPLICATION_JSON.getMimeType());
        } catch (Exception e) {
            logger.warning("NoticeBeginTask|解析信息发生异常");
        }
    }

}
