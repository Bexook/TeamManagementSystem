package com.example.TeamManagementSystem.service.mail;

import javax.mail.MessagingException;

public interface MailSenderService {

    void sendResetPasswordEmail(/*MailType mailType, MailContent mailContent, String emailAddress*/) throws MessagingException;

}
