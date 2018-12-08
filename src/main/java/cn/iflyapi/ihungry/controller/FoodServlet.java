package cn.iflyapi.ihungry.controller;

import cn.iflyapi.ihungry.model.Food;
import cn.iflyapi.ihungry.service.FoodService;
import cn.iflyapi.ihungry.service.FoodServiceImpl;
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
 * @date: 2018-12-07 14:10 PM
 */
public class FoodServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应内容类型
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        FoodService foodService = new FoodServiceImpl();
        PrintWriter out = resp.getWriter();
        out.println(JSON.toJSONString(JSONResult.success(foodService.listFood())));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        Food food = new Food();
        food.setName(req.getParameter("name"));
        food.setCategory(req.getParameter("category"));
        FoodService foodService = new FoodServiceImpl();
        out.println(JSONObject.toJSONString(foodService.addFood(food)));

    }
}
