package com.cos.kidalkidal.service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.cos.kidalkidal.model.modelChat;
import com.cos.kidalkidal.model.modelChatPayload;
import com.cos.kidalkidal.model.modelChatRoom;
import com.cos.kidalkidal.model.modelUser;

@Component
@Repository
public class serviceSocketChat extends TextWebSocketHandler {

	public static Map< String , modelChat > mapChat;  
	public static Map< String , modelChatRoom > mapChatRoom;
	public static Map< String , WebSocketSession > mapVisitor;
	public static Map< String , String > mapImage; 
	public static int numberVisitor;
	public modelUser user;
	
	@PostConstruct
	private void init() {
		
		mapChat = new LinkedHashMap<>(); 
		mapChatRoom = new LinkedHashMap<>();
		mapVisitor = new LinkedHashMap<>();
		mapImage = new LinkedHashMap<>();
		numberVisitor = 0;
		user = new modelUser();
	}
	
	@Override
	public void afterConnectionEstablished( WebSocketSession session ) throws Exception {
		
		super.afterConnectionEstablished( session );
		numberVisitor += 1;
		System.out.println( "==============================" );
		System.out.println( "동접수 : " + Integer.toString( numberVisitor ) );
		System.out.println( "입장 : " + session.toString() );
		System.out.println( "==============================" );
		
		modelChat chat = new modelChat();
		
		chat = functionSessionSplit( session );
		
		if( chat.getChatUserEmail() != "kidalkidal.inc@gmail.com" ) {
			
			if( functionRoomCheck( chat.getChatRoomName() ) > 0 ) {
				
				functionRoomAdd( chat.getChatRoomName() );
			}
		}
		
		functionRoomJoin( session , chat );
	}
	
	@Override
	public void afterConnectionClosed( WebSocketSession session , CloseStatus status ) throws Exception {

		numberVisitor -= 1;
		System.out.println( "==============================" );
		System.out.println( "동접수 : " + Integer.toString( numberVisitor ) );
		System.out.println( "퇴장 : " + session.toString() );
		System.out.println( "==============================" );
		
		modelChat chat = new modelChat();
		
		chat = functionSessionSplit( session );
		
		if( functionRoomCheck( chat.getChatRoomName() ) > 0 ) {
			
			functionRoomAdd( chat.getChatRoomName() );
		}
		
		functionRoomLeave( session , chat );
		
		super.afterConnectionClosed( session , status );
	}
	
	@Override
	protected void handleTextMessage( WebSocketSession session, TextMessage message ) throws Exception {
		
		modelChat chat = new modelChat();
		String[] msgArray = new String[] {};
		
		String msg = "";
		String time = functionTimeGenerater();
		
		chat = functionSessionSplit( session );
		msgArray = functionMessageSplit( message.getPayload() );
		
		if( msgArray[0].equals( "JOIN" ) ) {
			
			msg = "JOIN/" + chat.getChatUserEmail();
		}
		
		if( msgArray[0].equals( "CHAT" ) ) {
			
			msg = msgArray[0] + "/" + chat.getChatUserEmail()+ "/" + time + "/" + msgArray[1];
		}
		
		functionMessageSend( chat , msg );
		
		super.handleTextMessage( session, message );
	}
	
	public modelChat functionSessionSplit( WebSocketSession session ) {
		
		modelChat chat = new modelChat();
		
		String[] body = session.getUri().toString().split( "/chat/" );
		String[] url = body[1].split("/");
		
		chat.setChatUserEmail( url[0] );
		chat.setChatUserName( url[1] );
		chat.setChatRoomName( url[2] );
		
		return chat;
	}
	
	public int functionRoomCheck( String chatRoomName ) {
		
		if( mapChatRoom.get( chatRoomName ) == null ) {
			
			return 1;
		} else {
			
			return 0;
		}
	}
	
	public void functionRoomAdd( String chatRoomName ) {
		
		modelChatRoom chatRoom = new modelChatRoom();
		chatRoom.setRoomId( chatRoomName );
		chatRoom.setRoomName( chatRoomName );
		
		mapChatRoom.put( chatRoom.getRoomId() , chatRoom );
	}
	
	public void functionRoomSubtract( String chatRoomName ) {
		
		mapChatRoom.remove( chatRoomName );
	}
	
	public void functionRoomJoin( WebSocketSession session , modelChat chat ) {
		
		mapChatRoom.get( chat.getChatRoomName() ).getSessions().add( session );
	}
	
	public void functionRoomLeave( WebSocketSession session , modelChat chat ) {
		
		mapChatRoom.get( chat.getChatRoomName() ).getSessions().remove( session );
		
		String message = "LEAVE/" + chat.getChatUserEmail() ;
		functionMessageSend( chat , message );
	}  
	
	public void functionMessageSend( modelChat chat , String message ) {
	
		modelChatRoom chatRoom = new modelChatRoom();
		
		chatRoom = mapChatRoom.get( chat.getChatRoomName() );
		
		try {
			
			TextMessage textMessage = new TextMessage( new String( message.getBytes() , "UTF-8" ) );
			
			System.out.println( "==============================" );
			System.out.println( "채팅방 이름 : " + chatRoom.getRoomName() );
			for( WebSocketSession session : chatRoom.getSessions() ) {
				
				System.out.println( "접속 세션 : " + session );
				session.sendMessage( textMessage );
			}
			System.out.println( "==============================" );
		} catch( Exception e ) {
			
			e.printStackTrace();
		}
	}		
	
	public String[] functionMessageSplit( String message ) {
		
		return message.split( "/" );
	}
	
	public String functionTimeGenerater() {
	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format( now );
	}
	
	public List< String > functionChatRoomSelect() {
		
		List< String > listUser = new ArrayList< String >(); 
		
		for( String result : mapChatRoom.keySet() ) {
			
			listUser.add( result );
		}
		
		return listUser;
	}

	public modelChatPayload functionChatImageSelect( modelChatPayload modelChatPayload ) {

        for( String key : modelChatPayload.getChatImage().keySet()  ) {

            String value = modelChatPayload.getChatImage().get( key );
            mapImage.put( key , value );
        }
        
        
        System.out.println( "Type : " + modelChatPayload.getChatRoomType() );
        
        modelChatRoom chatRoom = new modelChatRoom();
		chatRoom = mapChatRoom.get( modelChatPayload.getChatRoomType() );
		modelChatPayload.getChatImage().clear();
		
		try {
			
			Map< String , String > map = new HashMap< String , String >() ; 
			for( WebSocketSession session : chatRoom.getSessions() ) {
				
				String[] body = session.getUri().toString().split( "/chat/" );
				String[] url = body[1].split("/");
				
				map.put( url[0] , mapImage.get( url[0] ) );
			}
			
			modelChatPayload.setChatImage( map );
		} catch( Exception e ) {
			
			e.printStackTrace();
		}
        
		return modelChatPayload;
	}

	public modelChatPayload functionChatImageAdd( String emails ) {
		
		modelChatPayload modelChatPayload = new modelChatPayload();
		Map< String , String > map = new HashMap< String , String >();
		
		String email = emails.replace( "\"","" );
		
		map.put( email , mapImage.get( email ) );
		modelChatPayload.setChatImage( map );
		
		return modelChatPayload;
	}
}
