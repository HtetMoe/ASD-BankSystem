package edu.mum.cs.cs525.labs.exercises.project.ui.framework;

public class SavingAccount extends Account {

    public SavingAccount(String accountNumber) {
        super(accountNumber);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance for withdrawal!");
        }
    }

    @Override
    public double calculateInterest() {
        return 0;
    }
}
