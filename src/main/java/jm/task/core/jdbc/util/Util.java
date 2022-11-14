package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/kata_schema";
    private static final String DB_USRNAME = "root";
    private static final String DB_PASSWORD = "root";

    public Connection getConnection() {
        Connection connection = null;
        try {
            //Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USRNAME, DB_PASSWORD);
            connection.setAutoCommit(false);
            System.out.println("Connection OK");
        } catch (SQLException /*| ClassNotFoundException*/ e) {
            System.out.println("Connection FAIL");
        }

        return connection;
    }

    public void CloseQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
            //
        }
    }

    public void CloseQuietly(Statement stmt) {
        try {
            stmt.close();
        } catch (Exception e) {
            //
        }
    }

    public void CloseQuietly(ResultSet rs) {
        try {
            rs.close();
        } catch (Exception e) {
            //
        }
    }
}
