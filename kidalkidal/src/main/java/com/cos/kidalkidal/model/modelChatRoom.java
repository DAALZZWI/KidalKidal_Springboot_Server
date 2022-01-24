package com.cos.kidalkidal.model;

import java.util.HashSet;
import java.util.Set;
import org.springframework.web.socket.WebSocketSession;

public class modelChatRoom {
	
	private String roomId;
	private String roomName;
	private Set< WebSocketSession > sessions;
	
	public modelChatRoom() {
		
		this.roomId = "";
		this.roomName = "";
		this.sessions = new HashSet<>();
	}

	public String getRoomId() { return roomId; }

	public void setRoomId( String roomId ) { this.roomId = roomId; }

	public String getRoomName() { return roomName; }

	public void setRoomName( String roomName ) { this.roomName = roomName; }

	public Set< WebSocketSession > getSessions() { return sessions; }

	public void setSessions( Set< WebSocketSession > sessions ) { this.sessions = sessions; }
}
