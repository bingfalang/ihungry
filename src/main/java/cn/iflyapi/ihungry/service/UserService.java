package cn.iflyapi.ihungry.service;

import cn.iflyapi.ihungry.model.User;
import cn.iflyapi.ihungry.util.JSONResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: qfwang
 * @date: 2018-11-05 10:59 AM
 */
public interface UserService {

    List<User> getAllUsers();

    JSONResult register(HttpServletRequest request);
}
