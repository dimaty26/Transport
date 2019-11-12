package org.ncstudy.authentication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${spring.mail.host}")
    String host;
    @Value("${spring.mail.username}")
    String username;
    @Value("${spring.mail.password}")
    String password;
    @Value("${spring.mail.port}")
    Integer port;
    @Value("${spring.mail.protocol}")
    String protocol;

    @Bean
    public JavaMailSender javaMailSenderBean(){
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(host);
        sender.setPort(port);
        sender.setUsername(username);
        sender.setPassword(password);
        Properties javaMailProperties = sender.getJavaMailProperties();
        javaMailProperties.setProperty("mail.transport.protocol", protocol);
        javaMailProperties.setProperty("mail.debug", "true");
        return sender;
    }
}
