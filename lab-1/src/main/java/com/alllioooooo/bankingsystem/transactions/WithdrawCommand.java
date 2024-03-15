package com.alllioooooo.bankingsystem.transactions;

import com.alllioooooo.bankingsystem.accounts.Accountable;

public class WithdrawCommand implements Command {
    private final Accountable account;
    private final double amount;

    public WithdrawCommand(Accountable account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public boolean execute() {
        return account.withdraw(amount);
    }

    @Override
    public boolean undo() {
        account.deposit(amount);
        return true;
    }
}
