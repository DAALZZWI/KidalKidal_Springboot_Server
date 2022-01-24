package com.cos.kidalkidal.model;

import java.io.Serializable;

public class modelUser implements Serializable {
	
	private static final long serialVersionUID = 2255495030294874177L;
	
	private int userPk;
    private int userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userRegisterDate;
    private String userStatus;
    private String userImage;

    public modelUser() {

        this.userPk = 0;
        this.userId = 0;
        this.userPassword = "";
        this.userName = "";
        this.userEmail = "";
        this.userRegisterDate = "";
        this.userStatus = "";
        this.userImage = "";
    }
    
    public int getUserPk() { return userPk; }

    public void setUserPk( int userPk ) { this.userPk = userPk; }

    public int getUserId() { return userId; }

    public void setUserId( int userId ) { this.userId = userId; }

    public String getUserPassword() { return userPassword; }

    public void setUserPassword( String userPassword ) { this.userPassword = userPassword; }

    public String getUserName() { return userName; }

    public void setUserName( String userName ) { this.userName = userName; }

    public String getUserEmail() { return userEmail; }

    public void setUserEmail( String userEmail ) { this.userEmail = userEmail; }

    public String getUserRegisterDate() { return userRegisterDate; }

    public void setUserRegisterDate( String userRegisterDate ) { this.userRegisterDate = userRegisterDate; }

    public String getUserStatus() { return userStatus; }

    public void setUserStatus( String userStatus ) { this.userStatus = userStatus; }

    public String getUserImage() { return userImage; }

    public void setUserImage( String userImage ) { this.userImage = userImage; }
}
