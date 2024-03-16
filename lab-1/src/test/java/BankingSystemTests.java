import com.alllioooooo.bankingsystem.accounts.CreditAccount;
import com.alllioooooo.bankingsystem.accounts.DebitAccount;
import com.alllioooooo.bankingsystem.exceptions.InsufficientFundsException;
import com.alllioooooo.bankingsystem.exceptions.InvalidOperationException;
import com.alllioooooo.bankingsystem.exceptions.UnauthorizedWithdrawalException;
import com.alllioooooo.bankingsystem.transactions.TransferCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankingSystemTests {

    @Test
    public void testDebitAccountDepositIncreasesBalance() throws InvalidOperationException {
        DebitAccount account = new DebitAccount(0.02, 1000);
        double initialBalance = account.getBalance();
        account.deposit(500);
        assertEquals(initialBalance + 500, account.getBalance(), 0.01);
    }

    @Test
    public void testTransferBetweenAccounts() throws InvalidOperationException, InsufficientFundsException, UnauthorizedWithdrawalException {
        DebitAccount fromAccount = new DebitAccount(0.02, 1000);
        fromAccount.deposit(1000);
        CreditAccount toAccount = new CreditAccount(1000, 50, 200);

        double fromAccountInitialBalance = fromAccount.getBalance();
        double toAccountInitialBalance = toAccount.getBalance();

        TransferCommand transfer = new TransferCommand(fromAccount, toAccount, 500);
        transfer.execute();

        assertEquals(fromAccountInitialBalance - 500, fromAccount.getBalance(), 0.01);
        assertEquals(toAccountInitialBalance + 500, toAccount.getBalance(), 0.01);
    }
}