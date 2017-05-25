package com.czw.util;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public final class JdbcUtils {
    private static DataSource dataSource = null;

    private JdbcUtils() {
    }

    static {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties prop = new Properties();
            prop.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
            prop.setProperty("password", "123456");
            prop.setProperty("username", "root");
            prop.setProperty("url", "jdbc:mysql://192.168.1.121:3306/pp_app?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true");

//            InputStream is = JdbcUtils.class.getClassLoader()
//                    .getResourceAsStream("jdbc.properties");
//            prop.load(is);
            dataSource = BasicDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection() {
        // return DriverManager.getConnection(url, user, password);
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void free(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null)
                    try {
                        conn.close();
                        // dataSource.free(conn);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}