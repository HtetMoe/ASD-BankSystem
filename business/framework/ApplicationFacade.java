package edu.mum.cs.cs525.labs.exercises.project.business.framework;

import java.util.List;

public interface ApplicationFacade<T> {
    Account createAccount(T accountType, double balance, String accountNumber);

    void deposit(Account account, double amount);
    void withdraw(Account account, double amount);
    void undoLastTransaction(Account account);

    void applyInterestToAllAccount();
    List<Account> getAccounts();
}
