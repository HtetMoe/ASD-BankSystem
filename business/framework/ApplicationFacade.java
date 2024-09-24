package edu.mum.cs.cs525.labs.exercises.project.business.framework;

import java.util.List;

public interface ApplicationFacade<T> {

    public Account createAccount(T accountType,double balance ,String accountNumber);

    public void deposit(Account account,double amount);

    public void withdraw(Account account,double amount);

    public void applyInterestToAllAccount();

    public void undoLastTransaction(Account account);

    public List<Account> getAccounts();


}
