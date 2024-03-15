package com.alllioooooo.bankingsystem.transactions;

import com.alllioooooo.bankingsystem.accounts.Accountable;
import com.alllioooooo.bankingsystem.exceptions.InvalidOperationException;

public class DepositCommand implements Command {
    private final Accountable account;
    private final double amount;

    public DepositCommand(Accountable account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public boolean execute() {
        try {
            account.deposit(amount);
            return true;
        } catch (InvalidOperationException e) {
            System.out.println("Deposit failed: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean undo() {
        try {
            return account.withdraw(amount);
        } catch (Exception e) {
            System.out.println("Undo deposit failed: " + e.getMessage());
            return false;
        }
    }
}