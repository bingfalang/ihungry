package cn.iflyapi.ihungry.controller;

import cn.iflyapi.ihungry.model.User;
import cn.iflyapi.ihungry.util.DBUtils;
import cn.iflyapi.ihungry.util.JSONResult;
import com.alibaba.fastjson.JSON;

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
public class RegisterServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("=============Servlet 初始化============");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path="/WEB-INF/views/init.html";
        request.getRequestDispatcher(path).forward(request, response);
    }
}
