package org.ncstudy.authentication.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender {

    private final JavaMailSender sender;

    @Value("${spring.mail.username}")
    String username;

    public MailSender(@Qualifier("javaMailSenderBean") JavaMailSender sender) {
        this.sender = sender;
    }

    public void send(String email, String theme, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(email);
        mailMessage.setSubject(theme);
        mailMessage.setText(message);
        sender.send(mailMessage);
    }
}
