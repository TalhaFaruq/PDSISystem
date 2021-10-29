package com.tfworkers.PDSISystem.Utilities;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailUtil {

	/**
	 * The Java mail sender.
	 */
	JavaMailSender javaMailSender;
	/**
     * Instantiates a new Mail service.
     *
     * @param javaMailSender the java mail sender
     */
    public EmailUtil(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

	/**
	 * Send mail.
	 *
	 * @param recipient the recipient
	 * @param subject   the subject
	 * @param message   the message
	 */
	public void sendMail(String recipient, String subject, String message) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(recipient, recipient);

		msg.setSubject(subject);
		msg.setText(message);

		javaMailSender.send(msg);
	}

}
