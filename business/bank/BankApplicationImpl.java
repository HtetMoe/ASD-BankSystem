package edu.mum.cs.cs525.labs.exercises.project.business.bank;

import edu.mum.cs.cs525.labs.exercises.project.business.bank.factory.BankAccountFactory;
import edu.mum.cs.cs525.labs.exercises.project.business.bank.interest.CheckingInterest;
import edu.mum.cs.cs525.labs.exercises.project.business.bank.interest.SavingInterest;
import edu.mum.cs.cs525.labs.exercises.project.business.framework.*;
import edu.mum.cs.cs525.labs.exercises.project.business.framework.DAO.DBConnect;
import edu.mum.cs.cs525.labs.exercises.project.business.framework.command.Command;
import edu.mum.cs.cs525.labs.exercises.project.business.framework.command.concreteCommand.DepositCommand;
import edu.mum.cs.cs525.labs.exercises.project.business.framework.command.concreteCommand.WithdrawCommand;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankApplicationImpl implements ApplicationFacade<BankAccountType>, Subject {
    List<Account> accounts = new ArrayList<>();
    List<Observer> observers = new ArrayList<>();

    @Override
    public Account createAccount(BankAccountType accountType, double balance, String accountNumber, String email) {
        BankAccountFactory bankAccountFactory = new BankAccountFactory();
        Account newAccount = bankAccountFactory.createAccount(accountType, balance, accountNumber, email);

        this.accounts.add(newAccount);
        return newAccount;
    }

    @Override
    public void deposit(Account account, double amount) {
        Command depositCommand = new DepositCommand(account, amount);
        account.executeTransaction(depositCommand);
        notifyObservers(account);
    }

    @Override
    public void withdraw(Account account, double amount) {
        Command withdrawCommand = new WithdrawCommand(account, amount);
        account.executeTransaction(withdrawCommand);
        notifyObservers(account);
    }

    @Override
    public void applyInterestToAllAccount() {
        try {
            ResultSet rs = DBConnect.getAllAccounts();
            while (rs.next()) {
                Double balance = Double.valueOf(rs.getString(10));

                BankAccountType accountType = BankAccountType.valueOf(rs.getString("accstatus"));
                switch (accountType) {
                    case SAVINGS -> {
                        balance += new SavingInterest().calculateInterest(balance);
                    }
                    case CHECKING -> {
                        balance += new CheckingInterest().calculateInterest(balance);
                    }
                }
                //update into DB
                DBConnect.updateAccountBalance(rs.getString("accountnr"), balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void undoLastTransaction(Account account) {
        account.undoLastTransaction();
    }

    @Override
    public List<Account> getAccounts() {
        return this.accounts;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Account account) {
        for (Observer o : observers) {
            o.update(account);
        }
    }
}
