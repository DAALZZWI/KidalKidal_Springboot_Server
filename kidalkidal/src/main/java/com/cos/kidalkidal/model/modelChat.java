package com.cos.kidalkidal.model;

import java.io.Serializable;

public class modelChat implements Serializable {

	private static final long serialVersionUID = 7586523609440769209L;
	
	private String chatUserEmail;
    private String chatUserName;
    private String chatUserImage;
    private String chatRoomName;
    private String chatRoomType;

    public modelChat() {

        this.chatUserEmail = "";
        this.chatUserName = "";
        this.chatUserImage = "";
        this.chatRoomName = "";
        this.chatRoomType = "";
    }

    public String getChatUserEmail() { return chatUserEmail; }

    public void setChatUserEmail( String chatUserEmail ) { this.chatUserEmail = chatUserEmail; }

    public String getChatUserName() { return chatUserName; }

    public void setChatUserName( String chatUserName ) { this.chatUserName = chatUserName; }

    public String getChatUserImage() { return chatUserImage; }

	public void setChatUserImage( String chatUserImage ) { this.chatUserImage = chatUserImage; }

	public String getChatRoomName() { return chatRoomName; }

    public void setChatRoomName( String chatRoomName ) { this.chatRoomName = chatRoomName; }

    public String getChatRoomType() { return chatRoomType; }

    public void setChatRoomType( String chatRoomType ) { this.chatRoomType = chatRoomType; }
}