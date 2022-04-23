package ua.tunepoint.mail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.tunepoint.mail.config.props.EmailSenderProperties;
import ua.tunepoint.mail.config.props.EmailServerProperties;
import ua.tunepoint.web.exception.ServerException;

import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailServerProperties emailServerProperties;
    private final EmailSenderProperties emailSenderProperties;
    private Properties emailProperties;

    public void send(String recipientAddress, String subject, String body) {
        try {
            Session session = Session.getInstance(emailProperties, authenticator());

            Message message = new MimeMessage(session);
            message.setSubject(subject);
            message.setFrom(
                    new InternetAddress(emailSenderProperties.getAddress())
            );
            message.setRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(recipientAddress)
            );
            message.setContent(body, "text/html");

            Transport.send(message);
        } catch (Exception ex) {
            throw new ServerException(ex.getMessage());
        }
    }

    private Authenticator authenticator() {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailSenderProperties.getAddress(), emailSenderProperties.getPassword());
            }
        };
    }

    @PostConstruct
    public void init() {
        emailProperties = new Properties();

        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.host", emailServerProperties.getHost());
        emailProperties.put("mail.smtp.port", Integer.toString(emailServerProperties.getPort()));
        emailProperties.put("mail.smtp.starttls.required", "true");
        emailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        emailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    }
}
