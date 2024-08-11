package com.assessment.comsc.AdminDashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    public ResponseEntity<String> sendEmail( Email email) {
        try {
            // Retireve the properties
            SimpleMailMessage emailBody = new SimpleMailMessage();
            emailBody.setTo(email.getMailto());
            emailBody.setSubject(email.getSubject());
            emailBody.setText(email.getBody());

            // Send the email
            javaMailSender.send(emailBody);
            System.out.println("Email sent successfully");

            return ResponseEntity.ok("Email sent successfully");
        } catch (MailException e) {
            // Handle exception and return an error response
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email");
        }
    }

}
