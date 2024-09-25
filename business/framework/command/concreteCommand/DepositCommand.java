package edu.mum.cs.cs525.labs.exercises.project.business.framework.command.concreteCommand;

import edu.mum.cs.cs525.labs.exercises.project.business.framework.Account;
import edu.mum.cs.cs525.labs.exercises.project.business.framework.command.Command;

import static java.lang.StringTemplate.STR;

// command for deposit operation
public class DepositCommand implements Command {
    private Account account;
    private double amount;

    public DepositCommand(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.deposit(amount);
        System.out.println(STR."Deposited \{amount} to account \{account.getAccountNumber()}");
    }

    @Override
    public void undo() {
        account.withdraw(amount);
        System.out.println(STR."Undid deposit of \{amount} from account \{account.getAccountNumber()}");
    }
}