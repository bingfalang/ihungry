package cn.iflyapi.ihungry.util;

import java.sql.*;
import java.util.Objects;

/**
 * @author: qfwang
 * @date: 2018-11-01 12:44 PM
 */
public class DBUtils {

    /**
     * 1、加载数据库驱动程序：Class.forName(驱动程序类)
     * 2、通过用户名密码和连接地址获取数据库连接对象：DriverManager.getConnection(连接地址,用户名,密码)
     * 3、构造查询SQL语句
     * 4、创建Statement实例：Statement stmt = conn.createStatement()
     * 5、执行查询SQL语句，并返回结果：ResultSet rs = stmt.executeQuery(sql)
     * 6、处理结果
     * 7、关闭连接：rs.close()、stmt.close()、conn.close()
     */

    private static String dbname = "ihungry";

    private static String username = "root";

    private static String password = "123456";

    private static String url = "jdbc:mysql://localhost:3306/" + dbname + "?user=" + username + "&password=" + password + "&useSSL=false&useUnicode=true&characterEncoding=utf8";

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
