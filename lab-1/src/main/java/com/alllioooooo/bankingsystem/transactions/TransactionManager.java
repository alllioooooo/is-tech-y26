package com.alllioooooo.bankingsystem.transactions;

public class TransactionManager {
    private final TransactionHistory transactionHistory = new TransactionHistory();

    public void executeTransaction(Command transaction) {
        if (transaction.execute()) {
            transactionHistory.addTransaction(transaction);
        }
    }
}
