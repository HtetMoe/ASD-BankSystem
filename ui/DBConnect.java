package edu.mum.cs.cs525.labs.exercises.project.ui;

import java.sql.*;

public class DBConnect {

    /*
        - localhost =>  IP address 127.0.0.1
        - default port = 3306
        - jdbc // driver dependency
     */

    // Database URL, username, and password
    private static final String URL = "jdbc:mysql://localhost:3306/bank_system"; //DB URL
    private static final String USER = "root"; //DB username
    private static final String PASSWORD = "root"; //DB password

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException{
        try {
            if (connection == null) {
                //establish the connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connected to the database successfully!");
            }
        }
        catch (SQLException e) {
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
            throw e; // rethrowing the SQLException
        }
        return connection;
    }

    public static void getData() {
        String sqlSelectAllPersons = "SELECT * FROM users";
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sqlSelectAllPersons);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//                long id = rs.getLong("ID");
//                String name = rs.getString("FIRST_NAME");
//                String lastName = rs.getString("LAST_NAME");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    } // how to acc table in max
}
