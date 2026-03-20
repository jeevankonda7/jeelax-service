package com.nerdcoder.productapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailTransferService {

  private final JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String fromEmail;

  public EmailTransferService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public String sendEmail(String toMail) {
    try {
      final SimpleMailMessage mailMessage = new SimpleMailMessage();
      mailMessage.setFrom(fromEmail);
      mailMessage.setTo(toMail);
      mailMessage.setSubject("Re: Registration for application");
      mailMessage.setText("Registration done successfully!... Thank You and await for future updates");
      mailSender.send(mailMessage);
      return "SUCCESS";
    }catch (Exception exception) {
       return exception.getMessage();
    }

  }
}
