package edu.mum.cs.cs525.labs.exercises.project.business.framework.command.Invoker;

import edu.mum.cs.cs525.labs.exercises.project.business.framework.command.Command;

import java.util.Stack;

public class TransactionProcessor {
    private Stack<Command> commandHistory = new Stack<>();

    // Execute a command and store it in the history
    public void processTransaction(Command command) {
        command.execute();
        commandHistory.push(command);
    }

    // Undo the last command
    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            lastCommand.undo();
        } else {
            System.out.println("No commands to undo.");
        }
    }
}
