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

    // Get a singleton database connection
    public static Connection getConnection() throws SQLException{
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD); //establish the connection
            System.out.println("Connected to the database successfully!");
        }
        return connection;
    }

    /* CRUD OPERATIONS */

    //Insert(create)
    public static void createAccount(String accountnr, String accname, String street, String city, String zip, String state, String accountType, String accountStatus, double amountDeposit) {
        String sqlInsert = "INSERT INTO accounts (accountnr, accname, street, city, zip, state, acctype, accstatus, amountDeposit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, accountnr);
            ps.setString(2, accname);

            ps.setString(3, street);
            ps.setString(4, city);
            ps.setString(5, zip);
            ps.setString(6, state);

            ps.setString(7, accountType);
            ps.setString(8, accountStatus);

            ps.setDouble(9, amountDeposit);
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
            return ps.executeQuery(); // Execute query and return ResultSet
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //update account
    public static void updateAccountBalance(String accountnr, double newAmount) {
        String sqlUpdate = "UPDATE accounts SET amountDeposit = ? WHERE accountnr = ?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlUpdate);
            ps.setDouble(1, newAmount);  // Set new balance
            ps.setString(2, accountnr);  // Set account number
            int rowsUpdated = ps.executeUpdate();
            System.out.println(STR."Account balance updated for \{accountnr}. Rows affected: \{rowsUpdated}");
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
