package edu.mum.cs.cs525.labs.exercises.project.business.framework.command;

//transaction command interface
public interface Command {
    void execute();
    void undo();

    /*
         deposit, withdraw, and
         others like: transfer funds, terminate/ close the account
     */
}
