package cn.iflyapi.ihungry.controller;

import cn.iflyapi.ihungry.service.ApplyService;
import cn.iflyapi.ihungry.service.ApplyServiceImpl;
import cn.iflyapi.ihungry.service.UserService;
import cn.iflyapi.ihungry.service.UserServiceImpl;
import cn.iflyapi.ihungry.util.JSONResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: qfwang
 * @date: 2018-11-17 16:54 PM
 */
public class StatsServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应内容类型
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        ApplyService applyService = new ApplyServiceImpl();
        PrintWriter out = resp.getWriter();
        out.println(JSON.toJSONString(JSONResult.success(applyService.getRecordApplyUsers())));
    }
}
