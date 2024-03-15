package com.alllioooooo.bankingsystem.transactions;

import com.alllioooooo.bankingsystem.accounts.Accountable;
import com.alllioooooo.bankingsystem.exceptions.InsufficientFundsException;
import com.alllioooooo.bankingsystem.exceptions.InvalidOperationException;
import com.alllioooooo.bankingsystem.exceptions.UnauthorizedWithdrawalException;

public class WithdrawCommand implements Command {
    private final Accountable account;
    private final double amount;

    public WithdrawCommand(Accountable account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public boolean execute() {
        try {
            return account.withdraw(amount);
        } catch (InsufficientFundsException | InvalidOperationException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
            return false;
        } catch (UnauthorizedWithdrawalException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean undo() {
        try {
            account.deposit(amount);
            return true;
        } catch (InvalidOperationException e) {
            System.out.println("Undo withdrawal failed: " + e.getMessage());
            return false;
        }
    }
}
