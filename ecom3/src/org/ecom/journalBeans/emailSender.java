package org.ecom.journalBeans;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * SendEmail Class will send an email to a user using sendEmail()
 * 
 */
public class emailSender {

	private static final String EMAIL_USERNAME = "ecom.editor@gmail.com";
	private static final String EMAIL_PASSWORD = "ecom.editor";

	/**
	 * Send an email to one recipient at a time (using gmail)
	 * 
	 * @param toEmailAddress
	 * @param EmailSubject
	 * @param EmailBody
	 * @return
	 */
	public boolean sendEmail(String toEmailAddress, String EmailSubject,
			String EmailBody) {

		boolean ret = false;
		// Set general properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		// initial the authentication
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(EMAIL_USERNAME,
								EMAIL_PASSWORD);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(EMAIL_USERNAME));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toEmailAddress));
			message.setSubject(EmailSubject);
			message.setText(EmailBody);

			Transport.send(message);

			ret = true;

		} catch (MessagingException e) {
			// throw new RuntimeException(e);
			ret = false;
		}
		return ret;
	}
}