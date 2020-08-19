package edu.unah.poo.function;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;

public class EmailSender {

    private String Sender = "jorgechinchillafk@gmail.com";
    private String Password = "unapalabra";
    private String Receiver;
    private String Subject;
    private String MessageToSend;

    public EmailSender(String receiver, String subject, String messageToSend) {
        Receiver = receiver;
        Subject = subject;
        MessageToSend = messageToSend;
    }

    public void sendmail() throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Sender , Password);
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(Sender , false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Receiver));
        msg.setSubject(Subject);
        msg.setContent("SistemaDeVentas", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(MessageToSend, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
//        MimeBodyPart attachPart = new MimeBodyPart();
//
//        attachPart.attachFile("/static/images/sdv.png");
//        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);

    }

}
