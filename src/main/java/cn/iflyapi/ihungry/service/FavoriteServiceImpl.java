package cn.iflyapi.ihungry.service;

import cn.iflyapi.ihungry.model.FavoriteCount;
import cn.iflyapi.ihungry.util.DBUtils;
import cn.iflyapi.ihungry.util.DateUtils;
import cn.iflyapi.ihungry.util.JSONResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: qfwang
 * @date: 2018-12-08 3:39 PM
 */
public class FavoriteServiceImpl implements FavoriteService {
    @Override
    public JSONResult addFavorite(String[] foodIds, String userIP) {
        long start = DateUtils.getTodaySomeTime(17, 0);
        long end = DateUtils.getTodaySomeTime(17, 30);
        long now = System.currentTimeMillis();
        if (now < start || now > end) {
            return JSONResult.fail("不在订餐范围内！");
        }
        Connection connection = DBUtils.connect();
        int flag = 0;
        try {
            String querySql = "select * from favorite where user_ip = '" + userIP + "' and to_days(created_at) = to_days(now())";
            PreparedStatement query = connection.prepareStatement(querySql);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                return JSONResult.fail("你已经选择过了哦");
            }
            StringBuilder stringBuilder = new StringBuilder("insert into favorite (food_id,user_ip,created_at) values");
            for (String id : foodIds) {
                stringBuilder.append("(" + id + ",'" + userIP + "',now()),");
            }
            String sql = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            flag = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (flag == 1) {
            return JSONResult.success("提交成功了哦");
        }

        return JSONResult.fail("提交失败了哦");
    }

    @Override
    public List<FavoriteCount> countTodayFavorite() {
        String sql = "select b.name,b.category,count(*) total\n" +
                "from favorite a\n" +
                "left join food b on a.food_id=b.id\n" +
                "where to_days(a.created_at) = to_days(now())\n" +
                "group by b.name,b.category\n" +
                "order by total desc\n";
        List<FavoriteCount> favoriteCountList = new ArrayList<>();

        try {
            Connection connection = DBUtils.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                FavoriteCount favoriteCount = new FavoriteCount();
                favoriteCount.setName(set.getString("name"));
                favoriteCount.setCategory(set.getString("category"));
                favoriteCount.setTotal(set.getInt("total"));
                favoriteCountList.add(favoriteCount);
            }
            DBUtils.close(connection);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteCountList;
    }
}
