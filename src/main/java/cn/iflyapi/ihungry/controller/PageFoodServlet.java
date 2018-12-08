package cn.iflyapi.ihungry.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: qfwang
 * @date: 2018-12-07 15:13 AM
 */
public class PageFoodServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path="/WEB-INF/views/addfood.html";
        request.getRequestDispatcher(path).forward(request, response);
    }
}
