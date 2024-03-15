package com.alllioooooo.bankingsystem.transactions;

public interface Command {
    boolean execute();
    boolean undo();
}
