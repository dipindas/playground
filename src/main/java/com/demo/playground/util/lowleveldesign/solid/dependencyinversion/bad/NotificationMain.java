package com.demo.playground.util.lowleveldesign.solid.dependencyinversion.bad;

public class NotificationMain {
    public static void main(String[] args) {
        NotificationSenderService notificationSenderService = new NotificationSenderService();
        notificationSenderService.sendNotification();
    }
}

class NotificationSenderService {

    public void sendNotification() {
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.send();

        WhatsappService whatsappService = new WhatsappService();
        whatsappService.send();
    }
}

class EmailNotification {
    public void send() {
        System.out.println("Sending email notification");
    }
}

class WhatsappService {
    public void send() {
        System.out.println("Sending whatsapp notification");
    }
}