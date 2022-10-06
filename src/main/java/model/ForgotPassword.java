package model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ForgotPassword {
    private static String recoveryCode;
    private static String password;
    private static String email;
    private Connection conn;

    public String getRecoveryCode() {
        return recoveryCode;
    }

    public void setRecoveryCode(String recoveryCode) {
        this.recoveryCode = recoveryCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void sendEmail(String forgotEmail) {
        Properties props = new Properties();
        /* Parâmetros de conexão com servidor Hotmail */
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication("bytech2022@outlook.com", "Bytech!@#");
                    }
                });

        /** Ativa Debug para sessão */
        session.setDebug(false);

        int min = 100000;
        int max = 999999;
        int range = max - min + 1;
        String newCode = Integer.toString((int) (Math.random() * range) + min);
        setRecoveryCode(newCode);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("bytech2022@outlook.com")); //Remetente

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(forgotEmail)); //Destinatário(s)
            message.setSubject("Recuperação de Senha - TrackCash");//Assunto
            message.setText("Utilize o seguinte código para recuperar a senha: " + recoveryCode);
            /*Método para enviar a mensagem criada*/
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean codeTest(String testCode) {
        return testCode.equals(getRecoveryCode());
    }
    public boolean emailTest() {
        try {
            conn = ConnectionFactory.getConnection();
            String select = "SELECT email FROM users WHERE email=(?)";
            PreparedStatement stmt = this.conn.prepareStatement(select);
            stmt.setString(1, getEmail());
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                String dbEmail = resultSet.getString("email");
                if (getEmail().equals(dbEmail)) {
                    stmt.close();
                    return true;
                }
            }
        } catch (SQLException U) {
            throw new RuntimeException(U);
        }
        return false;
    }

    public void passwordUpdate(String newPassword) {
        try {
            conn = ConnectionFactory.getConnection();
            String update = "UPDATE users SET password=(?) WHERE email=(?)";
            PreparedStatement stmt = this.conn.prepareStatement(update);
            stmt.setString(1, newPassword);
            stmt.setString(2, getEmail());
            stmt.execute();
            stmt.close();
        } catch (SQLException U) {
            throw new RuntimeException(U);
        }
    }

}
