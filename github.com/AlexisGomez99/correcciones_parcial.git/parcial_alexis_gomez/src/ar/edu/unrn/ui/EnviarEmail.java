package ar.edu.unrn.ui;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ar.edu.unrn.modelo.Observer;

public class EnviarEmail implements Observer {
	// destinatario
	private String from = "from@example.com";
	// usuario y clave que se obtiene desde Mailtrap
	final String username = "216d7f15886590";
	final String password = "e276b84eb45194";
	String host = "smtp.mailtrap.io";
	@Override
	public void enviarEmail(String email, String asunto, String textoEmail) {
				String to = email;
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");// it’s optional in Mailtrap
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.port", "2525");
				// Get the Session object.
				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
				try {
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(from));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
					message.setSubject(asunto);
					message.setText(textoEmail);
					// Send message
					Transport.send(message);
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
	}

}
