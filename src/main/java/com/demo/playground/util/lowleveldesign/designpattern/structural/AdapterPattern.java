package com.demo.playground.util.lowleveldesign.designpattern.structural;

public class AdapterPattern {
    public static void main(String[] args) {
        EmailService legacy = new EmailNotification();
        legacy.send("sasi", "test", "hello");


        EmailService sendGrid = new SendGridAdapter(new SendGridNotification());
        sendGrid.send("damu", "test", "hi");
    }
}

interface EmailService {
    void send(String to, String subject, String message);
}

class EmailNotification implements EmailService {

    @Override
    public void send(String to, String subject, String message) {
        System.out.println("Sending email using legacy mails " + message);
    }
}

class SendGridNotification {
    public void sendEmail(String recepient, String header, String message) {
        System.out.println("Sending email using sendGrid mails " + message);
    }
}

class SendGridAdapter implements EmailService {

    private SendGridNotification sendGridNotification;

    public SendGridAdapter(SendGridNotification sendGridNotification) {
        this.sendGridNotification = sendGridNotification;
    }

    @Override
    public void send(String to, String subject, String message) {
        sendGridNotification.sendEmail(to, subject, message);
    }
}
