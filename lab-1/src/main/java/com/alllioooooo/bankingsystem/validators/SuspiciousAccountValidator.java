package com.alllioooooo.bankingsystem.validators;

import com.alllioooooo.bankingsystem.accounts.Accountable;
import com.alllioooooo.bankingsystem.clients.Client;

public class SuspiciousAccountValidator {

    public static boolean isAccountSuspicious(Client client) {
        return client.getAddress() == null || client.getPassportNumber() == null;
    }

    public static void validateAccount(Accountable account, Client client) {
        //NOTE: isAccountSuspicious(client) ? account.setSuspicious(true) : account.setSuspicious(false);
        if (isAccountSuspicious(client)) {
            account.setSuspicious(true);
        } else {
            account.setSuspicious(false);
        }
    }
}
