package edu.mum.cs.cs525.labs.exercises.project.business.framework.DAO;

import edu.mum.cs.cs525.labs.exercises.project.business.bank.BankAccountType;
import edu.mum.cs.cs525.labs.exercises.project.business.bank.account.CheckingAccount;
import edu.mum.cs.cs525.labs.exercises.project.business.bank.account.SavingsAccount;
import edu.mum.cs.cs525.labs.exercises.project.business.bank.interest.CheckingInterest;
import edu.mum.cs.cs525.labs.exercises.project.business.bank.interest.SavingInterest;
import edu.mum.cs.cs525.labs.exercises.project.business.framework.Account;
import edu.mum.cs.cs525.labs.exercises.project.business.framework.InterestStrategy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    /* CRUD OPERATIONS - Bank Account */

    //1.Insert(create)
    public static void createAccount(String accountnr, String accname, String street, String city, String zip, String state, String accountType, String accountStatus, double amountDeposit, String email) {
        String sqlInsert = "INSERT INTO accounts (accountnr, accname, street, city, zip, state, acctype, accstatus, amountDeposit, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            ps.setString(10, email);
            ps.executeUpdate();
            System.out.println("Account created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //read all accounts
    public static ResultSet getAllAccounts() throws SQLException {
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

//    public static List<Account> getAccounts() throws SQLException {
//        List<Account> accounts = new ArrayList<>();
//        ResultSet rs = getAllAccounts();
//        while (rs.next()) {
//            String accountNumber = rs.getString("accountnr");
//            double balance = rs.getDouble("amountDeposit");
//            String email = rs.getString("email");  // Assuming you store email
//
//            // Assuming you have account types in your database, e.g., SAVINGS, CHECKING
//            BankAccountType accountType = BankAccountType.valueOf(rs.getString("accountType"));
//            switch (accountType){
//                case SAVINGS ->
//                        accounts.add(new SavingsAccount(accountNumber, balance, new SavingInterest(), email));
//                case CHECKING ->
//                        accounts.add(new CheckingAccount(accountNumber, balance, new CheckingInterest(), email));
//            }
//        }
//        return accounts;
//    }

    /* CRUD OPERATIONS - Credit Account */

    //create cc account
    public static void insertCCAccount(String accName, String ccNumber, String city, String state, String zip,
                                               String email, String expireDate, String accType, Double amountDeposit) {
        String sqlInsert = "INSERT INTO credit_card_accounts (accname, ccnumber, city, state, zip, email, expireddate, acctype, amountdeposit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlInsert)) {

            ps.setString(1, accName);
            ps.setString(2, ccNumber);
            ps.setString(3, city);
            ps.setString(4, state);
            ps.setString(5, zip);
            ps.setString(6, email);
            ps.setString(7, expireDate);
            ps.setString(8, accType);
            ps.setDouble(9, amountDeposit);

            int rowsInserted = ps.executeUpdate();
            System.out.println(STR."Credit card account created: \{accName}. Rows affected: \{rowsInserted}");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //read all cc accounts
    public static ResultSet getAllCCAccounts() throws SQLException {
        String sqlSelectAll = "SELECT * FROM credit_card_accounts";  // Replace with your table name
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlSelectAll);
            return ps.executeQuery(); // Execute query and return ResultSet
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  // Re-throwing the exception to be handled by the caller
        }
    }

    //update amount cc account
    public static void updateCreditCardBalance(String ccNumber, double newAmount) {
        String sqlUpdate = "UPDATE credit_card_accounts SET amountdeposit = ? WHERE ccnumber = ?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlUpdate);
            ps.setDouble(1, newAmount);  // Set the new balance
            ps.setString(2, ccNumber);  // Set the credit card number

            int rowsUpdated = ps.executeUpdate();
            System.out.println(STR."Credit card balance updated for \{ccNumber}. Rows affected: \{rowsUpdated}");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
