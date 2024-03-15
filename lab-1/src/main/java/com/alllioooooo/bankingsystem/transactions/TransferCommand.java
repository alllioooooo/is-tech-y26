package com.alllioooooo.bankingsystem.transactions;

import com.alllioooooo.bankingsystem.accounts.Accountable;

public class TransferCommand implements Command {
    private final Accountable fromAccount;
    private final Accountable toAccount;
    private final double amount;

    public TransferCommand(Accountable fromAccount, Accountable toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public boolean execute() {
        if (fromAccount.withdraw(amount)) {
            toAccount.deposit(amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean undo() {
        if (toAccount.withdraw(amount)) {
            fromAccount.deposit(amount);
            return true;
        }
        return false;
    }
}
