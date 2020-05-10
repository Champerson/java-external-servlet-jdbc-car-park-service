package com.car.park.service.dao.support;

import com.car.park.service.dao.exceptions.UncheckedSQLException;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DbcpDataSource {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/car_park_service_db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setInitialSize(10);
        dataSource.setMaxActive(10);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(100);
    }

    static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new UncheckedSQLException(e);
        }
    }
}
