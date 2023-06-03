package com.tadkp2023.annas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Koneksidatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/database_tadkp2023";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet executeQuery(String query) {
        Connection connection = getConnection();
        if (connection == null) {
            JOptionPane.showMessageDialog(null, "Error! Program tidak terhubung ke database");
            return null;
        }

        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void executeSql(String sql) {
        Connection connection = getConnection();
        if (connection == null) {
            JOptionPane.showMessageDialog(null, "Error! Program tidak terhubung ke database");
            return;
        }

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}