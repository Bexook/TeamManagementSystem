package com.example.petProject.service.mail;

import com.example.petProject.model.MailContent;
import com.example.petProject.model.enumTypes.MailType;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface MailSenderService {

    void sendResetPasswordEmail(/*MailType mailType, MailContent mailContent, String emailAddress*/) throws MessagingException;

}
