package com.tfworkers.PDSISystem.Utilities;

import org.springframework.stereotype.Service;

import com.tfworkers.PDSISystem.Model.Entity.SMS;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class SmsUtil {
	private String ACCOUNT_SID = "AC3fa3c0ff18fd3891dee5c43cc9b4d103";

	private String AUTH_TOKEN = "a1925e78481f1d0a22224ea7581cf53d";

	private String FROM_NUMBER = "+12058594517";

	/**
	 * Send.
	 *
	 * @param sms
	 */
	public void send(SMS sms) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
				.create();
		System.out.println("here is my id:" + message.getSid());// Unique resource ID created to manage this transaction

	}

}
