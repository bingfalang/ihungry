package cn.iflyapi.ihungry.util;

import java.sql.*;
import java.util.Objects;

/**
 * @author: qfwang
 * @date: 2018-11-01 12:44 PM
 */
public class DBUtils {


    private static String dbname = "ihungry";

    private static String username = "root";

    private static String password = "123456";

    private static String url = "jdbc:mysql://127.0.0.1:3306/" + dbname + "?user=" + username + "&password=" + password + "&useSSL=false";

    public static Connection connect(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

       return connection;
    }

    public static void close(Connection connection){
        if (null != connection) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static PreparedStatement statement(String sql){
        Connection connection = connect();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    public static ResultSet execute(String sql, String... params){
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = statement(sql);
        try {
            for (int i = 1; i <= params.length; i++){
                preparedStatement.setString(i,params[i-1]);
            }
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void insert(String sql){
        PreparedStatement preparedStatement = statement(sql);
        try {
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
