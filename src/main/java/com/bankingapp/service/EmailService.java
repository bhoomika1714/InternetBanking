package com.bankingapp.service;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class EmailService {

    private final SendGrid sendGrid;

    // ✅ Use your verified sender email here
    private static final String VERIFIED_SENDER = "rsbhoomikamarigoudar17116@gmail.com";

    public EmailService(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    public String sendEmail(String to, String subject, String body) {
        Email from = new Email(VERIFIED_SENDER);
        Email recipient = new Email(to);
        Content content = new Content("text/plain", body);
        Mail mail = new Mail(from, subject, recipient, content);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            return "Status: " + response.getStatusCode() + " - " + response.getBody();
        } catch (IOException ex) {
            return "Error: " + ex.getMessage();
        }
    }

    // OTP-specific helper
    public String sendOtpEmail(String toEmail, String otp) {
        String subject = "Your OTP Code";
        String body = "Your One-Time Password (OTP) is: " + otp +
                      "\nThis code will expire in 5 minutes.";
        return sendEmail(toEmail, subject, body);
    }
}
