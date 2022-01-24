package com.cos.kidalkidal.model;

import java.io.Serializable;

public class modelUserImage implements Serializable {

	private static final long serialVersionUID = 2367532255279369681L;
	
	private String userImage;
	
	public modelUserImage() {
		
		userImage = "";
	}

	public String getUserImage() { return userImage; }

	public void setUserImage( String userImage ) { this.userImage = userImage; }
}
