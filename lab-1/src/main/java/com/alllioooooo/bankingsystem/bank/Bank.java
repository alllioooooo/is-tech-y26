package com.alllioooooo.bankingsystem.bank;

import com.alllioooooo.bankingsystem.accounts.Accountable;
import com.alllioooooo.bankingsystem.clients.Client;
import com.alllioooooo.bankingsystem.notifications.Observer;

public interface Bank {

    public void registerAccount(Client client, Accountable account);

    void removeObserver(Observer o);

    void registerObserver(Observer o);

    public void notifyObservers(String message);
}
