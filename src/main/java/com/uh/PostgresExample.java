package com.uh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresExample {

    public static void main(String[] args) throws SQLException {
        System.out.println("-------- PostgreSQL " + "JDBC Connection Testing ------------");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
            e.printStackTrace();
            return;
        }
        System.out.println("PostgreSQL JDBC Driver Registered!");
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "select * from session.test_part_session where test_part_session_id = '125853' ";
        try {

            //hostname, username and password
            connection = DriverManager.getConnection(args[0], args[1], args[2]);
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String str = rs.getString("login_json");
                String id = rs.getString("test_part_session_id");
                String newStr = null;
                if (!str.contains("\"")) {
                    newStr = str.replace("{", "{\"").replace(":", "\":\"").replace(",", "\",\"").replace("}", "\"}");
                    String update = "update session.test_part_session set login_json = '" + newStr
                            + "' where test_part_session_id =" + id;
                    ps = connection.prepareStatement(update);
                    ps.executeUpdate();
                }

            }

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        } finally {
            if (ps != null && connection != null) {
                ps.close();
                connection.close();
            }
        }
    }

}
