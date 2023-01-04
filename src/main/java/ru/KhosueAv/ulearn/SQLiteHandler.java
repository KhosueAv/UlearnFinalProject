package ru.KhosueAv.ulearn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteHandler {

    private Connection conn;
    private Statement stmt;

    public SQLiteHandler() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:earthquakes.db");
        stmt = conn.createStatement();
        addTable();
    }

    public void addTable() throws SQLException {
        var createTableSqlQuery = """
            CREATE TABLE IF NOT EXISTS earthquakes (
             id text not null PRIMARY KEY,
             depth integer not null,
             type_of_magnitude text not null,
             magnitude real not null,
             state text not null,
             registration_date text not null
            );""";

        stmt.execute(createTableSqlQuery);
    }

    public void add(Earthquake earthquake) throws SQLException {
        var addSqlQuery = """
            INSERT OR IGNORE INTO earthquakes(
            id, depth, type_of_magnitude, magnitude, state, registration_date
            ) VALUES (?,?,?,?,?,?)
            """;
        PreparedStatement preparedStatement = conn.prepareStatement(addSqlQuery);
        preparedStatement.setString(1, earthquake.getId());
        preparedStatement.setInt(2, earthquake.getDepth());
        preparedStatement.setString(3, earthquake.getTypeOfMagnitude());
        preparedStatement.setDouble(4, earthquake.getMagnitude());
        preparedStatement.setString(5, earthquake.getState());
        preparedStatement.setString(6, earthquake.getDate().toString());
        preparedStatement.executeUpdate();
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return stmt.executeQuery(query);
    }
}