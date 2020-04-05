package com.demo.entity;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class SendEmail{

    public String sendEmail(String newEmail) throws MessagingException, IOException {
        sendmail(newEmail);
        return "Email sent successfully";
    }

    /**
     * Trimite email al o anumita adresa trimisa ca argument
     * @param newEmail adresa destinatarului
     * @throws MessagingException
     * @throws IOException
     */
    private void sendmail(String newEmail) throws MessagingException, IOException {
        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        String email = "<email>";
        String pass = "<pass>";

        /*props.put("mail.smtp.port", "587");
        props.put("mail.host", "smtp.mail.yahoo.com");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); */

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, pass);
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(email, false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(newEmail));
        msg.setSubject("Test");
        msg.setContent("Salut!", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Salut!", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        //MimeBodyPart attachPart = new MimeBodyPart();

        //attachPart.attachFile("/var/tmp/image19.png");
        //multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }
}
