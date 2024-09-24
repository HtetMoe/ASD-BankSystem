package edu.mum.cs.cs525.labs.exercises.project.ui.framework.command.concreteCommands;

import edu.mum.cs.cs525.labs.exercises.project.ui.framework.Account;
import edu.mum.cs.cs525.labs.exercises.project.ui.framework.command.Command;

//command for withdraw operation
public class WithdrawCommand implements Command {
    private Account account;
    private double amount;

    public WithdrawCommand(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.withdraw(amount);
        System.out.println(STR."Withdrew \{amount} from account \{account.getAccountNumber()}");
    }
}
