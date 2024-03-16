package com.alllioooooo.bankingsystem.notifications;

public interface Notificationable {
    // NOTE: add default method here please
    // 1) swich (unum)
    // 2) message is full string
    default void sendNotification(String message) {
        System.out.println(message);
    }
}
