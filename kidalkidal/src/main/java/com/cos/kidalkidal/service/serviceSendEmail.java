package com.cos.kidalkidal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.cos.kidalkidal.model.modelEmail;

@Service
public class serviceSendEmail {
	
	@Autowired
	private JavaMailSender senderJavaMail;
	
	public boolean functionSendEmail( modelEmail modelEmail ) {
		
		try {
			
			SimpleMailMessage message = new SimpleMailMessage();
			
			message.setFrom( modelEmail.getEmailFrom() );
			message.setTo( modelEmail.getEmailTo() );
			message.setSubject( modelEmail.getEmailSubject() );
			message.setText( modelEmail.getEmailText() );
			
			senderJavaMail.send( message );
			
			return true;
		} catch ( Exception e ) {
			
			return false;
		}
	}
}
