package com.example.petProject.service.mail.impl;

import com.example.petProject.model.MailContent;
import com.example.petProject.model.enumTypes.MailType;
import com.example.petProject.service.mail.MailSenderService;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

@Service
public class MailSenderServiceImpl implements MailSenderService {


    @Override
    public void sendResetPasswordEmail(/*MailType mailType, MailContent mailContent, String emailAddress*/) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("andrik.mykytyn@gmail.com","dunlopdunlopdunlop");
            }
        });


        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("andrik.mykytyn@gmail.com", false));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("andriy.mykytyn@sombrainc.com"));
        message.setSubject("Tutorials point email");
        message.setContent("Tutorials point email", "text/html");
        message.setSentDate(new Date());


//        Transport.send(message);
//
////        MimeBodyPart messageBodyPart = new MimeBodyPart();
////        messageBodyPart.setContent("Tutorials point email", "text/html");
////
////        message.setContent(messageBodyPart);
    }



//    private Message buildMessage(Pair<String,String> emailFromAuth,String emailTo){
//
//    }
}
