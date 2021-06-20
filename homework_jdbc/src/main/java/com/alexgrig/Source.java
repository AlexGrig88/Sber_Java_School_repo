package com.alexgrig;

import java.sql.Connection;
import java.sql.SQLException;

//маркерный интерфейс
public interface Source {
    Connection connect() throws SQLException;
    void disconnect();
}
