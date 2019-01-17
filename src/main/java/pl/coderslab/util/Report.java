package pl.coderslab.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Report {

    public void sendMail(String login, String email, String passBeforeHash) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("maciekrosiaktesting@gmail.com", "Testing911");
            }

        });

        try {

            MimeMessage msg = new MimeMessage(session);
            String to = email;
            InternetAddress[] address = InternetAddress.parse(to, true);
            msg.setRecipients(Message.RecipientType.TO, address);
            String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
            msg.setSubject("M-EVENT Twoje dane do logowania: " + timeStamp);
            msg.setSentDate(new Date());
            msg.setText("Twój login to: " + login + ", a Twoje hasło to: " + passBeforeHash);
            msg.setHeader("XPriority", "1");
            Transport.send(msg);

            System.out.println("Mail has been sent successfully");

        } catch (MessagingException mex) {

            System.out.println("Unable to send an email" + mex);

        }

    }

}
