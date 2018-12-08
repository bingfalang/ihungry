package cn.iflyapi.ihungry.service;

import cn.iflyapi.ihungry.model.Food;
import cn.iflyapi.ihungry.model.User;
import cn.iflyapi.ihungry.util.DBUtils;
import cn.iflyapi.ihungry.util.JSONResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: qfwang
 * @date: 2018-12-08 3:02 PM
 */
public class FoodServiceImpl implements FoodService {
    @Override
    public JSONResult addFood(Food food) {
        if (food.getName() == null || food.getName().length() > 6) {
            return JSONResult.fail("菜名不能为空或超过6个字符");
        }
        if (food.getCategory() == null || food.getCategory().length() > 4) {
            return JSONResult.fail("类别不能为空或超过4个字符");
        }
        int flag = 0;
        try {
            Connection connection = DBUtils.connect();

            String countSql = "select * from food where name = '" + food.getName() + "' and category = '" + food.getCategory() + "'";
            PreparedStatement count = connection.prepareStatement(countSql);
            ResultSet resultSet = count.executeQuery();
            if (resultSet.next()) {
                return JSONResult.fail("已经存在了");
            }

            String sql = "insert into food (name,category,created_at) values (?,?,now())";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, food.getName());
            preparedStatement.setString(2, food.getCategory());
            flag = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (flag == 1) {
            return JSONResult.success();
        }

        return JSONResult.fail("添加失败了哦");
    }

    @Override
    public List<Food> listFood() {
        List<Food> foodList = new ArrayList<>();
        try {
            String sql = "select id,name,category from food where is_deleted = 0";
            Connection connection = DBUtils.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                Food food = new Food();
                food.setName(set.getString("name"));
                food.setId(set.getInt("id"));
                food.setCategory(set.getString("category"));
                foodList.add(food);
            }
            DBUtils.close(connection);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodList;
    }
}
