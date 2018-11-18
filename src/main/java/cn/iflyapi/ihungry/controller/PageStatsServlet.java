package cn.iflyapi.ihungry.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: qfwang
 * @date: 2018-11-01 11:20 AM
 */
public class PageStatsServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("=============Servlet 初始化============");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path="/WEB-INF/views/stats.html";
        request.getRequestDispatcher(path).forward(request, response);
    }
}
