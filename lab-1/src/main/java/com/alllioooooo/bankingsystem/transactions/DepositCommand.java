package com.alllioooooo.bankingsystem.transactions;

import com.alllioooooo.bankingsystem.accounts.Accountable;

public class DepositCommand implements Command {
    private final Accountable account;
    private final double amount;

    public DepositCommand(Accountable account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public boolean execute() {
        account.deposit(amount);
        return true;
    }

    @Override
    public boolean undo() {
        return account.withdraw(amount);
    }
}
