package com.company.screenshot.email_sender;

import com.company.screenshot.date_format.DateViewFormat;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EmailSender {
    private String to;
    private String from ;
    private String username;
    private String password;
    private String host;

    public void setTo(String to) {
        this.to = to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void sendScreenshot(String pathToScreenshot) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            String currentDate = DateViewFormat.getCurrentDateWithTimeByPattern("dd.MM.YYYY HH:mm:ss");
            message.setSubject(currentDate);

            MimeMultipart multipart = new MimeMultipart("related");

            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = "<img src=\"cid:image\">";
            messageBodyPart.setContent(htmlText, "text/html");

            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(pathToScreenshot);

            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");

            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Sent message done....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
