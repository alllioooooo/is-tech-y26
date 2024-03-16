package com.alllioooooo.bankingsystem.transactions;

import com.alllioooooo.bankingsystem.accounts.Accountable;
import com.alllioooooo.bankingsystem.exceptions.InsufficientFundsException;
import com.alllioooooo.bankingsystem.exceptions.InvalidOperationException;
import com.alllioooooo.bankingsystem.exceptions.UnauthorizedWithdrawalException;

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
        } catch (UnauthorizedWithdrawalException e) {
            throw new RuntimeException(e);
        } catch (InvalidOperationException e) {
            throw new RuntimeException(e);
        } catch (InsufficientFundsException e) {
            throw new RuntimeException(e);
        }
    }
}