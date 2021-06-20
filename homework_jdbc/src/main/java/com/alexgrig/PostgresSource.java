package com.alexgrig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresSource implements Source {

    private static final String URL = "jdbc:postgresql://localhost:5432/results_fibonachi";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin";
    private Connection connection;

    public PostgresSource() { }

    @Override
    public Connection connect() throws SQLException {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            throw new SQLException("Не удалось подключиться");
        }
        return connection;
    }

    @Override
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
