package edu.mum.cs.cs525.labs.exercises.project.ui.framework.command.Invoker;

import edu.mum.cs.cs525.labs.exercises.project.ui.framework.command.Command;

public class TransactionProcessor {

    public void processTransaction(Command command) {
        command.execute();
    }
}
