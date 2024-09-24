package edu.mum.cs.cs525.labs.exercises.project.ui.framework.command.concreteCommands;

import edu.mum.cs.cs525.labs.exercises.project.ui.framework.Account;
import edu.mum.cs.cs525.labs.exercises.project.ui.framework.command.Command;
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
}
