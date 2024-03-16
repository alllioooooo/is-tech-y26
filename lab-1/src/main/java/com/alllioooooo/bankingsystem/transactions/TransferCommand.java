package com.alllioooooo.bankingsystem.transactions;

import com.alllioooooo.bankingsystem.accounts.Accountable;
import com.alllioooooo.bankingsystem.exceptions.InsufficientFundsException;
import com.alllioooooo.bankingsystem.exceptions.InvalidOperationException;
import com.alllioooooo.bankingsystem.exceptions.UnauthorizedWithdrawalException;

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
        try {
            if (fromAccount.withdraw(amount)) {
                // NOTE: return toAccount.deposit(amount);
                toAccount.deposit(amount);
                return true;
            }
        } catch (InvalidOperationException | InsufficientFundsException e) {
            System.out.println("Transfer failed: " + e.getMessage());
        } catch (UnauthorizedWithdrawalException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public boolean undo() {
        try {
            if (toAccount.withdraw(amount)) {
                //NOTE: maybe deposit return value will be boolean
                fromAccount.deposit(amount);
                return true;
            }
        } catch (UnauthorizedWithdrawalException e) {
            throw new RuntimeException(e);
        } catch (InvalidOperationException e) {
            throw new RuntimeException(e);
        } catch (InsufficientFundsException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}

