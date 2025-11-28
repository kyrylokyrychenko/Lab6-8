package utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class ErrorNotifier {

    // === Налаштування e-mail ===
    private static final String USER = "rockduck56@gmail.com";       // твій Gmail
    private static final String APP_PASSWORD = "otvf itcr yycv izro"; // App Password 16 символів
    private static final String TO = "rockduck56@gmail.com";          // куди надсилати (може бути той самий)

    public static void sendErrorEmail(Exception e) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true"); // TLS
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USER, APP_PASSWORD);
                }
            });

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(USER));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TO));
            msg.setSubject("КРИТИЧНА ПОМИЛКА У ПРОГРАМІ");
            msg.setText("Виникла помилка:\n\n" + e.toString());

            Transport.send(msg);
            System.out.println("Повідомлення про помилку надіслано!");

        } catch (Exception ex) {
            System.out.println("Не вдалося надіслати email:");
            ex.printStackTrace();
        }
    }
}
