package cn.iflyapi.ihungry.service;

import cn.iflyapi.ihungry.model.User;
import cn.iflyapi.ihungry.util.DBUtils;
import cn.iflyapi.ihungry.util.JSONResult;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: qfwang
 * @date: 2018-11-05 11:19 AM
 */
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getAllUsers() {
        List<User> nameList = new ArrayList<>();
        try {
            String sql = "select id,name from user where is_deleted = 0 order by apply_count desc";
            Connection connection = DBUtils.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                User user = new User();
                user.setName(set.getString("name"));
                user.setId(set.getInt("id"));
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
    public JSONResult register(HttpServletRequest request) {
        int flag = 0;
        try {
            Connection connection = DBUtils.connect();
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            String countSql = "select * from user where name = '" + name + "'";
            PreparedStatement count = connection.prepareStatement(countSql);
            ResultSet resultSet = count.executeQuery();
            if (resultSet.next()) {
                return JSONResult.fail("已经注册过了哦");
            }

            String sql = "insert into user (name,email,phone,created_at) values (?,?,?,now())";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            flag = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (flag == 1) {
            return JSONResult.success();
        }

        return JSONResult.fail("注册失败了哦");
    }
}
