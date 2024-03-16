package com.alllioooooo.bankingsystem.transactions;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {
    private final List<Command> transactions = new ArrayList<>();

    public void addTransaction(Command transaction) {
        transactions.add(transaction);
    }

    public boolean undoTransaction(Command transaction) {
        // NOTE: return transactions.contains(transaction) ? transaction.undo() : false;
        if (transactions.contains(transaction)) {
            return transaction.undo();
        }

        return false;
    }
}

