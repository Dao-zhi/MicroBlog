package org.microblog.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDatabase {
    public static String DRIVER = "com.mysql.jdbc.Driver";
    public static String URL = "jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
    public static String USER = "root";
    public static String PASSWORD = "431241wjw";
    public static Connection conn;
    public ConnectionDatabase(){
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        return conn;
    }
}