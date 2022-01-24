package com.cos.kidalkidal.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cos.kidalkidal.model.modelEmail;
import com.cos.kidalkidal.model.modelUserPayload;
import com.cos.kidalkidal.model.modelUser;

@Service
public class serviceRegister {
	
	@Autowired
	private serviceUser serviceUser;

	@Autowired
	private serviceSendEmail serviceSendEmail;
	
	public modelUserPayload functionUserRegister ( modelUserPayload modelUserPayload ) {
		
		modelUserPayload.getModelUser().setUserStatus( "y" );
		modelUserPayload.getModelUser().setUserRegisterDate( functionUserRegisterDate() );
		
		if( serviceUser.userSelect( modelUserPayload.getModelUser().getUserEmail() ) != null ) {
			
			modelUserPayload.setModelUserPayload( 100 ,  "account that's already registered" ,  new modelUser() );
			return modelUserPayload;
		}
		
		if( serviceUser.userInsert( modelUserPayload.getModelUser() ) == 0 ) {
			
			modelUserPayload.setModelUserPayload( 101 ,  "error server database" ,  new modelUser() );
			return modelUserPayload;
		}
		
		if( functionSendEmailRegister( modelUserPayload.getModelUser() ) == false ) {
			
			modelUserPayload.setModelUserPayload( 201 ,  "success register : fail sending" ,  new modelUser() );
			return modelUserPayload;
		}
		
		modelUserPayload.setModelUserPayload( 200 ,  "success register : success sending" ,  new modelUser() );
		
		return modelUserPayload;
	}
	
	public modelUserPayload functionUserUnRegister ( modelUserPayload modelUserPayload ) {
		
		if( serviceUser.userDelete( modelUserPayload.getModelUser().getUserEmail() ) == 0 ) {
			
			modelUserPayload.setModelUserPayload( 100 ,  "fail unregister : failed to unregister  " ,  new modelUser() );
		}
	
		if( functionSendEmailUnRegister( modelUserPayload.getModelUser() ) == false ) {
			
			modelUserPayload.setModelUserPayload( 201 ,  "success unregister : fail sending" ,  new modelUser() );
		}
		
		modelUserPayload.setModelUserPayload( 200 ,  "success unregister : success sending" ,  new modelUser() );
		return modelUserPayload;
	}
	
	public String functionUserRegisterDate() {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
		String day = dateFormat.format( new Date() );
		
		return day;
	}
	
	public boolean functionSendEmailRegister( modelUser modelUser ) {
		
		modelEmail modelEmail = new modelEmail();
		
		modelEmail.setEmailFrom( "kidalkidal.inc@gmail.com" );
		modelEmail.setEmailTo( modelUser.getUserEmail() );
		modelEmail.setEmailSubject( "[  기달기달 : 회원가입  ] " + modelUser.getUserName() + "님! 회원가입 축하해요!!" );
		modelEmail.setEmailText( "고객님께선 " + modelUser.getUserRegisterDate() + "일 기준으로 가입이 완료되었어요!!"  );
		
		if( serviceSendEmail.functionSendEmail( modelEmail ) == true ) {
			
			return true;
		} else {
			
			return false;
		}
	}
	
	public boolean functionSendEmailUnRegister( modelUser modelUser ) {
		
		modelEmail modelEmail = new modelEmail();
		
		modelEmail.setEmailFrom( "kidalkidal.inc@gmail.com" );
		modelEmail.setEmailTo( modelUser.getUserEmail() );
		modelEmail.setEmailSubject( "[  기달기달 : 회원탈퇴  ] " + modelUser.getUserName() + "님! 회원탈퇴 됬어요ㅠㅠ" );
		modelEmail.setEmailText( "고객님께선 " + modelUser.getUserRegisterDate() + "일 기준으로 가입이 탈퇴되었어요ㅠㅠ"  );
		
		if( serviceSendEmail.functionSendEmail( modelEmail ) == true ) {
			
			return true;
		} else {
			
			return false;
		}
	}
}
