package org.example.mvc.repository;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String dbName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/mvc";
    private static final String username = "root";
    private static final String password = "1111";
    private static final Integer maxPoolSize = 40;
    private static DataSource ds;

    private static DataSource getDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setDriverClassName(dbName);
        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setUsername(username);
        hikariDataSource.setPassword(password);
        hikariDataSource.setMaximumPoolSize(maxPoolSize);

        return hikariDataSource;
    }

    public static Connection getConnection() throws SQLException {
        if (ds == null) ds = getDataSource();
        return ds.getConnection();
    }
}
