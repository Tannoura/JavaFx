/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Koussay
 */
public class EmailSender { public static void sendEmail(String recipient, String subject, String body) throws MessagingException {
      // Set SMTP server properties
      Properties props = new Properties();
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.socketFactory.port", "465");
      props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.port", "465");

      // Create a session with SMTP authentication
      Session session = Session.getInstance(props, new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
             //email and application mdp (lezm 2fa tkoun acitve aal compte)
            return new PasswordAuthentication("louay.zarrouk@esprit.tn", "202JMT3061");
         }
      });

      // Create a new email message
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("louay.zarrouk@esprit.tn"));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
      message.setSubject(subject);
      message.setText(body);

      // Send the email
      Transport.send(message);
      System.out.println("Email sent successfully.");
   }
    
}
