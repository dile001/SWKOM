package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.services.NotificationService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender mailSender;

    public NotificationServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(System.getenv("mail-receiver"));
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);

    }
}
