package cn.iflyapi.ihungry.service;

import cn.iflyapi.ihungry.model.ApplyRecord;
import cn.iflyapi.ihungry.model.User;
import cn.iflyapi.ihungry.util.JSONResult;
import org.apache.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: qfwang
 * @date: 2018-11-05 10:59 AM
 */
public interface ApplyService {

    int countTodayApply();

    List<User> getTodayApplyUsers();

    List<ApplyRecord> getRecordApplyUsers();

    JSONResult apply(HttpServletRequest request);
}
