package com.cos.kidalkidal.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.cos.kidalkidal.model.modelCompany;
import com.cos.kidalkidal.model.modelCompanyRoom;
import com.cos.kidalkidal.model.modelOrder;
import com.cos.kidalkidal.model.modelVisitorPayload;

@Component
@Repository
public class serviceSocketOrder extends TextWebSocketHandler {

	@Autowired
	private serviceCompany serviceCompany;
	
	public static Map< String , modelCompanyRoom > mapCompanyRoom;
	public static Map< String , String > mapVisitorImage;
	public static int numberVisitor;
	
	@PostConstruct
	public void init() {
		
		mapCompanyRoom = new LinkedHashMap<>();
		mapVisitorImage = new LinkedHashMap<>();
		numberVisitor = 0;
	}
	
	public int functionCompanyInit() {
		
		int num = 0;
		
		if( mapCompanyRoom.isEmpty() ) {
			
			List< modelCompany > companyList = new ArrayList<>();
			companyList = serviceCompany.functionCompanySelectAll();
			
			for( modelCompany company : companyList ) {
				
				modelCompanyRoom companyRoom = new modelCompanyRoom();
				companyRoom.setCompanyId( company.getCompanyId() );
				companyRoom.setCompanyName( company.getCompanyName() );
				mapCompanyRoom.put( company.getCompanyId() , companyRoom );
			}
			num = 1;
		}
		
		return num;
	}
	
	public modelVisitorPayload functionVisitorImageInsert( modelVisitorPayload modelVisitorPayload ) {
		
		for( String key : modelVisitorPayload.getVisitorImage().keySet() ) {
			
			mapVisitorImage.put( key , modelVisitorPayload.getVisitorImage().get( key ) ) ;
		}
		
		return modelVisitorPayload;
	}

	@Override
	public void afterConnectionEstablished( WebSocketSession session ) throws Exception {

		super.afterConnectionEstablished( session );
		
		numberVisitor += 1;
		System.out.println( "==============================" );
		System.out.println( "동접수 : " + Integer.toString( numberVisitor ) );
		System.out.println( "입장 : " + session.toString() );
		System.out.println( "==============================" );
		
		modelOrder order = new modelOrder();
		
		order = functionSessionSplit( session );
		
		functionOrderJoin( session , order );	
	}
	
	@Override
	public void afterConnectionClosed( WebSocketSession session , CloseStatus status ) throws Exception {

		super.afterConnectionClosed( session , status );
		
		numberVisitor -= 1;
		System.out.println( "==============================" );
		System.out.println( "동접수 : " + Integer.toString( numberVisitor ) );
		System.out.println( "퇴장 : " + session.toString() );
		System.out.println( "==============================" );
			
		modelOrder order = new modelOrder();
		
		order = functionSessionSplit( session );
		
		functionOrderLeave( session , order );
	}
	
	@Override
	protected void handleTextMessage( WebSocketSession session , TextMessage message ) throws Exception {

		super.handleTextMessage( session , message );
		
		modelOrder order = new modelOrder();
		String[] msgArray = new String[] {};
		String msg = "";
		
		if( session.getUri().toString().contains( "/order/customer/" ) ) {
			
			order = functionSessionSplit( session );
			msgArray = functionMessageSplit( message.getPayload() );
			
			if( msgArray[0].equals( "JOIN" ) ) {
				
				String companyName = mapCompanyRoom.get( msgArray[2] ).getCompanyName();
				int number = mapCompanyRoom.get( msgArray[2] ).getVisitorNumber().get( msgArray[1] );
				msg = "JOIN/" + order.getOrderUserEmail() + "/" + companyName + "/" + number;
				System.out.println(msg);
			}
			
			functionMessageSend( session , msg );
		}
		
		if( session.getUri().toString().contains( "/order/manager/" ) ) {
			
			order = functionSessionSplit( session );
			msgArray = functionMessageSplit( message.getPayload() );
			
			if( msgArray[0].equals( "LIST" ) ) {
				
				Map< String , Integer > visitorNumber = new HashMap<>();
				visitorNumber = mapCompanyRoom.get( order.getOrderCompanyId() ).getVisitorNumber();
				msg = "LIST/";
				
				for( String key : visitorNumber.keySet() ) {
					
					System.out.println( "key : " + key + " value : " + visitorNumber.get( key ) );
					msg += key + "," + visitorNumber.get( key ) + "/";
				}
				System.out.println( msg );

			}

			if( msgArray[0].equals( "USERPICK" ) ) {
				
				Set< WebSocketSession > sessions = new HashSet<>();
				msg = "PICK/";
				sessions = mapCompanyRoom.get( order.getOrderCompanyId() ).getSessionsCustomer();
				
				for( WebSocketSession ses : sessions ) {
					
					System.out.println( "in" );
					System.out.println( msgArray[1] );
					if( ses.getUri().toString().contains( msgArray[1] ) ) {
						
						session = ses;
						System.out.println( session );
					}
				}
				System.out.println( msg );
			}
			
			functionMessageSend( session , msg );
		}
	}
	
	public modelOrder functionSessionSplit( WebSocketSession session ) {
		
		modelOrder order = new modelOrder();
		
		if( session.getUri().toString().contains( "/order/customer/" ) ) {
			
			String[] body = session.getUri().toString().split( "/order/customer/" );
			String[] url = body[1].split("/");
			
			order.setOrderUserEmail( url[ 0 ] );
			order.setOrderCompanyId( url[ 1 ] );
		}
		
		if( session.getUri().toString().contains( "/order/manager/" ) ) {
			
			String[] body = session.getUri().toString().split( "/order/manager/" );
			String[] url = body[1].split("/");
			
			order.setOrderUserEmail( url[ 0 ] );
			order.setOrderCompanyId( url[ 1 ] );
		}
		
		
		return order;
	}
	
	public void functionOrderJoin( WebSocketSession session , modelOrder order ) {
		
		if( session.getUri().toString().contains( "/order/customer/" ) ) {
			
			int size = mapCompanyRoom.get( order.getOrderCompanyId() ).getNumber() + 1;
			
			mapCompanyRoom.get( order.getOrderCompanyId() ).getSessionsCustomer().add( session );
			mapCompanyRoom.get( order.getOrderCompanyId() ).setNumber( size );
			mapCompanyRoom.get( order.getOrderCompanyId() ).getVisitorNumber().put( order.getOrderUserEmail() , size );
			
			functionOrderAdd( order , size );
		}
		
		if( session.getUri().toString().contains( "/order/manager/" ) ) {
			
			mapCompanyRoom.get( order.getOrderCompanyId() ).getSessionsManager().add( session );
		}
		
		modelCompanyRoom companyRoom = mapCompanyRoom.get( order.getOrderCompanyId() );
		
		for( WebSocketSession s : companyRoom.getSessionsCustomer() ) {
		
			System.out.println( companyRoom.getCompanyId() + "- CustomerSession : " + s );
		}
		
		for( WebSocketSession s : companyRoom.getSessionsManager() ) {
			
			System.out.println( companyRoom.getCompanyId() + "- ManagerSession : " + s );
		}
		
		System.out.println( "==============================" );
	}
	
	public void functionOrderLeave( WebSocketSession session , modelOrder order ) {
		
		if( session.getUri().toString().contains( "/order/customer/" ) ) {
			
			mapCompanyRoom.get( order.getOrderCompanyId() ).getSessionsCustomer().remove( session );
		}
		
		if( session.getUri().toString().contains( "/order/manager/" ) ) {
			
			mapCompanyRoom.get( order.getOrderCompanyId() ).getSessionsManager().remove( session );
		}	
		
		modelCompanyRoom companyRoom = mapCompanyRoom.get( order.getOrderCompanyId() );
		
		for( WebSocketSession s : companyRoom.getSessionsCustomer() ) {
		
			System.out.println( companyRoom.getCompanyId() + "- CustomerSession : " + s );
		}
		
		for( WebSocketSession s : companyRoom.getSessionsManager() ) {
			
			System.out.println( companyRoom.getCompanyId() + "- ManagerSession : " + s );
		}
		
		System.out.println( "==============================" );
	}  
	
	public void functionOrderAdd( modelOrder order , int size ) {
		
		modelCompanyRoom companyRoom = mapCompanyRoom.get( order.getOrderCompanyId() );

		String msg = "";
		
		msg = "LISTADD/" + order.getOrderUserEmail() + "," + size + "/";
	
		System.out.println( msg );
		
		for( WebSocketSession s : companyRoom.getSessionsManager() ) {
			
			try {
				
				TextMessage textMessage = new TextMessage( new String( msg.getBytes() , "UTF-8" ) );
				s.sendMessage( textMessage );
			} catch( Exception e ) {
				
				e.printStackTrace();
			}
		}
	}
	
	public void functionOrderSubtract( modelOrder order  ) {
		
		modelCompanyRoom companyRoom = mapCompanyRoom.get( order.getOrderCompanyId() );

		String msg = "";
		
		msg = "LISTSUB/" + order.getOrderUserEmail() + "/";
	
		System.out.println( msg );
		
		for( WebSocketSession s : companyRoom.getSessionsManager() ) {
			
			try {
				
				TextMessage textMessage = new TextMessage( new String( msg.getBytes() , "UTF-8" ) );

					s.sendMessage( textMessage );
			} catch( Exception e ) {
				
				e.printStackTrace();
			}
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
	
	

	private void functionMessageSend( WebSocketSession session , String msg ) {

		try {
				
			TextMessage textMessage = new TextMessage( new String( msg.getBytes() , "UTF-8" ) );

				session.sendMessage( textMessage );
		} catch( Exception e ) {
			
			e.printStackTrace();
		}
	}
	
	public modelVisitorPayload functionVisitorImageSelect( modelVisitorPayload modelVisitorPayload ) {
		
		for( String name : mapVisitorImage.keySet() ) {
			
			modelVisitorPayload.getVisitorImage().put( name , mapVisitorImage.get( name ) );
		}
		
		return modelVisitorPayload;
	}
	
	public List< modelCompany > functionCompanySelect() {
		
		List< modelCompany > companyList = new ArrayList<>();
		companyList = serviceCompany.functionCompanySelectAll();
		
		if( mapCompanyRoom.isEmpty() ) {
			
			if( !companyList.isEmpty() ) {
				
				for( modelCompany result : companyList ) {
					
					modelCompanyRoom modelCompanyRoom = new modelCompanyRoom();
					modelCompanyRoom.setCompanyId( result.getCompanyId() );
					modelCompanyRoom.setCompanyName( result.getCompanyName() );
					
					mapCompanyRoom.put( result.getCompanyName() , modelCompanyRoom  );
				}
			}
		}
		
		return companyList;
	}
	
	public void dbInit() {
		
		List< modelCompany > companyList = new ArrayList<>();
		companyList = serviceCompany.functionCompanySelectAll();
		
		for( modelCompany company : companyList ) {
			
			modelCompanyRoom companyRoom = new modelCompanyRoom();
			companyRoom.setCompanyId( company.getCompanyId() );
			companyRoom.setCompanyName( company.getCompanyName() );
			
			serviceSocketOrder.mapCompanyRoom.put( company.getCompanyId() , companyRoom );
		}
	}
}
