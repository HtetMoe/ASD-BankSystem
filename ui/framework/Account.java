package edu.mum.cs.cs525.labs.exercises.project.ui.framework;

//temp, replace it later
public abstract class Account {
    protected String accountNumber;
    protected double balance;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    //abstract methods
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);
    public abstract double calculateInterest();

    //getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
}
