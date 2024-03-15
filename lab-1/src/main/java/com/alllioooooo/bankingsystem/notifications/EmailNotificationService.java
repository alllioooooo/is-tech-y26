package com.alllioooooo.bankingsystem.notifications;

public class EmailNotificationService implements Notificationable {
    @Override
    public void sendNotification(String message) {
        System.out.println("Email sent with message: " + message);
    }
}