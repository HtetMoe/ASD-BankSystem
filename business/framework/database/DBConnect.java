package edu.mum.cs.cs525.labs.exercises.project.business.framework.database;

import java.sql.*;

public class DBConnect {
    /*
        - localhost    =>  IP address 127.0.0.1
        - default port => 3306
        - jdbc // driver dependency
     */

    // Database URL, username, and password
    private static final String URL      = "jdbc:mysql://localhost:3306/bank_system"; //DB URL
    private static final String USER     = "root"; //DB username
    private static final String PASSWORD = "root"; //DB password

    private static Connection connection = null;

    //connect DB
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

    /* CRUD OPERATIONS */

    //Insert(create)
    public static void createAccount(String accountnr, String clientName, String street, String city, String zip, String state, String clientType, double amountDeposit) {
        String sqlInsert = "INSERT INTO accounts (accountnr, clientName, street, city, zip, state, clientType, amountDeposit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, accountnr);
            ps.setString(2, clientName);
            ps.setString(3, street);
            ps.setString(4, city);
            ps.setString(5, zip);
            ps.setString(6, state);
            ps.setString(7, clientType);
            ps.setDouble(8, amountDeposit);
            ps.executeUpdate();
            System.out.println("Account created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //read all accounts
    public static ResultSet readAllAccounts() {
        String sqlSelectAll = "SELECT * FROM accounts";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlSelectAll);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String no = rs.getString("accountnr");
                String name = rs.getString("clientName");
                double amount = rs.getDouble("amountDeposit");
                System.out.println(STR."accNum: \{no} name: \{name} amount: \{amount}");
            }

            return rs;// Return result set
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //update account
    public static void updateAccount( double amountDeposit) {
        String sqlUpdate = "UPDATE accounts SET clientName = ?, street = ?, city = ?, zip = ?, state = ?, clientType = ?, amountDeposit = ? WHERE accountnr = ?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlUpdate);
            ps.setDouble(7, amountDeposit);
            ps.executeUpdate();
            System.out.println("Account updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //delete account
    public static void deleteAccount(String accountnr) {
        String sqlDelete = "DELETE FROM accounts WHERE accountnr = ?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlDelete);
            ps.setString(1, accountnr);
            ps.executeUpdate();
            System.out.println("Account deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void getData() {
        String sqlSelectAllPersons = "SELECT * FROM users";
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sqlSelectAllPersons);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("username");
                System.out.println(STR."id: \{id} name: \{name}");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
