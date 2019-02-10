package com.ecnu.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by yerunjie on 2019-01-20
 *
 * @author yerunjie
 */
public class CommonBookStore extends DataBaseBookStore {

    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASS = "123456";

    private Connection connection;

    public void init() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("连接成功");
        } catch (Exception e) {
            System.out.println("连接失败");
            e.printStackTrace();
        }
    }

    @Override
    Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            init();
        }
        return connection;
    }
}
