package com.mathwhiz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Added parameters to avoid timezone and SSL handshake errors
    private static final String URL = "jdbc:mysql://localhost:3306/math_whiz_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";

    // Replace with your actual MySQL password
    private static final String PASSWORD = "12345678";

    public static Connection getConnection() throws SQLException {
        try {
            // Explicitly load the MySQL driver class for Tomcat
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found in classpath!");
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}