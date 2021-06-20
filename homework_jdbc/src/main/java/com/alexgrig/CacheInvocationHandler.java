package com.alexgrig;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CacheInvocationHandler implements InvocationHandler {
    private final Object delegate;
    private boolean isCreated;

    public CacheInvocationHandler(Calculable delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Cacheable.class)) {
            return method.invoke(delegate, args);
        }

        Class classDB = method.getAnnotation(Cacheable.class).value();
        Source postgres = (Source) classDB.newInstance();
        Connection connection = null;
        try {
            connection = postgres.connect();
        } catch (SQLException e) {
            System.out.println("Не удалось получить соединение с базой данных!");
            return null;
        }
        if(!isCreated) {
            createDb(connection);
            isCreated = true;
        }

        String key = Arrays.toString(args);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM fibo_reults");
        while (resultSet.next()) {
            if (resultSet.getString("key").equals(key)) {
                System.out.println("Результат из базы данных");
                String resultFromDB = resultSet.getString("result");
                statement.close();
                connection.close();
                return stringToList(resultFromDB);
            }
        }
        System.out.println("вычисляется...");
        Object invoke = method.invoke(delegate, args);
        String result = invoke.toString();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO fibo_reults VALUES(?, ?)");
        preparedStatement.setString(1, key);
        preparedStatement.setString(2, result);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return invoke;

    }

    private void createDb(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS fibo_reults (key VARCHAR(128) UNIQUE, result TEXT)");
        } catch (SQLException ex) {
            System.out.println("Error create db!");
            ex.printStackTrace();
        }
    }

    private Object stringToList(String resultFromDB) {
        String str = resultFromDB.replaceAll("\\[|\\]", "");
        String[] arr = str.split(", ");
        List<Integer> list = new ArrayList<>();
        for (String s : arr) {
            list.add(Integer.parseInt(s));
        }
        return list;
    }
}
