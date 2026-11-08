package util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailSender {
    private static final String USERNAME = System.getenv("SMTP_USERNAME");
    private static final String PASSWORD = System.getenv("SMTP_APP_PASSWORD");

    public static void enviarCodigo(String destinatario, String codigo) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        message.setSubject("Código de verificación ITSE");
        message.setText("Tu código de verificación es: " + codigo);
        message.setReplyTo(InternetAddress.parse(USERNAME));
        message.setHeader("X-Mailer", "JavaMail");
        message.setHeader("Content-Type", "text/plain; charset=UTF-8");

        Transport.send(message);
        System.out.println("Correo enviado con código: " + codigo + " a " + destinatario);
    }
}

// contraseña: zfuk szna arwe nbqf
