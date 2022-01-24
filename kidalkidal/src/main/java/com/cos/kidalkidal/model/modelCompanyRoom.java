package com.cos.kidalkidal.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;


public class modelCompanyRoom {
	
	private String companyId;
	private String companyName;
	private Set< WebSocketSession > sessionsCustomer;
	private Set< WebSocketSession > sessionsManager;
	private Map< String , Integer > visitorNumber;
	private int number;
	
	public modelCompanyRoom() {
		
		this.companyId = "";
		this.companyName = "";
		this.sessionsCustomer = new HashSet<>();
		this.sessionsManager = new HashSet<>();
		this.visitorNumber = new HashMap<>();
		this.number = 0;
	}

	public String getCompanyId() { return companyId; }

	public void setCompanyId( String companyId ) { this.companyId = companyId; }

	public String getCompanyName() { return companyName; }

	public void setCompanyName( String companyName ) { this.companyName = companyName; }

	public Set< WebSocketSession > getSessionsCustomer() { return sessionsCustomer; }

	public void setSessionsCustomer( Set< WebSocketSession > sessionsCustomer ) { this.sessionsCustomer = sessionsCustomer; }

	public Set< WebSocketSession > getSessionsManager() { return sessionsManager; }

	public void setSessionsManager( Set< WebSocketSession > sessionsManager ) { this.sessionsManager = sessionsManager; }

	public Map< String , Integer > getVisitorNumber() { return visitorNumber; }

	public void setVisitorNumber( Map< String , Integer > visitorNumber ) { this.visitorNumber = visitorNumber; }

	public int getNumber() { return number; }

	public void setNumber( int number ) { this.number = number; }
}
