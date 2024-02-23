package com.alllioooooo.bankingsystem.bank;

import com.alllioooooo.bankingsystem.notifications.Observer;

public interface Bank {

    void removeObserver(Observer o);

    void registerObserver(Observer o);

    void notifyObservers();
}
