package com.cos.kidalkidal.model;

import java.io.Serializable;

public class modelEmail implements Serializable {

	private static final long serialVersionUID = 2346727126482166211L;
	
	private String emailFrom;
	private String emailTo;
	private String emailSubject;
	private String emailText;
	
	public modelEmail() {
		
		this.emailFrom = "";
		this.emailTo = "";
		this.emailSubject = "";
		this.emailText = "";
	}

	public String getEmailFrom() { return emailFrom; }

	public void setEmailFrom( String emailFrom ) { this.emailFrom = emailFrom; }

	public String getEmailTo() { return emailTo; }

	public void setEmailTo( String emailTo ) { this.emailTo = emailTo; }

	public String getEmailSubject() { return emailSubject; }

	public void setEmailSubject( String emailSubject ) { this.emailSubject = emailSubject; }

	public String getEmailText() { return emailText; }

	public void setEmailText( String emailText ) { this.emailText = emailText; }
}
