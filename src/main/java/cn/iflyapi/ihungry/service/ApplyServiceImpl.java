package cn.iflyapi.ihungry.service;

import cn.iflyapi.ihungry.model.User;
import cn.iflyapi.ihungry.util.DBUtils;
import cn.iflyapi.ihungry.util.DateUtils;
import cn.iflyapi.ihungry.util.JSONResult;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: qfwang
 * @date: 2018-11-05 11:00 AM
 */
public class ApplyServiceImpl implements ApplyService {
    @Override
    public int countTodayApply() {
        try {
            String sql = "select count(*) from apply where to_days(apply_at) = to_days(now())";
            Connection connection = DBUtils.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<User> getApplyUsers() {
        List<User> nameList = new ArrayList<>();
        try {

            String sql = "select u.name \n" +
                    "from apply a\n" +
                    "left join user u on a.user_id = u.id\n" +
                    "where to_days(apply_at) = to_days(now())";
            Connection connection = DBUtils.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                User user = new User();
                String name = set.getString("name");
                user.setName(name);
                nameList.add(user);
            }
            DBUtils.close(connection);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nameList;
    }

    @Override
    public JSONResult apply(HttpServletRequest request) {
        int flag = 0;
        try {
            long start = DateUtils.getTodaySomeTime(17, 0);
            long end = DateUtils.getTodaySomeTime(17, 30);
            long now = System.currentTimeMillis();
            if (now < start || now > end) {
                return JSONResult.fail("不在订餐范围内！");
            }
            Connection connection = DBUtils.connect();
            int userId = Integer.valueOf(request.getParameter("userId"));
            String querySql = "select * from apply where user_id = " + userId + " and to_days(apply_at) = to_days(now())";
            PreparedStatement query = connection.prepareStatement(querySql);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                return JSONResult.fail("你已经订过了哦");
            }
            String sql = "insert into apply (user_id,apply_at) values (?,now())";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            flag = preparedStatement.executeUpdate();

            String countSql = "update user set apply_count = apply_count + 1 where id = " + userId;
            PreparedStatement count = connection.prepareStatement(countSql);
            count.executeUpdate();

            DBUtils.close(connection);
            preparedStatement.close();
            query.close();
            count.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (flag == 1) {
            return JSONResult.success("订餐成功了哦");
        }

        return JSONResult.fail("订餐失败了哦");
    }
}
