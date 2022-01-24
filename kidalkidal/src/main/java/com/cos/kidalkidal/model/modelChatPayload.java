package com.cos.kidalkidal.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class modelChatPayload implements Serializable {

	private static final long serialVersionUID = -3241091336503693692L;
	
	private Map< String , String > chatImage;
    private String chatRoomType;

    public modelChatPayload() {

        this.chatImage = new HashMap< String , String >();
        this.chatRoomType = "";
    }

    public Map< String , String > getChatImage() { return chatImage; }

    public void setChatImage( Map<String, String> chatImage ) { this.chatImage = chatImage; }

    public String getChatRoomType() { return chatRoomType; }

    public void setChatRoomType( String chatRoomType ) { this.chatRoomType = chatRoomType; }
}
