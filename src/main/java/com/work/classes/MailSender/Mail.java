package com.work.classes.MailSender;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail   {
    Session session = null;
    String rec, subject, textMessage;

    public Mail(String rec, String subject,String textMessage){
        this.rec = rec;
        this.subject = subject;
        this.textMessage = textMessage;
    }

    public void Send() throws AddressException, MessagingException{
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ensas.geo@gmail.com", "ensas123");
            }
        });

        Message message = new MimeMessage(session);
        String mail_body = "<html><head></head><body><h1 style='color:green;'>CLUB COC NewsLetter</h1><b>"+textMessage+"</b></body></html>";
        String encodingOptions = "text/html; charset=UTF-8";
        message.setFrom(new InternetAddress("ensas.geo@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec));
        message.setSubject(subject);
        message.setContent("Bonjour", "text/html; charset=utf-8");
        message.setText(mail_body);
        message.setHeader("Content-Type", encodingOptions);
        Transport.send(message);
    }

}