package com.cos.kidalkidal.model;

import java.io.Serializable;
import java.util.ArrayList;

public class modelUserPayload implements Serializable {

	private static final long serialVersionUID = 5049417350940997262L;
	
	private int code;
	private String msg; 
	private modelUser modelUser;
	private ArrayList<modelUser> modelUsers;
	
	public modelUserPayload() {
		
		this.code = 0;
		this.msg = "";
		this.modelUser = new modelUser();
		this.modelUsers = new ArrayList<modelUser>();
	}
	
	public void setModelUserPayload( int code , String msg , modelUser modelUser ) {
		
		this.code = code;
		this.msg = msg;
		this.modelUser = modelUser;
	}
	public void setModelUsersPayload( int code , String msg , ArrayList<modelUser> modelUsers ) {
		
		this.code = code;
		this.msg = msg;
		this.modelUsers = modelUsers;
	}

	public int getCode() { return code; }

	public void setCode( int code ) { this.code = code; }
	
	public String getMsg() { return msg; }

	public void setMsg( String msg ) { this.msg = msg; }

	public modelUser getModelUser() { return modelUser; }

	public void setModelUser( modelUser modelUser ) { this.modelUser = modelUser; }

	public ArrayList< modelUser > getModelUsers() { return modelUsers; }

	public void setModelUsers( ArrayList<modelUser> modelUsers ) { this.modelUsers = modelUsers; }
	
}
