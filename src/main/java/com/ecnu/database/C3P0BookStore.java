package com.ecnu.database;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by yerunjie on 2019-02-10
 *
 * @author yerunjie
 */
public class C3P0BookStore extends DataBaseBookStore {

    private static ComboPooledDataSource dataSource = new ComboPooledDataSource(); // 空参数表示默认配置

    @Override
    Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


}
