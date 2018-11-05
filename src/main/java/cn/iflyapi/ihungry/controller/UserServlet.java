package cn.iflyapi.ihungry.controller;

import cn.iflyapi.ihungry.model.User;
import cn.iflyapi.ihungry.service.UserService;
import cn.iflyapi.ihungry.service.UserServiceImpl;
import cn.iflyapi.ihungry.util.DBUtils;
import cn.iflyapi.ihungry.util.JSONResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: qfwang
 * @date: 2018-11-01 11:20 AM
 */
public class UserServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应内容类型
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        UserService userService = new UserServiceImpl();
        PrintWriter out = resp.getWriter();
        out.println(JSON.toJSONString(JSONResult.success(userService.getAllUsers())));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        UserService userService = new UserServiceImpl();
        out.println(JSONObject.toJSONString(userService.register(req)));

    }
}
